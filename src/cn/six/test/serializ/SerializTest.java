package cn.six.test.serializ;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


/**
 * 测试jdk自带的序列化  序列化成list，obj
 * @author 有顺
 *
 */
public class SerializTest {
	
	
	//protostuff 测试
	@Test
	public void proWrite() throws Exception {
		List<SeriModel> list = new ArrayList<SeriModel>();
		for(int i=0; i<100; i++){
			SeriModel seriModel = new SeriModel("a"+i,i+"");
			list.add(seriModel);
		}
		byte[] serializeList = ProtoStuffSerializerUtil.serializeList(list);
		System.out.println(serializeList.length);
		FileOutputStream fos = new FileOutputStream("D:/serObj.txt");
		fos.write(serializeList);
		fos.close();
	}
	
	@Test
	public void proRead() throws Exception {
		FileInputStream inputStream = new FileInputStream("D:/serObj.txt");
		byte[] serializeList=new byte[1024*1024];
		int read = inputStream.read(serializeList);
		List<SeriModel> deserializeList = ProtoStuffSerializerUtil.deserializeList(serializeList, SeriModel.class);
		inputStream.close();
		System.out.println(deserializeList);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void writeObj() throws Exception {
		ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(new File("D:/serObj.txt")));
		SeriModel seriModel = new SeriModel("a", "1");
		stream.writeObject(seriModel);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void readObj() throws Exception {
		ObjectInputStream stream = new ObjectInputStream(new FileInputStream(new File("D:/serObj.txt")));
		SeriModel seriModel = (SeriModel) stream.readObject();
		System.out.println(seriModel);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void writeList() throws Exception {
		ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(new File("D:/serObj.txt")));
		List<SeriModel> list = new ArrayList<SeriModel>();
		for(int i=0; i<100; i++){
			SeriModel seriModel = new SeriModel("a"+i,i+"");
			list.add(seriModel);
		}
		stream.writeObject(list);
	}
	
	@SuppressWarnings({ "resource", "unchecked" })
	@Test
	public void readList() throws Exception {
		ObjectInputStream stream = new ObjectInputStream(new FileInputStream(new File("D:/serObj.txt")));
		List<SeriModel> list =  (List<SeriModel>) stream.readObject();
		System.out.println(list.size());
		for (SeriModel seriModel : list) {
			System.out.println(seriModel);
		}
		
	}

}

class SeriModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String value;
	private static String agg;
	
	
	
	public static String getAgg() {
		return agg;
	}
	public static void setAgg(String agg) {
		SeriModel.agg = agg;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public SeriModel(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
	@Override
	public String toString() {
		return "SeriModel [name=" + name + ", value=" + value +",agg="+agg+ "]";
	}
	
}
