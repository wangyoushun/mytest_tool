package cn.six.utils;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PropertiesUtil {
	private final static Log LOG = LogFactory.getLog(PropertiesUtil.class);
	private static Properties properties = new Properties();

	private PropertiesUtil() {
	}

	static {
		try {
			properties.load(PropertiesUtil.class.getClassLoader()
					.getResourceAsStream("pay_config.properties"));
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
	}

	public static String readConfig(String key) {
		return (String) properties.get(key);
	}
	
	public static Properties newInstance(){
		return properties;
	}
	
	public static Properties newInstance(String path){
		try {
			properties.load(PropertiesUtil.class.getClassLoader()
					.getResourceAsStream("pay_config.properties"));
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
		return properties;
	}
	
	
	//test
	public static void main(String[] args) {
		String name = "jdbc.properties";
		Properties properties = new Properties();
		try {
			properties.load(PropertiesUtil.class.getClassLoader()
					.getResourceAsStream(name));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Set<Object> keySet = properties.keySet();
		for (Object object : keySet) {
			System.out.println(object);
		}

	}
}
