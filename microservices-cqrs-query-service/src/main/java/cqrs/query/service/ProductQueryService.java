package cqrs.query.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import cqrs.query.dto.ProductEvent;
import cqrs.query.entity.Product;
import cqrs.query.repository.ProductRepository;
@Service
public class ProductQueryService {
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
	@KafkaListener(topics = "product-event-topic", groupId = "product-event-group")
	public void processProductEvents(ProductEvent productEvent) {
		Product product = productEvent.getProduct();
		if(productEvent.getEventType().equals("CreateProduct")) {
			productRepository.save(product);
		} else if(productEvent.getEventType().equals("UpdateProduct")) {
			Product existingProduct = productRepository.findById(productEvent.getProduct().getId()).get();
			existingProduct.setName(product.getName());
			existingProduct.setDescription(product.getDescription());
			existingProduct.setPrice(product.getPrice());
			productRepository.save(existingProduct);
		} 		
	}
}
