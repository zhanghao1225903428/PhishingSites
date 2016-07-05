package com.n410.filter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.n410.FileOutput.Filewrite;

import paser.parser;

public class firstFilter implements Filter {
	private FilterConfig config;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
       System.out.println("销毁");
	}
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.config = fConfig;
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain)
			throws IOException, ServletException
	{	
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) rep;		
		///获取访客IP信息
        //String Remoteip=request.getRemoteAddr();		
		String requestUrl=request.getRequestURL().toString();		
		System.out.println(requestUrl);
				
		HttpSession session=request.getSession();
		String realurl=(String) session.getAttribute("realurl");		
		System.out.println("真实url"+realurl);		
		String referer = request.getHeader("Referer");  
        System.out.println(referer);         
        String htmlPath="";
		if(realurl!=null&&referer!=null)//!requestUrl.contains("destory.jsp")
		{
			if(requestUrl.contains("http://localhost/PhishingSites/hitwh/"))
			{
				System.out.println("------不含3级目录/-------");
			    requestUrl=requestUrl.replace("http://localhost/PhishingSites/hitwh/", realurl);	
			    htmlPath = config.getServletContext().getRealPath("/hitwh/");//得到文件的存放目录		
			}
			else 
				{
					if(requestUrl.contains("http://localhost/hitwh/"))
					{
					   System.out.println("------含2级目录/-------");
					   requestUrl=requestUrl.replace("http://localhost/hitwh/", realurl);	
					   htmlPath = config.getServletContext().getRealPath("/hitwh/");//得到文件的存放目录		
					}
					else if(requestUrl.contains("http://localhost/"))
					{
						 System.out.println("------含1级目录/-------");
						 requestUrl=requestUrl.replace("http://localhost/", realurl);
						 htmlPath = config.getServletContext().getRealPath("/");//得到文件的存放目录		
					}
				}
			// htmlPath = config.getServletContext().getRealPath("/hitwh/");//得到文件的存放目录		
		    System.out.println(htmlPath);
			String[] sourceStrArray = requestUrl.split("/");
			for(int i=3;i<sourceStrArray.length-1;i++)
			{
				htmlPath=htmlPath+"\\"+sourceStrArray[i];
			}
			String filename=sourceStrArray[sourceStrArray.length-1];	
			if(requestUrl.contains(".js"))//||requestUrl.contains(".css")
			{
					//解析文本内容
				    System.out.println("---拦截到js--:"+requestUrl); 
				    String temp=new parser().Panrser(requestUrl);				   
					response.setContentType( "text/javascript;charset=utf-8" ) ;		   
					PrintWriter out = response.getWriter() ;
					out.write( temp) ;		
					out.close();
					System.out.println(htmlPath+"\\"+filename);
					new Filewrite(htmlPath,filename, temp).writetodisk();
					//return ;	
					
			}
				
				
			if(requestUrl.contains(".css"))//||requestUrl.contains(".css")
			{
			   System.out.println("---拦截到css--:"+requestUrl);
			   String temp=new parser().Panrser(requestUrl);
			   response.setContentType( "text/css;charset=utf-8" ) ;
			   PrintWriter out = response.getWriter() ;
			   out.write( temp) ;		
			   out.close();
			   System.out.println(htmlPath+"\\"+filename);
			   new Filewrite(htmlPath,filename, temp).writetodisk();
			  // return ;			
			}
			
			if(requestUrl.contains(".png")||requestUrl.contains(".jpg")||requestUrl.contains(".gif")||requestUrl.contains(".ico"))
			{
				System.out.println("---拦截到jpg--:"+requestUrl);
				String geshi=requestUrl.substring(requestUrl.length()-3,requestUrl.length());
				try {    
			           URL url = new URL(requestUrl);    
			           HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
			           conn.setRequestMethod("GET");    
			           conn.setConnectTimeout(5 * 1000);    
			           InputStream inStream = conn.getInputStream();//通过输入流获取图片数据    
			           byte data[] = new parser().readStream(inStream);  
			           if(new Filewrite(htmlPath, filename," ").WriteImage(data))
			        	   System.out.println("图片输出成功");
			           else
			        	   System.out.println("图片输出失败");
			           
			           inStream.read(data);  //读数据     
			           inStream.close();     		           
			           response.setContentType("image/"+geshi); //设置返回的文件类型     
			           OutputStream os = response.getOutputStream();    
			           os.write(data);    
			           os.flush();    
			           os.close(); 
			           
			         //  return ;		
			       } catch (Exception e) {    
			           e.printStackTrace();    
			       }   
			  }
			//拦截HTML
			if(requestUrl.contains(".html")&&!requestUrl.contains("index.html"))
			{
			   System.out.println("---拦截到html--:"+requestUrl);
			   String temp=new parser().Panrser(requestUrl); 
			   response.setContentType( "text/html;charset=utf-8" ) ;
			   PrintWriter out = response.getWriter() ;
			   out.write( temp) ;		
			   out.close();
			   //return ;	
			}
		}		
		System.out.println("---拦截到--:"+requestUrl);
		chain.doFilter(req, response);
		System.out.println("---放行--:"+requestUrl);
	}
	

}
