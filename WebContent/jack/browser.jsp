<%@ page language="java" contentType="text/html; charset=GB18030"
pageEncoding="GB18030"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %> 
<%@ page import ="java.sql.*" %>
<%@ page import="com.mysql.jdbc.Driver" %>
<html>
<head>
<meta charset="utf-8">
<title>���ռ�����Ϣ</title>

<%
  Statement st;
  Connection conn = null;
  //�������ݿ�������
   Class.forName("com.mysql.jdbc.Driver").newInstance();
   //���ݿ�����URL
   String url="jdbc:mysql://127.0.0.1:3306/jspmysql";
   //���ݿ��û���������
   String user="root";
   String  password="1234";
   //�������ݿ����ȡ��һ�����ݿ�����ֵ
   conn =  DriverManager.getConnection(url,user,password);
%>
<%
request.setCharacterEncoding("GBK");
    String browser = request.getParameter("browser");//��ȡ������汾��Ϣ
    //��ȡ�ͻ�����Ϣ
	String sql="INSERT INTO jspbrowser(type) VALUES('"+browser+"')";
  st = (Statement) conn.createStatement();    // ��������ִ�о�̬sql����Statement����   
  int count = st.executeUpdate(sql);  // ִ�в��������sql��䣬�����ز������ݵĸ���   
	conn.close();   //�ر����ݿ�����   
%>

</html>