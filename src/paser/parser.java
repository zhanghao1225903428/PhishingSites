package paser;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class parser {
	
	public String Panrser(String baseurl)
	{
	      String pageContent="";
		  String charset=new WebEncoding().getCharset(baseurl);
		  if(charset==null)
		  {
			  charset="utf-8";
		  }
	      System.out.println(charset+""+baseurl);
	      //String charset="utf-8";	  
		  try {		 
		  URL url;		
		  url = new URL(baseurl);	
      // showArrayList.add("��ʼ�������ӣ�"+baseurl);
      // this.repaint();
       HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
       httpURLConnection.setConnectTimeout(2500);
       httpURLConnection.setReadTimeout(50000);                
      //���ַ�������
      BufferedReader  reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),charset));
      //ÿ���ַ���
      String content = "";
   //  System.out.println("��ʼ��");
  //   int cout=0;
      //��ʼ��ȡҳ������
      while((content = reader.readLine()) != null) 
      {
    	//  cout++;
    	//  System.out.println(cout);
    	  pageContent+=content+"\n";
      }
      
      reader.close();
	} catch (IOException e) 
		  {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
		  return pageContent;     
	}
	
	public byte[] readStream(InputStream inStream) 
	{  
        ByteArrayOutputStream bops = new ByteArrayOutputStream();  
        int data = -1;  
        try {  
            while((data = inStream.read()) != -1){  
                bops.write(data);  
            }  
            return bops.toByteArray();  
        }catch(Exception e){  
            return null;  
        }  
    }  
	
	/*public static void main(String[] args) 
	  {
		double time1=System.currentTimeMillis();
		String temp=new parser().Panrser("http://www.163.com/");
		System.out.println(temp);	
		double time2=System.currentTimeMillis()-time1;
		System.out.println("��ʱ��"+time2);
	 }*/
}
