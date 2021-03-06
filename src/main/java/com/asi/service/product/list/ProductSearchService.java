package com.asi.service.product.list;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.asi.core.repo.product.ProductRepo;
import com.asi.service.product.client.ProductClient;
import com.asi.service.product.client.vo.ProductDetail;
import com.asi.service.product.vo.Product;

@RestController
@RequestMapping("api")
public class ProductSearchService {
	@Autowired ProductDetail serviceResponse; 
	@Autowired ProductRepo repository;
	private Logger _LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired ProductClient client;

	@RequestMapping(value = "product/{xid}",method = RequestMethod.GET, headers="content-type=application/json, application/xml" ,produces={"application/xml", "application/json"} )
	public ResponseEntity<Product> handle(HttpEntity<byte[]> requestEntity,@PathVariable String xid) throws UnsupportedEncodingException {
				
		if(_LOGGER.isTraceEnabled()) _LOGGER.trace("calling service");
		
		Product productResponse = repository.getProductPrices(Integer.valueOf(xid).intValue());
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.set("MyResponseHeader", "MyValue");
	    return new ResponseEntity<Product>(productResponse, responseHeaders, HttpStatus.OK);
	}

}
