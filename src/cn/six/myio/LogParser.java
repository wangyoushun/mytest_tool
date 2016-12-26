package cn.six.myio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class LogParser {

	private static final String searchStr="select t.* from etcmp_ibe_his";
	
	/**
	 * 统计ics查询次数
	 * @throws Exception
	 */
	@Test
	public void parset() throws Exception {

		String filePath = "D:/tmp/etcmp_log";
		List<String> list = new ArrayList<String>();
		
		File dir = new File(filePath);
		File[] listFiles = dir.listFiles();
		
		for (File file : listFiles) {
			System.out.println(file.getName());
			parseFile(file.getAbsolutePath(),list);
		}
		
		System.out.println(list.size());
		
		
		OutputStream out = new FileOutputStream("D:/tmp/etcmp_log/output.txt");
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
				out));
		for (String str : list) {
			writer.write(str);
			writer.newLine();
			writer.flush();
		}
		writer.close();
	}
	
	@SuppressWarnings("resource")
	@Test
	public void parset2() throws Exception {
		long startTime = System.currentTimeMillis();
		String fileName = "D:/tmp/etcmp_log/log-info.log.2016-08-20";
		String s="select t.* from etcmp_ibe_his";
		List<String> list = new ArrayList<String>();
		FileInputStream in = new FileInputStream(fileName);;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
		String temp = null;
		while((temp=bufferedReader.readLine())!=null){
			if(StringUtils.contains(temp, s)){
				System.out.println(temp);
				list.add(temp);
			}
		}
		System.out.println(list);
		long endTime = System.currentTimeMillis();
		System.out.println(endTime-startTime);
	}
	
	public void parseFile(String fileName,List<String> list) throws Exception {
		FileInputStream in = new FileInputStream(fileName);;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
		String temp = null;
		int countNum = 1;
		while((temp=bufferedReader.readLine())!=null){
			if(StringUtils.contains(temp, searchStr)){
				System.out.println(temp);
				list.add(fileName+"("+countNum+")"+"----><-----"+temp);
			}
			countNum++;
		}
		bufferedReader.close();
	}
}
