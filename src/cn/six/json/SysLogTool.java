package cn.six.json;

import java.io.File;

import cn.six.utils.DateUtil;
import cn.six.utils.FileTool;
import cn.six.utils.StringTool;

/**
 * 
 * @ClassName: SysLogTool
 * @Description: 封装system输出日志
 * @author iwantfly
 * @date 2017年7月16日 下午11:06:22
 *
 */
public class SysLogTool {
	private boolean consolelog = false;
	private  String filePath;

	public SysLogTool() {
	}

	public SysLogTool(boolean consolelog) {
		this(consolelog,null);
	}

	public SysLogTool(boolean consolelog, String filePath) {
		super();
		this.consolelog = consolelog;
		this.filePath = filePath;
	}

	public void log(Object obj) {
		if (consolelog) {
			System.out.println(obj);
		}
	}

	/**
	 * 打印输出信息到文件中
	 * @param str
	 */
	public void toFile(String str) {
		if (!StringTool.isEmpty(filePath) && !StringTool.isEmpty(str)) {
			boolean writeAppend = FileTool.writeAppend(new File(filePath), DateUtil.DateTime()+":  "+str);
			if(!writeAppend){
				System.err.println("eerrr");
			}
		}
	}
}
