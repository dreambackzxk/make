package cn.dry.sqlserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class jdbc {

    public static void main(String[] args) {
    	try {  
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//��������
			System.out.println("���������ɹ���");
		try{ 
				String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=mydb";//��ַ+�˿ںţ����ݿ�����
				String user="sa";//��¼��
				String password="1234";//��½����
				Connection con=DriverManager.getConnection(url,user,password);  
				System.out.println("�������ݿ�ɹ���");
			}
		catch(SQLException e){
			System.out.print("SQL Server����ʧ�ܣ�");}
		}
	catch(Exception e){
		System.out.println("��������ʧ�ܣ�");}
    }

}