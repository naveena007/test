package com.mss.sb.util;

public class ExcelSheetcalling 
{
	public ReadExcelSheet getEmpsheet() throws Exception{
		ReadProp rp = new ReadProp(); // calling ReadProp class
		ReadExcelSheet er = new ReadExcelSheet(rp.getPath().getProperty("empexcelsheetpath")); // calling excel sheet
	return er;
} 
	public ReadExcelSheet getWebadminsheet() throws Exception{
		ReadProp rp = new ReadProp(); // calling ReadProp class
		ReadExcelSheet er = new ReadExcelSheet(rp.getPath().getProperty("webadminexcelsheetpath")); // calling excel sheet
	return er;
} 
}
