package cn.six.mystring;

import java.util.HashMap;

public class Zipper {

	public static void main(String[] args) {
		String str="aabcccccaaa";
		zip2(str);
	}

	public static void zip2(String str) {
		char[] charArray = str.toCharArray();
		
		int count=0;
		char c;
	//	String str2="";
		StringBuilder str2 = new StringBuilder();
		
		for(int i=1, length=charArray.length; i<length; i++){
			boolean flag = (charArray[i]==charArray[i-1]);
			
			if(flag){
				count++;
			}
			
			if(!flag || i==length-1){
				c=charArray[i-1];
				str2.append(c).append(count+1);
			//	System.out.println(str2);
				count=0;
			}
		}
		
		
		if(str2.toString().length()<str.length()){
			System.out.println(str2.toString());
		}else{
			System.out.println(str);
		}
	}

	public static void zip1(String str) {
		char[] charArray = str.toCharArray();
		HashMap<Character,Integer> hashMap = new HashMap<Character,Integer>();
		Integer integer;
		
		for (char c : charArray) {
			if(null == hashMap.get(c)){
				hashMap.put(c, 0);
			}else{
				integer = hashMap.get(c)+1;
				hashMap.put(c, integer);
			}
		}
		
		System.out.println(hashMap);
	}
}
