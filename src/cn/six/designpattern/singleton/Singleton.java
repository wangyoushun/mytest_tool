package cn.six.designpattern.singleton;

public class Singleton {
		
	    private static Singleton singleton=null;
		//懒汉模式(调用的时候才去创建)
		private static synchronized Singleton getInstance()
		{
			if(singleton==null)
			{
				singleton=new Singleton();
//				return new Singleton();
			}
			return singleton;
		}
		//饿汉模式(初始化的时候就去创建)
		/*private static Singleton singleton = new Singleton();
		@SuppressWarnings("unused")
		private static Singleton getInstance()
		{
			return singleton;
		}*/
	
		public static void main(String[] args) {
			
			Singleton s1 = Singleton.getInstance();
			Singleton s2 = Singleton.getInstance();
			System.out.println(s1==s2);
			
			new Thread(new Runnable(){

				@Override
				public void run() {
//					while(true)
//					{
						System.out.println(Singleton.getInstance());
//					}
					
				}
				
			}).start();
			new Thread(new Runnable(){

				@Override
				public void run() {
//					while(true)
//					{
						System.out.println(Singleton.getInstance());
//					}
				}
			}).start();
			
		}
		
}
