package com.hit.product.applications.services.impl;

import com.github.slugify.Slugify;
import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.configs.exceptions.NotFoundException;
import com.hit.product.applications.repositories.CategoryRepository;
import com.hit.product.applications.services.CategoryService;
import com.hit.product.domains.dtos.CategoryDto;
import com.hit.product.domains.entities.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        checkCategoryException(category);
        return category.get();
    }

    @Override
    public Category createCategory(CategoryDto categoryDto) {
        return createOrUpdate(new Category(), categoryDto);
    }

    @Override
    public Category updateCategory(Long id, CategoryDto categoryDto) {
        Optional<Category> category = categoryRepository.findById(id);
        checkCategoryException(category);
        return createOrUpdate(category.get(), categoryDto);
    }

    private Category createOrUpdate(Category category, CategoryDto categoryDto) {
        modelMapper.map(categoryDto, category);

        Slugify slug = new Slugify();
        String result = slug.slugify(categoryDto.getName());
        category.setSlug(result);

        return categoryRepository.save(category);
    }

    @Override
    public TrueFalseResponse deleteCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        checkCategoryException(category);
        categoryRepository.deleteById(id);
        return new TrueFalseResponse(true);
    }

    private void checkCategoryException(Optional<Category> category) {
        if(category.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }
}
