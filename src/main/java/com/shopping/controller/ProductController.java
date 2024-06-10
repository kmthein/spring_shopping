package com.shopping.controller;

import com.shopping.entity.Category;
import com.shopping.entity.Image;
import com.shopping.entity.Product;
import com.shopping.repository.ImageRepository;
import com.shopping.service.CategoryService;
import com.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ImageRepository imageRepository;
    private static final String UPLOAD_DIR = System.getProperty("user.home") + "/spring_shopping/static/external-images";

    @GetMapping("/")
    public List<Product> renderHomePage() {
        return productService.getAllProduct();
    }

    @GetMapping("/product/{id}")
    public Product getProductDetail(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/saveProduct")
    public void addProduct(@ModelAttribute("product") Product product, @RequestParam("files") MultipartFile[] files) {
        List<Image> images = new ArrayList<>();

        productService.saveProduct(product);

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                try {
                    Files.createDirectories(Paths.get(UPLOAD_DIR));
                    file.transferTo(new File(UPLOAD_DIR + "/" + fileName));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Image image = new Image();
                image.setFileName(fileName);
                image.setFilePath("/external-images/" + fileName);
                image.setProduct(product);

                imageRepository.save(image);

                images.add(image);
            }
        }
    }

    @GetMapping("/all-products")
    public String renderAllProductsPage(Model model) {
        model.addAttribute("products", productService.getAllProduct());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "allProducts";
    }

    @GetMapping("/all-products/category={category}")
    public List<Product> getProductByCategory(@PathVariable Long category, Model model) {
        return productService.getProductByCategoryId(category);
    }

    @GetMapping("delete-product/{id}")
    public String deleteProductById(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/all-products";
    }
}



//@Controller
//public class ProductController {
//    @Autowired
//    private CategoryService categoryService;
//
//    private static final String UPLOAD_DIR = System.getProperty("user.home") + "/spring_shopping/static/external-images";
//
//    @Autowired
//    private ProductService productService;
//
//    @Autowired
//    private ImageRepository imageRepository;
//
//    @GetMapping("/")
//    public String renderHomePage(Model model) {
//        model.addAttribute("products", productService.getAllProduct());
//        return "index";
//    }
//
//    @GetMapping("/add-new/product")
//    public String renderAddProudctPage(Model model) {
//        model.addAttribute("products", new Product());
//        model.addAttribute("categories", categoryService.getAllCategories());
//        return "addProduct";
//    }
//
//    @PostMapping("/saveProduct")
//    public String addProduct(@ModelAttribute("product") Product product, @RequestParam("files") MultipartFile[] files) {
//        List<Image> images = new ArrayList<>();
//
//        productService.saveProduct(product);
//
//        for (MultipartFile file : files) {
//            if (!file.isEmpty()) {
//                String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
//                try {
//                    Files.createDirectories(Paths.get(UPLOAD_DIR));
//                    file.transferTo(new File(UPLOAD_DIR + "/" + fileName));
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                Image image = new Image();
//                image.setFileName(fileName);
//                image.setFilePath("/external-images/" + fileName);
//                image.setProduct(product);
//
//                imageRepository.save(image);
//
//                images.add(image);
//            }
//        }
//        return "redirect:/all-products";
//    }
//
//    @GetMapping("/product/{id}")
//    public String getProductDetail(@PathVariable Long id, Model model) {
//        model.addAttribute("product", productService.getProductById(id));
//        return "productDetail";
//    }
//
//    @GetMapping("/product-edit/{id}")
//    public String renderProductEditPage(@PathVariable Long id, Model model) {
//        model.addAttribute("product", productService.getProductById(id));
//        model.addAttribute("categories", categoryService.getAllCategories());
//        return "editProduct";
//    }
//
//    @GetMapping("/all-products")
//    public String renderAllProductsPage(Model model) {
//        model.addAttribute("products", productService.getAllProduct());
//        model.addAttribute("categories", categoryService.getAllCategories());
//        return "allProducts";
//    }
//
//    @GetMapping("/all-products/category={category}")
//    public String getProductByCategory(@PathVariable Long category, Model model) {
//        model.addAttribute("products", productService.getProductByCategoryId(category));
//        model.addAttribute("categories", categoryService.getAllCategories());
//        return "productByCategory";
//    }
//
//    @GetMapping("delete-product/{id}")
//    public String deleteProductById(@PathVariable Long id) {
//        productService.deleteProduct(id);
//        return "redirect:/all-products";
//    }
//}