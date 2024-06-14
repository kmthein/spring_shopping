package com.shopping.controller;

import com.shopping.entity.Category;
import com.shopping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/add-category")
    public void renderAddCategoryPage(Model model) {
        model.addAttribute("category", new Category());
    }

    @PostMapping("/saveCategory")
    public void saveCategory(@RequestBody Category category) {
        categoryService.saveCategory(category);
    }

    @GetMapping("/all-category")
    public List<Category> getAllCategories(Model model) {
        return categoryService.getAllCategories();
    }
}

//@Controller
//public class CategoryController {
//
//    @Autowired
//    private CategoryService categoryService;
//
//    @GetMapping("/add-category")
//    public String renderAddCategoryPage(Model model) {
//        model.addAttribute("category", new Category());
//        return "addCategory";
//    }
//
//    @PostMapping("/saveCategory")
//    public String saveCategory(Category category) {
//        categoryService.saveCategory(category);
//        return "redirect:/";
//    }
//
//    @GetMapping("/all-category")
//    public String renderAllCategories(Model model) {
//        model.addAttribute("categories", categoryService.getAllCategories());
//            return "";
//    }
//}
