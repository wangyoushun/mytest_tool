package cn.six.thread;

public class ThreadA {  
    public static void main(String[] args) {  
        ThreadB b = new ThreadB();  
        //启动计算线程  
        b.start();  
        //线程 A 拥有 b 对象上的锁。线程为了调用 wait()或 notify()方法，该线程必须是那个
//对象锁的拥有者 
        synchronized (b) {  
            try {  
                System.out.println("等待对象 b 完成计算。。。");  
                //当前线程 A 等待  
                b.wait();  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
            System.out.println("b 对象计算的总和是：" + b.total);  
        }  
    }  
} 
