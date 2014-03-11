package com.asi.core.repo.product;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.asi.service.product.client.LookupValuesClient;
import com.asi.service.product.client.ProductClient;
import com.asi.service.product.client.vo.Price;
import com.asi.service.product.client.vo.PriceGrid;
import com.asi.service.product.client.vo.ProductDetail;
import com.asi.service.product.client.vo.ProductNumber;

public class ProductRepo {
	private final static Logger _LOGGER = Logger
			.getLogger(ProductRepo.class.getName());
	/**
	 * @return the productClient
	 */
	public ProductClient getProductClient() {
		return productClient;
	}
	/**
	 * @param productClient the productClient to set
	 */
	public void setProductClient(ProductClient productClient) {
		this.productClient = productClient;
	}
	@Autowired ProductClient productClient;
	@Autowired ProductDetail productDetail;
	@Autowired LookupValuesClient lookupColor;
	
	public void getPriceConfiguration(Integer productID)
	{
	    
		productDetail = productClient.doIt(productID);
		List<ProductNumber> productRelation = productDetail.getProductNumbers();
		List<PriceGrid> priceGrids = productDetail.getPriceGrids();
		for(ProductNumber relationsToPriceGrid:productRelation)
		{
			Integer priceGridId = relationsToPriceGrid.getPriceGridId();
			_LOGGER.debug("Product Number ID" + relationsToPriceGrid.getProductId());
			for(PriceGrid priceGrid:priceGrids)
			{
				_LOGGER.debug("Price Description" + priceGrid.getDescription());
				if(priceGrid.getID().equals(priceGridId))
				{
					for(Price price:priceGrid.getPrices())
					{
						_LOGGER.debug("ListPrice for Quantity" + price.getQuantity() + " = " + price.getListPrice());
						
					}
				}
			}

		}
		_LOGGER.debug("Color " + lookupColor.getColorFromLookup(lookupColor.getLookupColorURL()).toString());
		_LOGGER.debug("Sizes " + lookupColor.getSizesFromLookup(lookupColor.getLookupSizeURL()).toString());
		_LOGGER.debug("Material " + lookupColor.getMaterialFromLookup(lookupColor.getLookupMaterialURL()).toString());
		
	}
}
