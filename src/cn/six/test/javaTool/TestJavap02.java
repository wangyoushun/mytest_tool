package cn.six.test.javaTool;

public class TestJavap02 {

	public static void main(String[] args) {
		int a =1, b=1, c=1, d=1;
		
		a++;
		++b;
		
		c=c++;
		d=++d;
		System.out.println(a+", "+b+", "+c+", "+d);
	}
}

/*
 * Classfile /D:/code/eclipse_bailian/mytest/bin/cn/six/test/javaTool/TestJavap02.class
  Last modified 2015-12-13; size 973 bytes
  MD5 checksum d2be2a2c64a778d05780ba6bdbfefea9
  Compiled from "TestJavap02.java"
public class cn.six.test.javaTool.TestJavap02
  SourceFile: "TestJavap02.java"
  minor version: 0
  major version: 51
  flags: ACC_PUBLIC, ACC_SUPER

Constant pool:
   #1 = Class              #2             //  cn/six/test/javaTool/TestJavap02
   #2 = Utf8               cn/six/test/javaTool/TestJavap02
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
  #13 = Utf8               Lcn/six/test/javaTool/TestJavap02;
  #14 = Utf8               main
  #15 = Utf8               ([Ljava/lang/String;)V
  #16 = Fieldref           #17.#19        //  java/lang/System.out:Ljava/io/PrintStream;
  #17 = Class              #18            //  java/lang/System
  #18 = Utf8               java/lang/System
  #19 = NameAndType        #20:#21        //  out:Ljava/io/PrintStream;
  #20 = Utf8               out
  #21 = Utf8               Ljava/io/PrintStream;
  #22 = Class              #23            //  java/lang/StringBuilder
  #23 = Utf8               java/lang/StringBuilder
  #24 = Methodref          #25.#27        //  java/lang/String.valueOf:(I)Ljava/lang/String;
  #25 = Class              #26            //  java/lang/String
  #26 = Utf8               java/lang/String
  #27 = NameAndType        #28:#29        //  valueOf:(I)Ljava/lang/String;
  #28 = Utf8               valueOf
  #29 = Utf8               (I)Ljava/lang/String;
  #30 = Methodref          #22.#31        //  java/lang/StringBuilder."<init>":(Ljava/lang/String;)V
  #31 = NameAndType        #5:#32         //  "<init>":(Ljava/lang/String;)V
  #32 = Utf8               (Ljava/lang/String;)V
  #33 = String             #34            //  , 
  #34 = Utf8               , 
  #35 = Methodref          #22.#36        //  java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
  #36 = NameAndType        #37:#38        //  append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
  #37 = Utf8               append
  #38 = Utf8               (Ljava/lang/String;)Ljava/lang/StringBuilder;
  #39 = Methodref          #22.#40        //  java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
  #40 = NameAndType        #37:#41        //  append:(I)Ljava/lang/StringBuilder;
  #41 = Utf8               (I)Ljava/lang/StringBuilder;
  #42 = Methodref          #22.#43        //  java/lang/StringBuilder.toString:()Ljava/lang/String;
  #43 = NameAndType        #44:#45        //  toString:()Ljava/lang/String;
  #44 = Utf8               toString
  #45 = Utf8               ()Ljava/lang/String;
  #46 = Methodref          #47.#49        //  java/io/PrintStream.println:(Ljava/lang/String;)V
  #47 = Class              #48            //  java/io/PrintStream
  #48 = Utf8               java/io/PrintStream
  #49 = NameAndType        #50:#32        //  println:(Ljava/lang/String;)V
  #50 = Utf8               println
  #51 = Utf8               args
  #52 = Utf8               [Ljava/lang/String;
  #53 = Utf8               a
  #54 = Utf8               I
  #55 = Utf8               b
  #56 = Utf8               c
  #57 = Utf8               d
  #58 = Utf8               SourceFile
  #59 = Utf8               TestJavap02.java
{
  public cn.six.test.javaTool.TestJavap02();
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
               0       5     0  this   Lcn/six/test/javaTool/TestJavap02;

  public static void main(java.lang.String[]);
    flags: ACC_PUBLIC, ACC_STATIC

    Code:
      stack=4, locals=5, args_size=1
         0: iconst_1      
         1: istore_1      
         2: iconst_1      
         3: istore_2      
         4: iconst_1      
         5: istore_3      
         6: iconst_1      
         7: istore        4
         9: iinc          1, 1
        12: iinc          2, 1
        15: iload_3       
        16: iinc          3, 1
        19: istore_3      
        20: iinc          4, 1
        23: iload         4
        25: istore        4
        27: getstatic     #16                 // Field java/lang/System.out:Ljava/io/PrintStream;
        30: new           #22                 // class java/lang/StringBuilder
        33: dup           
        34: iload_1       
        35: invokestatic  #24                 // Method java/lang/String.valueOf:(I)Ljava/lang/String;
        38: invokespecial #30                 // Method java/lang/StringBuilder."<init>":(Ljava/lang/String;)V
        41: ldc           #33                 // String , 
        43: invokevirtual #35                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        46: iload_2       
        47: invokevirtual #39                 // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        50: ldc           #33                 // String , 
        52: invokevirtual #35                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        55: iload_3       
        56: invokevirtual #39                 // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        59: ldc           #33                 // String , 
        61: invokevirtual #35                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        64: iload         4
        66: invokevirtual #39                 // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        69: invokevirtual #42                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
        72: invokevirtual #46                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        75: return        
      LineNumberTable:
        line 6: 0
        line 8: 9
        line 9: 12
        line 11: 15
        line 12: 20
        line 13: 27
        line 14: 75
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
               0      76     0  args   [Ljava/lang/String;
               2      74     1     a   I
               4      72     2     b   I
               6      70     3     c   I
               9      67     4     d   I
}

 * 
 * */
