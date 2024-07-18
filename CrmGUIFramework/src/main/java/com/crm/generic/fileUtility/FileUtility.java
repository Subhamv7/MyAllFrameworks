package com.crm.generic.fileUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
	public String getDataFromProperties(String key) throws IOException {
		FileInputStream fis = new FileInputStream("./configAppData/commondata1.properties");
		Properties pro= new Properties();
		pro.load(fis);
		String	value = pro.getProperty(key);
		return value;

	}
}
