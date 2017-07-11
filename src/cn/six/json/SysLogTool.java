package cn.six.json;

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
