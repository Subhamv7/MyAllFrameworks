package com.crm.generic.fileUtility;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtility {
public String getDataFromJson(String key) throws IOException, ParseException {
	FileReader f = new FileReader("./configAppData/jsonData.json");
	JSONParser j = new JSONParser();
	Object obj = j.parse(f);
	JSONObject jobj = (JSONObject)obj;
	String value = (String) jobj.get(key);
	return value;
}
}
