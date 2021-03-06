package cn.six.thread;

public class Thread01 {
	
	public static void main(String[] args) throws Exception {
		Resource r = new Resource();
		Producer producer = new Producer(r);
		coustmer coustmer = new coustmer(r);
		Thread t1 = new Thread(producer);
		Thread t2 = new Thread(coustmer);
		t1.start();
		Thread.sleep(10);
		t2.start();
		
	}
	
}

class Resource{
	private int i=1;
	
	public void set(){
		synchronized (this) {
		i++;
		System.out.println(Thread.currentThread().getName()+"--生产--"+i);
		}
	}
	
	public void out(){
		synchronized (this) {		
		i--;
		System.out.println(Thread.currentThread().getName()+"--消费--"+i);
		}
	}
	
	
}

class Producer implements Runnable {
	private Resource r;
	public Producer(Resource r){
		this.r = r;
	}
	@Override
	public void run() {
		while(true){
			r.set();
		}
	}
	
	
}

class coustmer implements Runnable{
	private Resource r;
	public coustmer(Resource r){
		this.r = r;
	}
	@Override
	public void run() {
		while(true){
			r.out();
		}
	}
	
	
}





//package cn.six.thread;

/*
多线程中最为常见的应用案例：
生产者消费者问题。
生产和消费同时执行，需要多线程。
但是执行的任务却不相同，处理的资源确实相同的：线程间的通信。

1，描述一下资源。
2，描述生产者，因为具备着自己的任务。
3，描述消费者，因为具备着自己的任务。


问题1：数据错误：已经被生产很早期的商品，才被消费到。
出现线程安全问题，加入了同步解决。使用同步函数。
问题已解决：不会在消费到之前很早期的商品。

问题2：发现了连续生产却没有消费，同时对同一个商品进行多次消费。
希望的结果应该是生产一个商品，就被消费掉。生产下一个商品。
搞清楚几个问题？
生产者什么时候生产呢？消费者什么时候应该消费呢？
当盘子中没有面包时，就生产，如果有了面包，就不要生产。
当盘子中已有面包时，就消费，如果没有面包，就不要消费。



*/

//1,描述资源。属性：商品名称和编号，  行为：对商品名称赋值，获取商品。
/*class Resource
{
	private String name;
	private int count = 1;
	
	//1,提供设置的方法。
	public  void set(String name)
	{
		//给成员变量赋值并加上编号。
		this.name = name + count;
		//编号自增。
		count++;
		//打印生产了哪个商品。
		System.out.println(Thread.currentThread().getName()+"......生产者...."+this.name);
	}
	public  void out()
	{
		System.out.println(Thread.currentThread().getName()+"....消费者...."+this.name);
	}
}

//2,描述生产者。
class Producer implements Runnable
{
	private Resource r ;
	// 生产者一初始化就要有资源，需要将资源传递到构造函数中。
	Producer(Resource r)
	{
		this.r = r;
	}
	public void run()
	{
		while(true)
		{
			r.set("面包");
		}
	}
}

//3,描述消费者。
class Consumer implements Runnable
{
	private Resource r ;
	// 消费者一初始化就要有资源，需要将资源传递到构造函数中。
	Consumer(Resource r)
	{
		this.r = r;
	}
	public void run()
	{
		while(true)
		{
			r.out();
		}
	}
}




class Thread01
{
	public static void main(String[] args) 
	{
		//1,创建资源对象。
		Resource r = new Resource();

		//2,创建线程任务。
		Producer pro = new Producer(r);
		Consumer con = new Consumer(r);

		//3,创建线程。
		Thread t1 = new Thread(pro);
		Thread t2 = new Thread(con);

		t1.start();
		t2.start();
	}
}
*/