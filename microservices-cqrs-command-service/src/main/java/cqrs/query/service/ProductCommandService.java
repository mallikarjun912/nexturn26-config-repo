package cqrs.query.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import cqrs.query.dto.ProductEvent;
import cqrs.query.entity.Product;
import cqrs.query.repository.ProductRepository;

@Service
public class ProductCommandService {
	@Autowired
	ProductRepository repository;
	
	@Autowired
	KafkaTemplate<String, Object> kafkaTemplate;
	
	public Product  createProduct(ProductEvent productEvent) { 
		Product productDO = repository.save(productEvent.getProduct());
		ProductEvent event = new ProductEvent("CreateProduct", productDO);
		kafkaTemplate.send("product-event-topic", event);
		return productDO;
	}	
	
	public Product updateProduct(ProductEvent productEvent) { 
		Product existingProduct = repository.findById(productEvent.getProduct().getId()).get();
		Product newProduct = productEvent.getProduct();
		existingProduct.setName(newProduct.getName());
		existingProduct.setDescription(newProduct.getDescription());
		existingProduct.setPrice(newProduct.getPrice());
		Product productDO = repository.save(existingProduct);
		ProductEvent event = new ProductEvent("UpdateProduct", productDO);
		kafkaTemplate.send("product-event-topic", event);
		return productDO;
	}	
	
}
