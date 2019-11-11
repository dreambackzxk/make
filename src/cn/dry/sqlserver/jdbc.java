package cn.dry.sqlserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class jdbc {

    public static void main(String[] args) {
    	try {  
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//加载驱动
			System.out.println("加载驱动成功！");
		try{ 
				String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=mydb";//地址+端口号，数据库名字
				String user="sa";//登录名
				String password="1234";//登陆密码
				Connection con=DriverManager.getConnection(url,user,password);  
				System.out.println("连接数据库成功！");
			}
		catch(SQLException e){
			System.out.print("SQL Server连接失败！");}
		}
	catch(Exception e){
		System.out.println("加载驱动失败！");}
    }

}