package com.asi.core.view.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractJExcelView;

@Component("product;application/vnd.ms-excel")
public class ProductExcelXLSView extends AbstractJExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			WritableWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setContentType("application/vnd.ms-excel");
	      WritableSheet sheet = workbook.createSheet("Persons", 0);

	        sheet.addCell(new Label(0, 0, "XID"));
	        sheet.addCell(new Label(1, 0, "Name"));
	        
	        sheet.addCell(new Label(0, 1, "12123213"));
	        sheet.addCell(new Label(1, 2, "12123213"));


	}

}
