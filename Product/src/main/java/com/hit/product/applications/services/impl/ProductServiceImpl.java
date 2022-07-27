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
    public List<Product> getProductsNewest() {
        return productRepository.findAll(PageRequest.of(0, 10, Sort.by("createdDate").descending())).getContent();
    }

    @Override
    public List<Product> getProductsBestSeller() {
        return productRepository.findAll(PageRequest.of(0, 10, Sort.by("amountSell").descending())).getContent();
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        checkProductException(product);
        return product.get();
    }

    @Override
    @Transactional
    public Product createProduct(Long idCategory,
                                 ProductDto productDto,
                                 List<MultipartFile> multipartFiles) {
        Optional<Category> category = categoryRepository.findById(idCategory);
        checkCategoryException(category);

        Product product = modelMapper.map(productDto, Product.class);

        product.setCategory(category.get());

        Slugify slug = new Slugify();
        String result = slug.slugify(productDto.getTitle());
        product.setSlug(result);

        multipartFiles.forEach(multipartFile -> {
            createImgProduct(product, new Image(), multipartFile);
        });

        product.setCategory(category.get());
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
                return productRepository.findAll(PageRequest.of(0, 10, Sort.by("title").descending())).getContent();
            case "hot":
                return productRepository.findAll(PageRequest.of(0, 10, Sort.by("amountSell").descending())).getContent();
            case "price-asc":
                return productRepository.findAll(PageRequest.of(0, 10, Sort.by("priceCurrent").ascending())).getContent();
            case "price-des":
                return productRepository.findAll(PageRequest.of(0, 10, Sort.by("priceCurrent").descending())).getContent();
            default:
                return productRepository.findAll();
        }
    }

    @Override
    public List<Product> getProductsByFilter(List<String> types, List<Integer> sizes, List<String> colors, List<String> brands) {
        List<Product> productIsTrue = new ArrayList<>();

        // Filter by type
        types.forEach(type -> {
            productIsTrue.addAll(productRepository.findByType(type));
        });

        // Filter by size
        sizes.forEach(size -> {
            if(size == 40)
                System.out.println("Yes");
            List<ProductSize> productSizes = (productSizeRepository.findByValue(size));
            productSizes.forEach(productSize -> {
                productIsTrue.add(productSize.getProduct());
            });
        });

        // Filter by color
        colors.forEach(color -> {
            List<ProductColor> productColors = (productColorRepository.findBySlug(color));
            productColors.forEach(productColor -> {
                productIsTrue.add(productColor.getProduct());
            });
        });

        // Filter by brand
        brands.forEach(brand -> {
            productIsTrue.addAll(productRepository.findByBrand(brand));
        });

        return productIsTrue;
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
