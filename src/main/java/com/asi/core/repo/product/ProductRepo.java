package com.asi.core.repo.product;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.asi.service.product.client.LookupValuesClient;
import com.asi.service.product.client.ProductClient;
import com.asi.service.product.client.vo.Price;
import com.asi.service.product.client.vo.PriceGrid;
import com.asi.service.product.client.vo.ProductDetail;
import com.asi.service.product.vo.ItemPriceDetail;
import com.asi.service.product.vo.Product;

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
	
	public Product getProductPrices(Integer productID)
	{
	    
		productDetail = productClient.doIt(productID);
		Product product = new Product();
		BeanUtils.copyProperties(productDetail, product);
		
		List<PriceGrid> priceGrids = productDetail.getPriceGrids();
		List<ItemPriceDetail> pricesInfo = new ArrayList<ItemPriceDetail>();
		
		for(PriceGrid prices:priceGrids)
		{
			pricesInfo.add(getBasePriceDetails(prices,false));
		}
		product.setItemPrices(pricesInfo);

		_LOGGER.debug("Color " + lookupColor.getColorFromLookup(lookupColor.getLookupColorURL()).toString());
		_LOGGER.debug("Sizes " + lookupColor.getSizesFromLookup(lookupColor.getLookupSizeURL()).toString());
		_LOGGER.debug("Material " + lookupColor.getMaterialFromLookup(lookupColor.getLookupMaterialURL()).toString());
		
		return product;
		
	}
    private ItemPriceDetail getBasePriceDetails(PriceGrid priceGrid, boolean setCurrency) {
    	ItemPriceDetail basePriceDetails = new ItemPriceDetail();
       // basePriceDetails.setBasePriceName(priceGrid.getDescription());
        
        List<String> pricesList = new ArrayList<String>();
        List<String> quantityList = new ArrayList<String>();
        List<String> discountList = new ArrayList<String>();
        for (Price p : priceGrid.getPrices()) {
            pricesList.add(p.getListPrice()+"");
            quantityList.add(p.getQuantity()+"");
            discountList.add(p.getDiscountRate().getIndustryDiscountCode());
        }
        

        return basePriceDetails;
    }

}