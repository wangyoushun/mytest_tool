/**
 * 
 */
package cn.six.test;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

/**
 * @author : wangyoushun
 * @createTime : 2017年6月5日 下午6:45:11
 * @version : 1.0
 * @description
 */
public class PropertiesTest {

	private static Properties pro=new Properties();
	@Test
	public void testName() throws Exception {
		 try (InputStream in = new BufferedInputStream(new FileInputStream("e://app_version.properties"))) {
	            pro.load(in);
	            String property = pro.getProperty(".curVersion");
	            System.out.println(property);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	 /**
     * 根据Key读取Value
     *
     * @param filePath 属性文件
     * @param key      需要读取的属性
     */
    public static String GetValueByKey(String filePath, String key) {
        Properties pps = new Properties();
        try (InputStream in = new BufferedInputStream(new FileInputStream(filePath))) {
            pps.load(in);
            return pps.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
