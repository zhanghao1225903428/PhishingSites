package com.n410.addsites;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.n410.FileOutput.Filewrite;

import paser.parser;

public class addserlvet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		String  realrequestUrl=request.getParameter("url");
		String  requestUrl=request.getParameter("url");
		if(!requestUrl.endsWith("/"))
		{			
			requestUrl=requestUrl+"/";
		}
		
		 Matcher slashMatcher = Pattern.compile("/").matcher(requestUrl);
		    int mIdx = 0;
		    while(slashMatcher.find()) {
		       mIdx++;
		       //当"#"符号第二次出现的位置
		       if(mIdx == 3){
		          break;
		       }
		    }
		int endIndex=slashMatcher.start()+1;
		requestUrl=requestUrl.substring(0, endIndex);
		System.out.println(requestUrl);
		
		HttpSession session=request.getSession();
		session.setAttribute("realurl",requestUrl);
		String  path=request.getServletContext().getRealPath("/hitwh");		
	    System.out.println(path);	    
	    String temp=new parser().Panrser(realrequestUrl);
	    String jack="<script language=\"JavaScript\" src=\"index.js\"></script><link href=\"Noname1.css\" type=text/css rel=stylesheet><div id=\"float_banner\">"+
	    "<div class=\"login\" onmouseover=\"display()\" onmouseout=\"disappear()\" style='position:relative;height:30px;width:50px;left:1000px;'><a class=\"ml20\" href=\"#\"  style=\"color:white\" id=\"click_test\">登陆</a> </div>";
	   // temp=temp+jack;	    
	    new Filewrite(path,"index.html", temp).writetodisk();
	    System.out.println(request.getContextPath() + "/hitwh/index.html");
	    response.sendRedirect(request.getContextPath() + "/hitwh/index.html");
	}

	
}
