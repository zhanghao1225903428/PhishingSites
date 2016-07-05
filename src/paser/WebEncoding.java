package paser;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebEncoding {
	private String findCharset(String line) 
	{   
		if(line.contains("Content-type")||line.contains("text/html"))
    	{
    		System.out.println(line);
    	} 
		    Pattern pattern=Pattern.compile("<meta\\s+http-equiv=\"*[^\"]*?\"*\\s+content=\"[^\"]*?charset=(\\S+?)\"\\s*/*>");
	        Matcher matcher=pattern.matcher(line);
	        if(matcher.find())
	        {
	            System.out.println("网页编码格式为："+matcher.group(1));
	            return matcher.group(1);
	        }
	        return null;
	}    
	
	public String getCharset(String link) {  
	      String result = null;    
	      HttpURLConnection conn = null;  
	      try {    
	          URL url = new URL(link);    
	          conn = (HttpURLConnection)url.openConnection();    
	         // conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");    
	          conn.connect();    
	          String contentType = conn.getContentType();    
	          //在header里面找charset    
	         //result = findCharset(contentType);     
	         //如果没找到的话，则一行一行的读入页面的html代码，从html代码中寻找    
	         if(result == null)
	         {    
	             BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));    
	             String line = reader.readLine();    
	             while(line != null) 
	             {    
	                  
	            	// if(line.contains("Content-type")||line.contains("text/html"))
	             /*	{
	             		System.out.print(line);
	             	} */
	                	
	                     result = findCharset(line);    
	                   //  break;    
	                     if(result==null)
	                     {
	                       line = reader.readLine();
	                     }
	                     else {
							break;
						}
	             }    
	         }    
	     } catch (Exception e) {    
	         // TODO Auto-generated catch block    
	         e.printStackTrace();    
	     } finally {  
	       conn.disconnect();  
	     }  
	     return result;    
	 }    
	
	/*public  static void main(String [] args)
	{
		String content="<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>\n" +
                "<meta content=\"求助 java获 html网页编码字段 正则表达式 Java Web 开发\" name=\"Keywords\"/>...\n";
        Pattern pattern=Pattern.compile("<meta\\s+http-equiv=\"Content-Type\"\\s+content=\"[\\s\\S]*?charset=(\\S+?)\"/>");
        Matcher matcher=pattern.matcher(content);
        if(matcher.find()){
            System.out.println(matcher.group(1));
        }
		System.err.println(new WebEncoding().getCharset("http://www.njtech.edu.cn/"));
	}
	*/
}
