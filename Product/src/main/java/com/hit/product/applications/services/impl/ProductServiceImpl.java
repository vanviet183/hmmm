package com.hit.product.applications.services.impl;

import com.github.slugify.Slugify;
import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.applications.repositories.*;
import com.hit.product.applications.services.ProductService;
import com.hit.product.applications.utils.UploadFile;
import com.hit.product.configs.exceptions.NotFoundException;
import com.hit.product.domains.dtos.ProductDto;
import com.hit.product.domains.entities.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UploadFile uploadFile;

    @Autowired
    ProductSizeRepository productSizeRepository;

    @Autowired
    ProductColorRepository productColorRepository;

    @Override
    public List<Product> getAllByPage(Integer page) {
        List<Product> products = new ArrayList<>();
        if (page != null) {
            products = productRepository.findAll(PageRequest.of(page, 10, Sort.by("createdDate"))).getContent();
        } else {
            products = productRepository.findAll();
        }
        return products;
    }

    @Override
    public List<Product> getProductsNewest(Integer page) {
        return productRepository.findAll(PageRequest.of(page, 10, Sort.by("createdDate").descending())).getContent();
    }

    @Override
    public List<Product> getProductsBestSeller(Integer page) {
        return productRepository.findAll(PageRequest.of(page, 10, Sort.by("amountSell").descending())).getContent();
    }



    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        checkProductException(product);
        return product.get();
    }

    @Override
    public List<Image> getImageByProductId(Long id) {
        Optional<Product> product = productRepository.findById(id);
        checkProductException(product);

        return imageRepository.findByProduct_Id(id);
    }

    @Override
    @Transactional
    public Product createProduct(Long idCategory,
                                 ProductDto productDto,
                                 List<ProductColor> productColors,
                                 List<ProductSize> productSizes,
                                 List<MultipartFile> multipartFiles) {
        Optional<Category> category = categoryRepository.findById(idCategory);
        checkCategoryException(category);

        Product product = modelMapper.map(productDto, Product.class);

        Slugify slug = new Slugify();
        String result = slug.slugify(productDto.getTitle());
        product.setSlug(result);

        product.setProductColors(productColors);
        product.setProductSizes(productSizes);

        multipartFiles.forEach(multipartFile -> {
            createImgProduct(product, new Image(), multipartFile);
        });

        return productRepository.save(product);
    }

    public void createImgProduct(Product product, Image image, MultipartFile multipartFile) {
        image.setImageUrl(uploadFile.getUrlFromFile(multipartFile));
        image.setProduct(product);
        imageRepository.save(image);
    }

    @Override
    @Transactional
    public Product updateProduct(Long id, ProductDto productDto, List<MultipartFile> multipartFiles) {
        Optional<Product> product = productRepository.findById(id);
        checkProductException(product);
        modelMapper.map(productDto, product.get());

        Slugify slug = new Slugify();
        String result = slug.slugify(productDto.getTitle());
        product.get().setSlug(result);

        imageRepository.deleteAll(product.get().getImages());

        multipartFiles.forEach(multipartFile -> {
            createImgProduct(product.get(), new Image(), multipartFile);
        });

        return productRepository.save(product.get());
    }

    @Override
    public TrueFalseResponse deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        checkProductException(product);
        productRepository.deleteById(id);
        return new TrueFalseResponse(true);
    }

    @Override
    public List<Product> searchProducts(String nameProduct) {
        Slugify slug = new Slugify();
        String result = slug.slugify(nameProduct);

        return productRepository.findBySlugContaining(result);
    }

    // Sort
    @Override
    public List<Product> getProductsSort(String type) {
        switch (type) {
            case "new":
                return productRepository.findAll(PageRequest.of(1, 10, Sort.by("title").ascending())).getContent();
            case "hot":
                return productRepository.findAll(PageRequest.of(1, 10, Sort.by("title").descending())).getContent();
            case "price-asc":
                return productRepository.findAll(PageRequest.of(1, 10, Sort.by("price").ascending())).getContent();
            case "price-des":
                return productRepository.findAll(PageRequest.of(1, 10, Sort.by("price").descending())).getContent();
            default:
                return productRepository.findAll();
        }
    }

    @Override
    public List<Product> getProductsByFilter(List<String> types, List<Integer> sizes, List<String> colors, List<String> brands) {
        List<Product> products = new ArrayList<>();

        // Filter by type
        types.forEach(type -> {
            List<Product> productsTypeTrue = productRepository.findByType(type);
            products.addAll(productsTypeTrue);
        });

        // Filter by size
        sizes.forEach(size -> {
            List<ProductSize> productSizes = productSizeRepository.findByValue(size);
            productSizes.forEach(productSize -> {
                products.add(productSize.getProduct());
            });
        });

        // Filter by color
        Slugify slug = new Slugify();
        colors.forEach(color -> {
            String result = slug.slugify(color);
            List<ProductColor> productColors = productColorRepository.findBySlug(result);
            productColors.forEach(productColor -> {
                products.add(productColor.getProduct());
            });
        });

        // Filter by brand
        brands.forEach(brand -> {
            List<Product> productsBrandTrue = productRepository.findByType(brand);
            products.addAll(productsBrandTrue);
        });

        return products;
    }

    private void checkCategoryException(Optional<Category> category) {
        if(category.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }

    private void checkProductException(Optional<Product> product) {
        if(product.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }
}
