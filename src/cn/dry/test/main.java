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
        //声明Connection对象
        Connection con;
        //驱动程序名
        String driver = "com.mysql.cj.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://localhost:3306/sqltestdb?useSSL=true&serverTimezone=GMT";
        //MySQL配置时的用户名
        String user = "root";
        //MySQL配置时的密码
        String password = "1111";
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            //con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sqltestdb?useSSL=true&serverTimezone=GMT", "root", "1234");
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            String name;
            String num; 
            PreparedStatement psql;
            ResultSet res;
            
            /*//预处理添加数据，其中有两个参数--“？”
            psql = con.prepareStatement("insert into emp (empno,ename,job,hiredate,sal) "
                    + "values(?,?,?,?,?)");
            psql.setInt(1, 5490);              //设置参数1，创建id为5490的数据
            psql.setString(2, "李四");      //设置参数2，name 为李四
            psql.setString(3, "员工"); 
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate2 = dateFormat2.parse("2019-09-13");
            psql.setDate(4,new java.sql.Date(myDate2.getTime()));
            psql.setFloat(5, (float) 7000.6);
            psql.executeUpdate();           //执行更新*/
            
            //预处理更新（修改）数据，将李四的sal改为5001.0
            psql = con.prepareStatement("update emp set sal = ? where ename = ?");
            psql.setFloat(1,(float) 5011.0);      
            psql.setString(2,"李四");             
            psql.executeUpdate();
            
            /*//预处理删除数据
            psql = con.prepareStatement("delete from emp where sal > ?");
            psql.setFloat(1, 4500);
            psql.executeUpdate();
            psql.close();*/
            
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句
            String sql = "select * from emp";
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("-----------------");
            System.out.println("执行结果如下所示:");  
            System.out.println("-----------------");  
            System.out.println("姓名" + "\t" + "职称" + "\t" + "工资");  
            System.out.println("-----------------");  
             
            String job = null;
            String id = null;
            String sal=null;
            while(rs.next()){
                //获取stuname这列数据
                job = rs.getString("job");
                //获取stuid这列数据
                id = rs.getString("ename");
                
                sal=rs.getString("sal");

                //输出结果
                System.out.println(id + "\t" + job + "\t" + sal);
            }
            rs.close();
            con.close();
        } catch(ClassNotFoundException e) {   
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");   
            e.printStackTrace();   
            } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();  
            }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            System.out.println("数据库数据成功获取！！");
        }
    }

}