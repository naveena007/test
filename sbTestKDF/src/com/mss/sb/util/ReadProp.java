package com.mss.sb.util;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


public class ReadProp{
public Properties getDataProp() throws Exception{

		File f= new File("E:\\AdityaAgent\\_work\\3\\s\\sbTestKDF\\src\\com\\mss\\sb\\resources\\SbAllProperty.properties");
		FileInputStream fis = new FileInputStream(f);
		Properties p= new Properties();
		p.load(fis);
		return p;
}
public Properties getPath() throws Exception{
	Properties p=getDataProp();
	File f= new File(p.getProperty("configPath"));
	FileInputStream fis = new FileInputStream(f);
	Properties p1= new Properties();
	p1.load(fis);
	//ReadProp rp=new ReadProp();
	//Properties p2=rp.getemployeeobjectPath();
	return p1;
}
/*public Properties getEmployeeObjectPath() throws Exception{
	Properties p=getDataProp();
	File f= new File(p.getProperty("empPath"));
	FileInputStream fis = new FileInputStream(f);
	Properties p2= new Properties();
	p2.load(fis);
	
	return p2;
}
public Properties getSiteAdminObjectPath() throws Exception{
	Properties p=getDataProp();
	File f= new File(p.getProperty("siteAdminPath"));
	FileInputStream fis = new FileInputStream(f);
	Properties p2= new Properties();
	p2.load(fis);
	return p2;
}
public Properties getCustomerAdminObjPath() throws Exception{
	Properties p=getDataProp();
	File f= new File(p.getProperty("customerAdminPath"));
	FileInputStream fis = new FileInputStream(f);
	Properties p2= new Properties();
	p2.load(fis);
	return p2;
} 
public Properties getProjectManagerObjPath() throws Exception{
	Properties p=getDataProp();
	File f= new File(p.getProperty("projectManagerPath"));
	FileInputStream fis = new FileInputStream(f);
	Properties p2= new Properties();
	p2.load(fis);
	return p2;
} 
public Properties getCsrObjPath() throws Exception{
	Properties p=getDataProp();
	File f= new File(p.getProperty("csrPath"));
	FileInputStream fis = new FileInputStream(f);
	Properties p2= new Properties();
	p2.load(fis);
	return p2;
}
public Properties getDirectorObjPath() throws Exception{
	Properties p=getDataProp();
	File f= new File(p.getProperty("directorPath"));
	FileInputStream fis = new FileInputStream(f);
	Properties p2= new Properties();
	p2.load(fis);
	return p2;
}
*/
public Properties getLoginObjPath() throws Exception{
	Properties p=getDataProp();
	File f= new File(p.getProperty("loginPath"));
	FileInputStream fis = new FileInputStream(f);
	Properties p2= new Properties();
	p2.load(fis);
	return p2;
}
/*
public Properties getAllExcelSheets() throws Exception{

	File f= new File("E:\\CASProject\\CASTestKeywordReal\\src\\com\\mss\\hubble\\resources\\excelSheets.properties");
	FileInputStream fis = new FileInputStream(f);
	Properties p= new Properties();
	p.load(fis);
	return p;
}
*/
/*public Properties getsecuritypath() throws Exception{
	Properties p=getDataProp();
	File f= new File(p.getProperty("path"));
	FileInputStream fis = new FileInputStream(f);
	Properties p1= new Properties();
	p1.load(fis);
	
	return p1;
}*/
}
