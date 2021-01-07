package ru.itis.hateoas.simplehateoasservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.hateoas.simplehateoasservice.services.ProductService;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PutMapping(value = "/products/{product-id}/updatePrice")
    public @ResponseBody
    ResponseEntity<?> send(@PathVariable("product-id") Long bookingId,
                           @RequestParam("price")Integer price) {
        return ResponseEntity.ok(
                EntityModel.of(productService.updatePrice(bookingId, price)));
    }
}
