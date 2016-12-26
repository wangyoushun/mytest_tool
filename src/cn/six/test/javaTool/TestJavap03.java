package cn.six.test.javaTool;

public class TestJavap03 {

	public static void main(String[] args) {
		String str = "abc"+"123";
		String str2 = "345";
		String str3 = "678";
		System.out.println(str);
		System.out.println(str2+str3);
	}
}

/*
 *Classfile /D:/code/eclipse_bailian/mytest/bin/cn/six/test/javaTool/TestJavap03.class
  Last modified 2015-12-13; size 943 bytes
  MD5 checksum 0a5ef37697ab15b76f80d12cc5f3540f
  Compiled from "TestJavap03.java"
public class cn.six.test.javaTool.TestJavap03
  SourceFile: "TestJavap03.java"
  minor version: 0
  major version: 51
  flags: ACC_PUBLIC, ACC_SUPER

Constant pool:
   #1 = Class              #2             //  cn/six/test/javaTool/TestJavap03
   #2 = Utf8               cn/six/test/javaTool/TestJavap03
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
  #13 = Utf8               Lcn/six/test/javaTool/TestJavap03;
  #14 = Utf8               main
  #15 = Utf8               ([Ljava/lang/String;)V
  #16 = String             #17            //  abc123
  #17 = Utf8               abc123
  #18 = String             #19            //  345
  #19 = Utf8               345
  #20 = String             #21            //  678
  #21 = Utf8               678
  #22 = Fieldref           #23.#25        //  java/lang/System.out:Ljava/io/PrintStream;
  #23 = Class              #24            //  java/lang/System
  #24 = Utf8               java/lang/System
  #25 = NameAndType        #26:#27        //  out:Ljava/io/PrintStream;
  #26 = Utf8               out
  #27 = Utf8               Ljava/io/PrintStream;
  #28 = Methodref          #29.#31        //  java/io/PrintStream.println:(Ljava/lang/String;)V
  #29 = Class              #30            //  java/io/PrintStream
  #30 = Utf8               java/io/PrintStream
  #31 = NameAndType        #32:#33        //  println:(Ljava/lang/String;)V
  #32 = Utf8               println
  #33 = Utf8               (Ljava/lang/String;)V
  #34 = Class              #35            //  java/lang/StringBuilder
  #35 = Utf8               java/lang/StringBuilder
  #36 = Methodref          #37.#39        //  java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
  #37 = Class              #38            //  java/lang/String
  #38 = Utf8               java/lang/String
  #39 = NameAndType        #40:#41        //  valueOf:(Ljava/lang/Object;)Ljava/lang/String;
  #40 = Utf8               valueOf
  #41 = Utf8               (Ljava/lang/Object;)Ljava/lang/String;
  #42 = Methodref          #34.#43        //  java/lang/StringBuilder."<init>":(Ljava/lang/String;)V
  #43 = NameAndType        #5:#33         //  "<init>":(Ljava/lang/String;)V
  #44 = Methodref          #34.#45        //  java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
  #45 = NameAndType        #46:#47        //  append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
  #46 = Utf8               append
  #47 = Utf8               (Ljava/lang/String;)Ljava/lang/StringBuilder;
  #48 = Methodref          #34.#49        //  java/lang/StringBuilder.toString:()Ljava/lang/String;
  #49 = NameAndType        #50:#51        //  toString:()Ljava/lang/String;
  #50 = Utf8               toString
  #51 = Utf8               ()Ljava/lang/String;
  #52 = Utf8               args
  #53 = Utf8               [Ljava/lang/String;
  #54 = Utf8               str
  #55 = Utf8               Ljava/lang/String;
  #56 = Utf8               str2
  #57 = Utf8               str3
  #58 = Utf8               SourceFile
  #59 = Utf8               TestJavap03.java
{
  public cn.six.test.javaTool.TestJavap03();
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
               0       5     0  this   Lcn/six/test/javaTool/TestJavap03;

  public static void main(java.lang.String[]);
    flags: ACC_PUBLIC, ACC_STATIC

    Code:
      stack=4, locals=4, args_size=1
         0: ldc           #16                 // String abc123
         2: astore_1      
         3: ldc           #18                 // String 345
         5: astore_2      
         6: ldc           #20                 // String 678
         8: astore_3      
         9: getstatic     #22                 // Field java/lang/System.out:Ljava/io/PrintStream;
        12: aload_1       
        13: invokevirtual #28                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        16: getstatic     #22                 // Field java/lang/System.out:Ljava/io/PrintStream;
        19: new           #34                 // class java/lang/StringBuilder
        22: dup           
        23: aload_2       
        24: invokestatic  #36                 // Method java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        27: invokespecial #42                 // Method java/lang/StringBuilder."<init>":(Ljava/lang/String;)V
        30: aload_3       
        31: invokevirtual #44                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        34: invokevirtual #48                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
        37: invokevirtual #28                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        40: return        
      LineNumberTable:
        line 6: 0
        line 7: 3
        line 8: 6
        line 9: 9
        line 10: 16
        line 11: 40
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
               0      41     0  args   [Ljava/lang/String;
               3      38     1   str   Ljava/lang/String;
               6      35     2  str2   Ljava/lang/String;
               9      32     3  str3   Ljava/lang/String;
}
 
 * */
 