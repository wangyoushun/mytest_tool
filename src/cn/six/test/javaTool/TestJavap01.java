package cn.six.test.javaTool;

import org.junit.Test;

public class TestJavap01 {

	public static void main(String[] args) {
		int i = 8;
		System.out.println(i);
		
	}
	
	@Test
	public void test2(){
		int a=1;
		int r1=(a++)+(++a)+(++a);
//		int r1=a++ + ++a + ++a;
		System.out.println("1:"+r1);
		System.out.println("2:"+a);
		a++;
		System.out.println("3:"+a);
		int r2 = a/2+a%2;
		System.out.println(r1+", "+r2);
	}
}

/*
 * Classfile /D:/code/eclipse_bailian/mytest/bin/cn/six/test/javaTool/TestJavap01.class
  Last modified 2015-12-13; size 568 bytes
  MD5 checksum 9bd67454d684eb62e61167b2b6903259
  Compiled from "TestJavap01.java"
public class cn.six.test.javaTool.TestJavap01
  SourceFile: "TestJavap01.java"
  minor version: 0
  major version: 51
  flags: ACC_PUBLIC, ACC_SUPER

Constant pool:
   #1 = Class              #2             //  cn/six/test/javaTool/TestJavap01
   #2 = Utf8               cn/six/test/javaTool/TestJavap01
   #3 = Class              #4             //  java/lang/Object
   #4 = Utf8               java/lang/Object
   #5 = Utf8               <init>
   #6 = Utf8               ()V
   #7 = Utf8               Code
   #8 = Methodref          #3.#9          //  java/lang/Object."<init>":()V
   #9 = NameAndType        #5:#6          //  "<init>":()V
  #10 = Utf8               LineNumberTable
  #11 = Utf8               LocalVariableTable
  #12 = Utf8               this
  #13 = Utf8               Lcn/six/test/javaTool/TestJavap01;
  #14 = Utf8               main
  #15 = Utf8               ([Ljava/lang/String;)V
  #16 = Fieldref           #17.#19        //  java/lang/System.out:Ljava/io/PrintStream;
  #17 = Class              #18            //  java/lang/System
  #18 = Utf8               java/lang/System
  #19 = NameAndType        #20:#21        //  out:Ljava/io/PrintStream;
  #20 = Utf8               out
  #21 = Utf8               Ljava/io/PrintStream;
  #22 = Methodref          #23.#25        //  java/io/PrintStream.println:(I)V
  #23 = Class              #24            //  java/io/PrintStream
  #24 = Utf8               java/io/PrintStream
  #25 = NameAndType        #26:#27        //  println:(I)V
  #26 = Utf8               println
  #27 = Utf8               (I)V
  #28 = Utf8               args
  #29 = Utf8               [Ljava/lang/String;
  #30 = Utf8               i
  #31 = Utf8               I
  #32 = Utf8               SourceFile
  #33 = Utf8               TestJavap01.java
{
  public cn.six.test.javaTool.TestJavap01();
    flags: ACC_PUBLIC

    Code:
      stack=1, locals=1, args_size=1
         0: aload_0       
         1: invokespecial #8                  // Method java/lang/Object."<init>":()V
         4: return        
      LineNumberTable:
        line 3: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
               0       5     0  this   Lcn/six/test/javaTool/TestJavap01;

  public static void main(java.lang.String[]);
    flags: ACC_PUBLIC, ACC_STATIC

    Code:
      stack=2, locals=2, args_size=1
         0: bipush        8
         2: istore_1      
         3: getstatic     #16                 // Field java/lang/System.out:Ljava/io/PrintStream;
         6: iload_1       
         7: invokevirtual #22                 // Method java/io/PrintStream.println:(I)V
        10: return        
      LineNumberTable:
        line 6: 0
        line 7: 3
        line 8: 10
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
               0      11     0  args   [Ljava/lang/String;
               3       8     1     i   I
}

 * 
 * 
 * */
