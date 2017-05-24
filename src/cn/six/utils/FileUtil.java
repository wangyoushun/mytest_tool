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
import java.util.List;

public class FileUtil {
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
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(file), encoding))) {
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
			if(file2.exists() && file2.getName().equals(file.getName())){
				System.out.println(file.getName()+"====exist");
				return;
			}
			System.out.println("==file==" + file.getName() + "----"
					+ file.getAbsolutePath());
			fileChannelCopy(file, new File(destPath));
		}
	}

}
