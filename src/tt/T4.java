package tt;

public class T4 {

	private static T4 t3;

	public T4(){
		System.out.println("1111");
	}
	
	public T4(int i){
		P();
	}

	public void P() {
		System.out.println("2222");
		
	}
	
	public static void main(String[] args) {
/*		t3 = new T4(1);
		t3.mytestM();
		int t = 3;
*/		
		for(;;){
			System.out.println("1");
		}
		
		
	}
	
	public int mytestM(){
		return 1;
	}
	
	
}
