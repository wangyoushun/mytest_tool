package cn.six.json;

/**
 * 
* @ClassName: SysLogTool 
* @Description: 封装system输出日志
* @author iwantfly
* @date 2017年7月16日 下午11:06:22 
*
 */
public class SysLogTool {
	private boolean consolelog=false;
	
	public SysLogTool(){}

	public SysLogTool(boolean consolelog) {
		super();
		this.consolelog = consolelog;
	}
	
	public void log(Object obj){
		if(consolelog){
			System.out.println(obj);
		}
	}
}
