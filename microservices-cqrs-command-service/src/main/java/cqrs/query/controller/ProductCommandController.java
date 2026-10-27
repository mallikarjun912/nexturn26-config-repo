package cqrs.query.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cqrs.query.dto.ProductEvent;
import cqrs.query.entity.Product;
import cqrs.query.service.ProductCommandService;

@RestController
@RequestMapping("/products")
public class ProductCommandController {
	@Autowired
    private ProductCommandService commandService;

    @PostMapping
    public Product createProduct(@RequestBody ProductEvent productEvent) {
    	System.out.println("Received product event: " + productEvent);
        return commandService.createProduct(productEvent);
    }
    
    @PutMapping
    public Product updateProduct( @RequestBody ProductEvent productEvent) {
    	System.out.println("Received product event: " + productEvent);
        return commandService.updateProduct(productEvent);
    }
}
