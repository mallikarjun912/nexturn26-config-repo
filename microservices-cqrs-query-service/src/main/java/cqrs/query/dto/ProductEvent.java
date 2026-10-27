package cqrs.query.dto;

import cqrs.query.entity.Product;

public class ProductEvent {
	private String eventType;
    private Product product;
    public ProductEvent() {
		// TODO Auto-generated constructor stub
	}
	public ProductEvent(String eventType, Product product) {
		this.eventType = eventType;
		this.product = product;
	}
	public String getEventType() {
		return eventType;
	}
	public Product getProduct() {
		return product;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
    
}
