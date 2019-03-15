package com.amaysim.shoppingcart.implem.catalogue;

import java.io.IOException;
import java.util.List;

import com.amaysim.shoppingcart.base.catalogue.Catalogue;
import com.amaysim.shoppingcart.base.catalogue.CatalogueProduct;
import com.amaysim.shoppingcart.base.catalogue.ICatalogueProvider;
import com.amaysim.shoppingcart.implem.util.FileReaderUtil;

public class CsvCatalogueProvider implements ICatalogueProvider {

	private final int PRODUCT_CODE_INDEX = 0;
	private final int PRODUCT_NAME_INDEX = 1;
	private final int PRODUCT_PRICE_INDEX = 2;

	private String csvFilePath;
		
	public CsvCatalogueProvider(String csvFilePath) {
		this.csvFilePath = csvFilePath;
	}
	
	@Override
	public Catalogue provideCatalogue() throws IOException {
		boolean skipOnError = true;
		List<Object> catalogueItems = FileReaderUtil.processLines(this.csvFilePath, this::lineToProduct, skipOnError);
		
		Catalogue catalogue = new Catalogue();
		for(Object product : catalogueItems) {
			catalogue.addProduct((CatalogueProduct)product);
		}
		return catalogue;
	}

	private CatalogueProduct lineToProduct(String csvLine) {
		String[] csvTokens = csvLine.split(",");
		String code = csvTokens[PRODUCT_CODE_INDEX];
		String name = csvTokens[PRODUCT_NAME_INDEX];
		Double price = Double.parseDouble(csvTokens[PRODUCT_PRICE_INDEX]);
		return new CatalogueProduct(code, name, price);
	}
}
