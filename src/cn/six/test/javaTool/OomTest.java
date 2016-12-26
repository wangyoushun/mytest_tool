package cn.six.test.javaTool;

import java.util.ArrayList;
import java.util.List;
/**
 * 测试oom异常
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * @author Administrator
 *
 */
public class OomTest {
	 static class OOMObject{
	    }

	    public static void main(String[] args){
	        List<OOMObject> list=new ArrayList<OOMObject>();
	        while(true){
	            list.add(new OOMObject());
	        }
	    }
}
