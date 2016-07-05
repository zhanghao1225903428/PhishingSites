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
       System.out.println("����");
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
		///��ȡ�ÿ�IP��Ϣ
        //String Remoteip=request.getRemoteAddr();		
		String requestUrl=request.getRequestURL().toString();		
		System.out.println(requestUrl);
				
		HttpSession session=request.getSession();
		String realurl=(String) session.getAttribute("realurl");		
		System.out.println("��ʵurl"+realurl);		
		String referer = request.getHeader("Referer");  
        System.out.println(referer);         
        String htmlPath="";
		if(realurl!=null&&referer!=null)//!requestUrl.contains("destory.jsp")
		{
			if(requestUrl.contains("http://localhost/PhishingSites/hitwh/"))
			{
				System.out.println("------����3��Ŀ¼/-------");
			    requestUrl=requestUrl.replace("http://localhost/PhishingSites/hitwh/", realurl);	
			    htmlPath = config.getServletContext().getRealPath("/hitwh/");//�õ��ļ��Ĵ��Ŀ¼		
			}
			else 
				{
					if(requestUrl.contains("http://localhost/hitwh/"))
					{
					   System.out.println("------��2��Ŀ¼/-------");
					   requestUrl=requestUrl.replace("http://localhost/hitwh/", realurl);	
					   htmlPath = config.getServletContext().getRealPath("/hitwh/");//�õ��ļ��Ĵ��Ŀ¼		
					}
					else if(requestUrl.contains("http://localhost/"))
					{
						 System.out.println("------��1��Ŀ¼/-------");
						 requestUrl=requestUrl.replace("http://localhost/", realurl);
						 htmlPath = config.getServletContext().getRealPath("/");//�õ��ļ��Ĵ��Ŀ¼		
					}
				}
			// htmlPath = config.getServletContext().getRealPath("/hitwh/");//�õ��ļ��Ĵ��Ŀ¼		
		    System.out.println(htmlPath);
			String[] sourceStrArray = requestUrl.split("/");
			for(int i=3;i<sourceStrArray.length-1;i++)
			{
				htmlPath=htmlPath+"\\"+sourceStrArray[i];
			}
			String filename=sourceStrArray[sourceStrArray.length-1];	
			if(requestUrl.contains(".js"))//||requestUrl.contains(".css")
			{
					//�����ı�����
				    System.out.println("---���ص�js--:"+requestUrl); 
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
			   System.out.println("---���ص�css--:"+requestUrl);
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
				System.out.println("---���ص�jpg--:"+requestUrl);
				String geshi=requestUrl.substring(requestUrl.length()-3,requestUrl.length());
				try {    
			           URL url = new URL(requestUrl);    
			           HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
			           conn.setRequestMethod("GET");    
			           conn.setConnectTimeout(5 * 1000);    
			           InputStream inStream = conn.getInputStream();//ͨ����������ȡͼƬ����    
			           byte data[] = new parser().readStream(inStream);  
			           if(new Filewrite(htmlPath, filename," ").WriteImage(data))
			        	   System.out.println("ͼƬ����ɹ�");
			           else
			        	   System.out.println("ͼƬ���ʧ��");
			           
			           inStream.read(data);  //������     
			           inStream.close();     		           
			           response.setContentType("image/"+geshi); //���÷��ص��ļ�����     
			           OutputStream os = response.getOutputStream();    
			           os.write(data);    
			           os.flush();    
			           os.close(); 
			           
			         //  return ;		
			       } catch (Exception e) {    
			           e.printStackTrace();    
			       }   
			  }
			//����HTML
			if(requestUrl.contains(".html")&&!requestUrl.contains("index.html"))
			{
			   System.out.println("---���ص�html--:"+requestUrl);
			   String temp=new parser().Panrser(requestUrl); 
			   response.setContentType( "text/html;charset=utf-8" ) ;
			   PrintWriter out = response.getWriter() ;
			   out.write( temp) ;		
			   out.close();
			   //return ;	
			}
		}		
		System.out.println("---���ص�--:"+requestUrl);
		chain.doFilter(req, response);
		System.out.println("---����--:"+requestUrl);
	}
	

}
