package club.ufdeen.DataDictExpolt.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
	private static Properties properties = new Properties();
	static {
		String path = System.getProperty("user.dir")+"/db.properties";
		FileInputStream in = null;
		try {
			in = new FileInputStream(path);
			properties.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	public static String getValue(String key) {
		return properties.getProperty(key);
	}
	
	
}
