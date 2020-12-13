package com.dkatalis.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class LoadPropertyWithSingleton {

	private static LoadPropertyWithSingleton obj = null;
	static Properties prop;

	private LoadPropertyWithSingleton() throws Exception {
		FileInputStream fs = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\com\\dkatalis\\resources\\config.properties");
		prop = new Properties();
		prop.load(fs);
	}

	public synchronized static LoadPropertyWithSingleton getinstance() throws Exception {
		if (obj == null)
			obj = new LoadPropertyWithSingleton();

		return obj;

	}

	public static String getvaluefromproperty(String key) {
		return prop.getProperty(key);
	}
}
