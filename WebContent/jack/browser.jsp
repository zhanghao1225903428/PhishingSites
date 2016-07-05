<%@ page language="java" contentType="text/html; charset=GB18030"
pageEncoding="GB18030"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %> 
<%@ page import ="java.sql.*" %>
<%@ page import="com.mysql.jdbc.Driver" %>
<html>
<head>
<meta charset="utf-8">
<title>接收键盘信息</title>

<%
  Statement st;
  Connection conn = null;
  //加载数据库驱动类
   Class.forName("com.mysql.jdbc.Driver").newInstance();
   //数据库连接URL
   String url="jdbc:mysql://127.0.0.1:3306/jspmysql";
   //数据库用户名和密码
   String user="root";
   String  password="1234";
   //根据数据库参数取得一个数据库连接值
   conn =  DriverManager.getConnection(url,user,password);
%>
<%
request.setCharacterEncoding("GBK");
    String browser = request.getParameter("browser");//获取浏览器版本信息
    //获取客户端信息
	String sql="INSERT INTO jspbrowser(type) VALUES('"+browser+"')";
  st = (Statement) conn.createStatement();    // 创建用于执行静态sql语句的Statement对象   
  int count = st.executeUpdate(sql);  // 执行插入操作的sql语句，并返回插入数据的个数   
	conn.close();   //关闭数据库连接   
%>

</html>