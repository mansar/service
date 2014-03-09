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
import org.springframework.web.bind.annotation.RestController;

import com.asi.service.product.client.ProductClient;
import com.asi.service.product.client.vo.ProductDetail;
import com.asi.service.product.vo.Product;

@RestController
@RequestMapping("api")
public class ProductSearchService {
	@Autowired ProductDetail serviceResponse; 
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired ProductClient client;

	@RequestMapping(value = "product/{xid}",headers="content-type=application/json, application/xml" ,produces={"application/xml", "application/json"} )
	public ResponseEntity<Product> handle(HttpEntity<byte[]> requestEntity,@PathVariable String xid) throws UnsupportedEncodingException {
		
		if(logger.isTraceEnabled()) logger.trace("calling service");
		serviceResponse = client.doIt(Integer.valueOf(xid).intValue());
		Product productResponse = new Product();
		productResponse.setiD(serviceResponse.getID());
		

		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.set("MyResponseHeader", "MyValue");
	    return new ResponseEntity<Product>(productResponse, responseHeaders, HttpStatus.ACCEPTED);
	}

}
