package cn.dry.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Date;
import java.text.SimpleDateFormat;

public class main {

    public static void main(String[] args) {
        //����Connection����
        Connection con;
        //����������
        String driver = "com.mysql.cj.jdbc.Driver";
        //URLָ��Ҫ���ʵ����ݿ���mydata
        String url = "jdbc:mysql://localhost:3306/sqltestdb?useSSL=true&serverTimezone=GMT";
        //MySQL����ʱ���û���
        String user = "root";
        //MySQL����ʱ������
        String password = "1111";
        //������ѯ�����
        try {
            //������������
            Class.forName(driver);
            //1.getConnection()����������MySQL���ݿ⣡��
            con = DriverManager.getConnection(url,user,password);
            //con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sqltestdb?useSSL=true&serverTimezone=GMT", "root", "1234");
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            String name;
            String num; 
            PreparedStatement psql;
            ResultSet res;
            
            /*//Ԥ����������ݣ���������������--������
            psql = con.prepareStatement("insert into emp (empno,ename,job,hiredate,sal) "
                    + "values(?,?,?,?,?)");
            psql.setInt(1, 5490);              //���ò���1������idΪ5490������
            psql.setString(2, "����");      //���ò���2��name Ϊ����
            psql.setString(3, "Ա��"); 
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate2 = dateFormat2.parse("2019-09-13");
            psql.setDate(4,new java.sql.Date(myDate2.getTime()));
            psql.setFloat(5, (float) 7000.6);
            psql.executeUpdate();           //ִ�и���*/
            
            //Ԥ������£��޸ģ����ݣ������ĵ�sal��Ϊ5001.0
            psql = con.prepareStatement("update emp set sal = ? where ename = ?");
            psql.setFloat(1,(float) 5011.0);      
            psql.setString(2,"����");             
            psql.executeUpdate();
            
            /*//Ԥ����ɾ������
            psql = con.prepareStatement("delete from emp where sal > ?");
            psql.setFloat(1, 4500);
            psql.executeUpdate();
            psql.close();*/
            
            //2.����statement���������ִ��SQL��䣡��
            Statement statement = con.createStatement();
            //Ҫִ�е�SQL���
            String sql = "select * from emp";
            //3.ResultSet�࣬������Ż�ȡ�Ľ��������
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("-----------------");
            System.out.println("ִ�н��������ʾ:");  
            System.out.println("-----------------");  
            System.out.println("����" + "\t" + "ְ��" + "\t" + "����");  
            System.out.println("-----------------");  
             
            String job = null;
            String id = null;
            String sal=null;
            while(rs.next()){
                //��ȡstuname��������
                job = rs.getString("job");
                //��ȡstuid��������
                id = rs.getString("ename");
                
                sal=rs.getString("sal");

                //������
                System.out.println(id + "\t" + job + "\t" + sal);
            }
            rs.close();
            con.close();
        } catch(ClassNotFoundException e) {   
            //���ݿ��������쳣����
            System.out.println("Sorry,can`t find the Driver!");   
            e.printStackTrace();   
            } catch(SQLException e) {
            //���ݿ�����ʧ���쳣����
            e.printStackTrace();  
            }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            System.out.println("���ݿ����ݳɹ���ȡ����");
        }
    }

}