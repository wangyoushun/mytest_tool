package cn.six.test;

public class Test_clone {

	public static void main(String[] args) throws CloneNotSupportedException {
		Bean bean = new Bean();
		ColoneBean coloneBean = new ColoneBean();
		coloneBean.name="111";
		ColoneBean clone = coloneBean.clone();
		System.out.println(clone.name);
		
	}
}

class ColoneBean implements Cloneable{
	public String name;
	public int a;
	
	 public ColoneBean clone() throws CloneNotSupportedException {  
		 return (ColoneBean) super.clone();
	 }
	
}