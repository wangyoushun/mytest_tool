package cn.six.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName: FileTool
 * @Description: 提供一些文件操作工具类
 * @author iwantfly
 * @date 2017年7月8日 下午4:18:17
 *
 */
public class FileTool {

	private static Logger logger = LoggerFactory.getLogger(FileTool.class);
	public static final String ENCODE_GBK = "GBK";
	public static final String ENCODE_UTF8 = "UTF-8";
	private static final boolean TOCAMEL = false;

	private static Map<String, String> map = new HashMap<String, String>();

	// 提供sql语句转java实体类型
	static {
		map.put("tinyint", "Integer");
		map.put("int", "Integer");
		map.put("bigint", "Integer");
		map.put("double", "Double");
		map.put("varchar", "String");
		map.put("char", "String");
		map.put("date", "Date");
		map.put("datetime", "Date");
		map.put("timestamp", "Date");
	}

	/**
	 * 以列表的方式获取文件的所有行
	 *
	 * @param file
	 *            需要出来的文件
	 * @return 包含所有行的list
	 */
	public static List<String> lines(File file) {
		List<String> list = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				list.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 以列表的方式获取文件的所有行
	 *
	 * @param file
	 *            需要处理的文件
	 * @param encoding
	 *            指定读取文件的编码
	 * @return 包含所有行的list
	 */
	public static List<String> lines(File file, String encoding) {
		List<String> list = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding))) {
			String line;
			while ((line = reader.readLine()) != null) {
				list.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 将字符串以追加的方式写入到文件中
	 */
	public static boolean writeAppend(File file, String str) {
		try (RandomAccessFile randomFile = new RandomAccessFile(file, "rw")) {
			long fileLength = randomFile.length();
			randomFile.seek(fileLength);
			if (fileLength > 0) {
				randomFile.writeBytes(System.getProperty("line.separator"));
			}
			randomFile.writeBytes(str);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 将字符串以追加的方式写入到文件中
	 */
	public static boolean writeAppend(File file, List<String> strList) {
		try (RandomAccessFile randomFile = new RandomAccessFile(file, "rw")) {
			long fileLength = randomFile.length();
			randomFile.seek(fileLength);
			if (fileLength > 0) {
				randomFile.writeBytes(System.getProperty("line.separator"));
			}

			for (String str : strList) {
				randomFile.write(str.getBytes("UTF-8"));
				randomFile.writeBytes(System.getProperty("line.separator"));
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 文件拷贝
	 * 
	 * @param s
	 * @param t
	 */
	public static void fileChannelCopy(File s, File t) {
		FileInputStream fi = null;
		FileOutputStream fo = null;
		FileChannel in = null;
		FileChannel out = null;
		try {
			fi = new FileInputStream(s);
			fo = new FileOutputStream(t);
			in = fi.getChannel();// 得到对应的文件通道
			out = fo.getChannel();// 得到对应的文件通道
			in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fi.close();
				in.close();
				fo.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 遍历文件夹
	public static void copyFile(File file, String destPath) {
		if (file.isDirectory()) {
			System.out.println("==mulu==" + file.getName());
			File file2 = new File(destPath);
			if (!file2.exists()) {
				file2.mkdirs();
				System.out.println("==create mulu==" + destPath);
			}
			File[] listFiles = file.listFiles();
			for (File f : listFiles) {
				copyFile(f, destPath + "/" + f.getName());
			}
		} else {
			File file2 = new File(destPath);
			if (file2.exists() && file2.getName().equals(file.getName())) {
				System.out.println(file.getName() + "====exist");
				return;
			}
			System.out.println("==file==" + file.getName() + "----" + file.getAbsolutePath());
			fileChannelCopy(file, new File(destPath));
		}
	}

	/**
	 * 
	 * @Title: jsonStrToJavaJsonStr @Description: 将json串转化为 java中的字符串 @param
	 *         file @return String 返回类型 @throws
	 */
	public static String jsonStrToJavaJsonStr(File file) {
		List<String> lines = lines(file, ENCODE_GBK);
		String jsonStr = "";
		for (String str : lines) {
			str = str.replace("\"", "\\\"");
			jsonStr += str.trim();
		}
		return jsonStr;
	}

	/**
	 * 
	 * @Title: sqlStrToEntity @Description: sql语句转java实体 @param @param
	 *         path @param @return 设定文件 @return String 返回类型 @throws
	 */
	public static String sqlStrToEntity(String path) {
		return sqlStrToEntity(path, TOCAMEL);
	}

	/**
	 * 
	 * @Title: sqlStrToEntity @Description: sql语句转java实体 @param @param path,
	 *         isToCamel @param @return 设定文件 @return String 返回类型 @throws
	 */
	public static String sqlStrToEntity(String path, boolean isToCamel) {
		List<String> lines = lines(new File(path), ENCODE_GBK);
		int size = lines.size();
		if (size == 0) {
			return "";
		}

		String entityStr = "";
		String firstStr = lines.get(0);
		if (!firstStr.contains("TABLE")) {
			throw new RuntimeException("sql语句不正确");
		}

		for (int i = 1; i < size - 1; i++) {
			String string = lines.get(i).trim();
			if ("".equals(string)) {
				continue;
			}
			String[] split = string.split(" ");
			String filedName = split[0];
			System.out.println(filedName);
			filedName = filedName.substring(1, filedName.length() - 1);
			System.out.println(filedName);
			if (isToCamel) {
				filedName = StringTool.underlineToCamel(filedName);
			}

			String filedType = split[1];
			if (filedType.contains("(")) {
				filedType = filedType.substring(0, filedType.indexOf("("));
			}

			filedType = map.get(filedType);

			entityStr += "private " + filedType + " " + filedName + "; ";
			if (string.contains("COMMENT")) {
				String commentStr = split[split.length - 1].trim();
				commentStr = commentStr.substring(1, commentStr.length() - 2);
				entityStr += " //" + commentStr;
			}
			entityStr += "\n";
		}
		return entityStr;
	}

	/**
	 * 删除目录，并判断执行删除操作后目录是否存在
	 * 
	 * @param folder
	 * @return
	 * @Date 2017年7月24日15:04:14
	 */
	public static boolean deleteFolder(File folder) {
		return deleteFolderContents(folder) && folder.delete();
	}
	
	/**
	 * 
	 * @param folder
	 * @return
	 */
	public static boolean deleteFolderContents(File folder) {
		logger.debug("Deleting content of: " + folder.getAbsolutePath());
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				if (!file.delete()) {
					return false;
				}
			} else {
				if (!deleteFolder(file)) {
					return false;
				}
			}
		}
		return true;
	}
}
