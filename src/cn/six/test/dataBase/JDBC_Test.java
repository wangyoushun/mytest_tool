package cn.six.test.dataBase;

import java.sql.* ;    
    
public class JDBC_Test {    
    //orcl为oracle数据库中的数据库名，localhost表示连接本机的oracle数据库     
   //1521为连接的端口号     
//    private static String url="jdbc:oracle:thin:@172.31.2.161:1521:ACCOUNT";    
    private static String url="jdbc:oracle:thin:@192.168.1.102:1521:ORCL";    
    //system为登陆oracle数据库的用户名     
    private static String user="mu_fare";    
    //manager为用户名system的密码     
    private static String password="mu_fare";    
    public static Connection conn;    
    public static PreparedStatement ps;    
    public static ResultSet rs;    
    public static Statement st ;    
    //连接数据库的方法     
    public void getConnection(){    
        try {    
            //初始化驱动包     
            Class.forName("oracle.jdbc.driver.OracleDriver");    
            //根据数据库连接字符，名称，密码给conn赋值     
            conn=DriverManager.getConnection(url, user, password);    
                
        } catch (Exception e) {    
            // TODO: handle exception     
            e.printStackTrace();    
        }    
    }    
     //测试能否与oracle数据库连接成功     
     public static void main(String[] args) throws SQLException {    
        JDBC_Test basedao=new JDBC_Test();    
        basedao.getConnection();    
        if(conn==null){    
            System.out.println("与oracle数据库连接失败！");    
        }else{    
            System.out.println("与oracle数据库连接成功！");    
            query();
        }    
     } 
     
     //query
     public static void query() throws SQLException{
    	conn=getConnection1();
    	String sql = "select * from ap_user";
    	Statement createStatement = conn.createStatement();
    	ResultSet rs = createStatement.executeQuery(sql);
    	while (rs.next()) {
    		String name = rs.getString("dept_name"); 
    		System.out.println(name);
		}
    	 
     }
     
     public static Connection getConnection1(){    
         try {    
             //初始化驱动包     
             Class.forName("oracle.jdbc.driver.OracleDriver");    
             //根据数据库连接字符，名称，密码给conn赋值     
             conn=DriverManager.getConnection(url, user, password);    
                 
         } catch (Exception e) {    
             // TODO: handle exception     
             e.printStackTrace();    
         }
         return conn;
     }
} 