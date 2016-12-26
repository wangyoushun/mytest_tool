package cn.six.thread;

public class Thread02 {

	public static void main(String[] args) throws Exception {
		
		final Detail detail = new Detail();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
				detail.incretment();
				}
				
			}
		}).start();
		Thread.sleep(1000);
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
				detail.dectment();
				}
			}
		}).start();
		
	}
	
	
}

class Detail{
	private int j=1;
	public synchronized void incretment(){
			j++;
			System.out.println("生产--"+j);	
	}
	
	public synchronized void dectment(){
		j--;
		System.out.println("消费--"+j);	
	}
}
