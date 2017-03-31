package cn.six.utils;

import java.io.File;

/**
 * 批量删除Maven下载失败的文件夹
 */
public class CleanMvn {
	public static void main(String[] args) {
//		String path = "D://programfile//apache-maven-3.3.9-bin//reposity2";
		String path = "D:/programfile/apache-maven-3.3.9-bin/";
		System.out.println("start====");
		findAndDelete(new File(path));
		System.out.println("end====");
	}

	public static boolean findAndDelete(File file) {
		if (!file.exists()) {
		} else if (file.isFile()) {
			if (file.getName().endsWith("lastUpdated")) {
				deleteFile(file.getParentFile());
				return true;
			}
		} else if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files) {
				if (findAndDelete(f)) {
					break;
				}
			}
		}
		return false;
	}

	public static void deleteFile(File file) {
		if (!file.exists()) {
		} else if (file.isFile()) {
			print("删除文件:" + file.getAbsolutePath());
			file.delete();
		} else if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files) {
				deleteFile(f);
			}
			print("删除文件夹:" + file.getAbsolutePath());
			print("====================================");
			file.delete();
		}
	}

	public static void print(String msg) {
		System.out.println(msg);
	}
}
