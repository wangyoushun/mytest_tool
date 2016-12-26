package cn.six.mystring;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.junit.Test;

public class Code {

	//编码与解码
    @Test
    public void code() throws Exception{
    	String s = "哈哈";
    	System.out.println(s);
    	String string = new String(s.getBytes("utf-8"),"gbk");
    	System.out.println(string);
    	
    	String s2 = new String(s.getBytes("utf-8"),"iso8859-1");
    	System.out.println(s2);
    	String s5 = new String(s2.getBytes("iso8859-1"),"utf-8");
    	System.out.println(s5);
    	
    	String s3 = new String(s.getBytes("gbk"),"iso8859-1");
    	System.out.println(s3);
    	String s4 = new String(s3.getBytes("iso-8859-1"),"gbk");
    	System.out.println(s4);
    	
    	System.out.println("系统默认编码"+Charset.defaultCharset().name());//gbk
    	
    	String encode = URLEncoder.encode(s, "utf-8");
    	System.out.println(encode);
    	String decode = URLDecoder.decode(encode, "utf-8");
    	System.out.println(decode);
    	
    	
    	
    }
}
