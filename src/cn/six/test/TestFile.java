/**
 * 
 */
package cn.six.test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.six.utils.FileTool;

/**
 * @author : wangyoushun
 * @createTime : 2017年7月13日 下午3:59:06
 * @version : 1.0
 * @description
 */
public class TestFile {
	
	public static void main(String[] args) {
		
	}
	

	@Test
	public void getUserList() throws Exception {
		System.out.println("获取用户开始=========");
		
		String path = "F:\\pus";
		String newPath = "F:\\pus\\newtxt.txt";
		File file = new File(path);
		File[] listFiles = file.listFiles();
		List<String> list = new ArrayList<String>();
		List<String> nlist = new ArrayList<String>();
		for (File file2 : listFiles) {
			List<String> lines = FileTool.lines(file2);
			list.addAll(lines);
		}

		System.out.println(list.size());
		for (String str : list) {
			if (!str.contains("\"userID\"")) {
				String replace = str.replace("\"", "");
//				System.out.println(replace);
				nlist.add(replace);
			}
		}

		FileTool.writeAppend(new File(newPath), nlist);
		System.out.println(nlist.size());
		System.out.println("获取用户结束=========");
	}
	
	

}
