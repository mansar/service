package com.asi.service.product.client;

import org.springframework.web.client.RestOperations;

import com.asi.service.product.client.vo.ProductDetail;



public class ProductClient {
	private final RestOperations restTemplate;
	public ProductClient(RestOperations restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}
	 public ProductDetail doIt(Integer productID) {
		return searchProduct(productID);
	 }
	 
	 private ProductDetail searchProduct(Integer productID)
	 {
		 String productSearchUrl = "http://stage-espupdates.asicentral.com/api/api/ProductImport/{productID}";
		 ProductDetail product = restTemplate.getForObject(productSearchUrl,ProductDetail.class,productID);
		 return product;
	 }
}
