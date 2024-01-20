package com.rowel.shoppers.controller;

import com.rowel.shoppers.model.Product;
import com.rowel.shoppers.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;


@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @RequestMapping("/")
    public String getProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "list-products";
    }

   @GetMapping("/addProduct")
   public String getAddProductForm(Product product){
       return "addProduct";
   }


    @PostMapping("/addProduct")
    public String insertProductData(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("product", product);
            return "addProduct";
        }
        productRepository.save(product);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String showProductConfirmation(@PathVariable("id") int id, Model model) {
        Product student = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Product ID " + id));
        model.addAttribute("id", id);
        return "confirm-delete";
    }
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id, @RequestParam("confirm") String confirm, Model model) {
        if (confirm.equals("yes")) {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Product ID " + id));
            productRepository.delete(product);
        }
        model.addAttribute("products", productRepository.findAll());
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String moveToUpdateProduct(@PathVariable("id")int id, Model model){
        Product product = productRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Invalid Product ID" + id));
        model.addAttribute("product", product);
        return "editProduct";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id")int id,@Valid Product product,BindingResult bindingResult,Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("product", product);
            return "editProduct";
        }
        productRepository.save(product);
        model.addAttribute("students", productRepository.findAll());
        return "redirect:/";
    }

    @ExceptionHandler(Throwable.class)
    public String handleException(Model model, Exception ex) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

}//end controller

