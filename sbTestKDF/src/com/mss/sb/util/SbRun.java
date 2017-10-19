package com.mss.sb.util;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import nu.studer.java.util.OrderedProperties;

public class SbRun extends Extents {
	WebDriver driver;
	String driverName;

	public static void main(String[] args) throws Exception {
		new SbRun().testing();
	}

	@Test
	public void testing() throws Exception {

		ReadProp rp = new ReadProp(); // calling ReadProp class
		//Properties excelSheets =rp.getAllExcelSheets();
		Properties prop = rp.getPath();
		
	/*	Properties adminProp = rp.getSiteAdminObjectPath();
		prop.putAll(adminProp);
		
		Properties cAdminProp = rp.getCustomerAdminObjPath();
		prop.putAll(cAdminProp);
		
		Properties pManagerProp = rp.getProjectManagerObjPath();
		prop.putAll(pManagerProp);
		
		Properties csrProp = rp.getCsrObjPath();
		prop.putAll(csrProp);
		
		Properties dirProp = rp.getDirectorObjPath();
		prop.putAll(dirProp);
		*/
		Properties loginProp = rp.getLoginObjPath();
		prop.putAll(loginProp);
		
			
		String browser = prop.getProperty("driver");
		if (browser.equalsIgnoreCase("firefox")) {
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
					"text/csv,application/vnd.ms-excel,text/x-csv,application/x-msexcel,application/excel,application/x-excel");
			profile.setPreference("browser.helperApps.alwaysAsk.force", false);
			profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
			driver = new FirefoxDriver(profile);
			driverName = "Firefox Browser";
		}
		// for chrome browser
		else if (browser.equalsIgnoreCase("chrome")) {
			System.out.println("----->"+prop.getProperty("chromedriver"));
			System.setProperty("webdriver.chrome.driver", prop.getProperty("chromedriver"));
			ChromeOptions co = new ChromeOptions();
			co.addArguments("disable-infobars");
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("password_manager_enabled", false);
			prefs.put("profile.default_content_setting_values.notifications", 2);
			co.setExperimentalOption("prefs", prefs);
			driver = new ChromeDriver(co);
			driverName = "Chrome Browser";
		} else {
			System.out.println("browser is incorrect");
		}
		int i = 0;
		driver.manage().window().maximize(); // maximize the window
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		OrderedProperties properties2 = new OrderedProperties();
		properties2.load(new FileInputStream(new File("E:\\AdityaAgent\\_work\\3\\s\\sbTestKDF\\src\\com\\mss\\sb\\resources\\AllExcelSheets.properties")));
		
	
		try {
			information();
			
			//properties2.entrySet();
			
			for (Entry<String, String> entry : properties2.entrySet()) {
				//System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
			
			ReadExcelSheet er = new ReadExcelSheet(entry.getValue());// calling Excel sheet
			
			
			GettingData gd = new GettingData(driver);
			String scenarios = rp.getPath().getProperty("mainsheet"); // calling
																		// scenarios
																		// worksheet
			System.out.println(scenarios);
			System.out.println("count: " + er.count(scenarios));
			er.removeCells(scenarios, 3); // removing result data in scenarios
											// worksheet
			/*for(int j=2;j<=er.count(scenarios);j++){ //for loop starts
		 if(er.readData(scenarios, j, 2).equals("Y")){ // verifying
			// whether Y is present or not in scenarios sheet
			er.removeCells(er.readData(scenarios, j, 0),7);
			er.removeCells(er.readData(scenarios, j, 0),8); // removing
			// result data in current testcase
			 }
			 }*/ // for loop ends
			
			
			for (int k = 1; k <= er.count(scenarios); k++) { // for loop-1
																// starts
				String required = er.readData(scenarios, k, 2);
				String tc = er.readData(scenarios, k, 1); // saving respective
															// testcase number
				if (required.equals("Y")) { // verifying whether Y is present or
											// not in scenarios sheet
					String sh = er.readData(scenarios, k, 0); // calling
																// respective
																// testcase
																// sheet
					System.out.println(sh);
					test = er1.startTest(tc + " -- " + sh); // report starts for
															// this testcase
					int count = er.count(sh); // number of rows in testcase
					
					WriteExcelSheet wes1 = new WriteExcelSheet(entry.getValue()); // write
																							// data
																							// into
																							// excel
																							// sheet
					 
					wes1.writeData(scenarios, k, 3, "Pass", true); // Pass is
																	// typed in
																	// excel
																	// sheet
					for (i = 2; i <= count; i++) { // for loop-2 starts
						Thread.sleep(500);
						if (!er.readData(sh, i, 1).equalsIgnoreCase("FIND")) {
							gd.operation(prop, er.readData(sh, i, 0), er.readData(sh, i, 1).toUpperCase(),
									er.readData(sh, i, 2), er.readData(sh, i, 3), er.readData(sh, i, 4),
									er.readData(sh, i, 5), er.readData(sh, i, 6), i, sh, k, entry.getValue());
						}
						if (er.readData(sh, i, 1).equalsIgnoreCase("GOTOURL")) { // verifying
																					// title
																					// name
							String title_name = driver.getTitle();
							String value = "Problem loading page";
							if (title_name.equals(value.trim())) {
								wes1.writeData(scenarios, k, 3, "Fail", false);
								wes1.writeData(sh, i, 8, "Fail", false);
								break;
							} else
								System.out.println("URL is succussfully running");
						}
					} // for loop-2 ends
				} else if (!(required.equals("") || required.equals("empty"))) { // for
																					// entering
																					// wrong
																					// input
																					// data
					String sh = er.readData(scenarios, k, 0);
					System.out.println(sh);
					//System.out.println(required);
					
					WriteExcelSheet we1 = new WriteExcelSheet(entry.getValue());
					
					we1.writeData(scenarios, k, 3, "Invalid Input", false);
				}
				endExtent(); // ending report for current testcase
			} // for loop-1 ends
			
			  }// while end-------------->
			
			
	
			System.out.println("Test execution is completed");
			driver.quit(); // closing browser
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			System.setProperty("webdriver.chrome.driver", prop.getProperty("chromedriver"));
			/*driver = new FirefoxDriver(); // for opening report
			driver.manage().window().maximize();
			driver.get(folder);*/
			ChromeOptions options=new ChromeOptions();
			options.addArguments("--disable-extensions");
			options.addArguments("--test-type");
			driver= new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.get(folder);

		//	Sending send = new Sending();
		//	send.mail(folder);

			try {
				for (Entry<String, String> entry : properties2.entrySet()) {
				//Desktop.getDesktop().open(new File(prop.getProperty("empexcelpath"))); // for
				Desktop.getDesktop().open(new File(entry.getValue())); // for
  																// opening
				}																// excel
																				// sheet
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}