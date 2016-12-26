package cn.six.thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.six.thread.cyclicBarrier.MainTask;

/**
 * 测试多线程遍历
 */
public class ThreadFor {
	
	private final static int inintSize=1000000;
 
	public static void main(String[] args) {
		List<Email> list = init(inintSize);
		long start = System.currentTimeMillis();
		/*	p(list);
		System.out.println(System.currentTimeMillis()-start);
		*/
		ExecutorService executor = Executors.newSingleThreadExecutor();
		CyclicBarrier barrier = new CyclicBarrier(1, new MainTask(start));
		EmailThread t1 = new EmailThread(list, barrier);
		executor.execute(t1);
		executor.shutdown();
	}
	
	public static void main3(String[] args) {
		List<Email> list1 = init(inintSize/4);
		List<Email> list2 = init(inintSize/4);
		List<Email> list3 = init(inintSize/4);
		List<Email> list4 = init(inintSize/4);
		
		long start = System.currentTimeMillis();
		ExecutorService executorService = Executors.newCachedThreadPool();
		CyclicBarrier barrier = new CyclicBarrier(4, new MainTask(start));
		EmailThread t1 = new EmailThread(list1,barrier);
		EmailThread t2 = new EmailThread(list2,barrier);
		EmailThread t3 = new EmailThread(list3,barrier);
		EmailThread t4 = new EmailThread(list4,barrier);
		executorService.execute(t1);
		executorService.execute(t2);
		executorService.execute(t3);
		executorService.execute(t4);
		
		executorService.shutdown();
		
	}
	

	public static List<Email> init(int size){
		List<Email> emailList = new ArrayList<Email>();
		for(int i=0; i<size; i++){
			Email email = new Email(i+"");
			emailList.add(email);
		}
		return emailList;
	}
	
	
	
    public static void main2(String[] args){
        //Email是一个实体类，包含username password两个属性
    	
        LinkedList<Email> emailList = new LinkedList<Email>();//假设list中有1W条数据
        for(int i=0;i<100000;i++){
            emailList.add(new Email(String.valueOf(i)));
        }
        
        fortime(emailList);
        
        for (int i=0;i<4;i++) {
              new Thread(new Run(emailList)).start();
        }
    }

	private static void fortime(LinkedList<Email> emailList) {
		long start = System.currentTimeMillis();
        for(int i=0; i<emailList.size(); i++){
        	Email email = emailList.get(i);
        	email.setUsername("main"+i);
        	
        //	 System.out.println(Thread.currentThread().getName()+"         email--"+email.getUserName()); 
        }
        long end = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName()+"--"+(end-start));
	}
}  
    class Email {
        String username;
        public Email(){}
        public Email(String username){
            this.username=username;
        }
        public String getUserName(){
            return username;
        }
		public void setUsername(String username) {
			this.username = username;
		}
        
    }
    class Run implements Runnable{
        LinkedList<Email> emailList;
        public Run(LinkedList<Email> emailList){
           this.emailList=emailList;
        }
     
            @Override 
        public void run() { 
            long start = System.currentTimeMillis();
            int cnt = 0;
            Email email=null;
            while(true){
                synchronized (emailList) { 
                    if(!emailList.isEmpty()){
                        email=emailList.removeFirst();
                        email.setUsername(Thread.currentThread().getName()+email.getUserName());
                        cnt++;
                    }
                    else{
                    	  break;
                    }
                }  
           //     System.out.println(Thread.currentThread().getName()+"         email--"+email.getUserName());              
            }
            long end = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName()+"--"+(end-start));
            System.out.println(Thread.currentThread().getName()+"-##-"+(cnt++));
        }
    }
    
    class EmailThread implements Runnable{

    	private List<Email> list;
    	private CyclicBarrier cb;
    	
		public EmailThread(List<Email> list,CyclicBarrier cb) {
			super();
			this.list = list;
			this.cb = cb;
		}

		@Override
		public void run() {
			for (Email e : list) {
				String userName = e.getUserName();
				userName += "str";
				e.setUsername(userName);
				
				for(int j=0; j<10000; j++){
					userName = e.getUserName();
					e.setUsername(userName);
				}
				System.out.println(e);
			}
			try {
				cb.await();
			} catch (InterruptedException | BrokenBarrierException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
    	
    }

