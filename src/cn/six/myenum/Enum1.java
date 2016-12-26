package cn.six.myenum;

/**
 * 枚举类
 * @author 有顺
 *
 */
public enum Enum1 {
	PLUS("+"),MINUS("-"),TIMES("*"),DIVIDE("/");  //枚举类的实例

	private String name;

	private Enum1(String name) {
		this.name = name;
	}
	
	public void print(){
		System.out.println(this.name);
	}
	
	public double eval(double a,double b){
		switch (this) {
		case PLUS:
			return a+b;
		case MINUS:
			return a-b;
		case TIMES:
			return a*b;
		case DIVIDE:
			return a/b;
		default:
			return 0;
		}
		
	}
	
	public static void main(String[] args) {
		Enum1.PLUS.print();
		double eval = Enum1.MINUS.eval(22, 3);
		System.out.println(eval);
	}
}
