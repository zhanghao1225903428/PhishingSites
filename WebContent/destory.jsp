<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'destory.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
<% 
if(request!=null)
{
String  result=request.getParameter("movestate");
if(result!=null)
{
	if(result.equals("movefailed"))
	{
		%>
		<script type="text/javascript">
		 alert("保存失败")
		 </script>
		<%
	}
	else if(result.equals("movesuccess"))
	{
		%>
		 <script type="text/javascript">
		 alert("保存成功")
		 </script>
		<%
	}
}
}
%>
  </head>
  
  <body>
  <%
 	 session.removeAttribute("realurl");
  %>
    完成克隆：<br/>
    注入监听脚本: <button onclick="location.href='<%=request.getContextPath()%>/init.jsp';">监听</button>   <br/>
    <hr style=" borber:1px blue solid; " />
     <form action="movesites" method="post">
         提取网站目录<br/>
         保存路径：<input type="text" name="path">  
  <input type="submit" value="提取">
  </form>
  </body>
</html>
