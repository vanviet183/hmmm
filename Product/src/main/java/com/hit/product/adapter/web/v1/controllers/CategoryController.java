package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.RestApiV1;
import com.hit.product.adapter.web.base.VsResponseUtil;
import com.hit.product.applications.constants.UrlConstant;
import com.hit.product.applications.services.CategoryService;
import com.hit.product.domains.dtos.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestApiV1
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping(UrlConstant.Category.DATA_CATEGORY)
    public ResponseEntity<?> getCategories() {
        return VsResponseUtil.ok(categoryService.getAll());
    }

    @GetMapping(UrlConstant.Category.DATA_CATEGORY_ID)
    public ResponseEntity<?> getCategory(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(categoryService.getCategoryById(id));
    }

    @PostMapping(UrlConstant.Category.DATA_CATEGORY_CREATE)
    public ResponseEntity<?> createCategory(@RequestBody @Valid CategoryDto categoryDto) {
        return VsResponseUtil.ok(categoryService.createCategory(categoryDto));
    }

    @PatchMapping(UrlConstant.Category.DATA_CATEGORY_ID)
    public ResponseEntity<?> updateCategory(@PathVariable("id") Long id, @RequestBody CategoryDto categoryDto) {
        return VsResponseUtil.ok(categoryService.updateCategory(id, categoryDto));
    }

    @DeleteMapping(UrlConstant.Category.DATA_CATEGORY_ID)
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(categoryService.deleteCategory(id));
    }

}
