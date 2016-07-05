package com.n410.FileOutput;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;   

public class Filewrite
{   
    private String  path;
    private String  content;
    private String  filename;
    public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Filewrite(String path,String filename,String content) {   
        this.path=path;
        this.content=content;
        this.filename=filename;
    }   
    
	public void writetodisk()
	{
		//FileWriter fw = null;  
     //   int count=1000;//д�ļ�����   
		OutputStreamWriter writer=null;
        try
        {   
           if (!(new File(this.path).isDirectory()))
           {
            new File(this.path).mkdirs();
           }
            	  
            String  temp=this.path+"\\"+this.filename;
         //   fw = new FileWriter(temp);  
            long begin3 = System.currentTimeMillis();   
            FileOutputStream fos = new FileOutputStream(temp);
             writer = new OutputStreamWriter(fos, "UTF-8");
            writer.write(this.content); 
          //  fw.write(this.content);  

          //  fw.close(); 
            long end3 = System.currentTimeMillis(); 
            System.out.println("FileWriterִ�к�ʱ:" + (end3 - begin3) + " ����");   

        } catch (Exception e) {   

            e.printStackTrace();   

        }   

        finally {   

            try {   
            	writer.close();
           //     fw.close();   

            } catch (Exception e) { 
                System.out.println(e.toString());
                e.printStackTrace();   

            }   

        }   
		
	}
	
	public boolean WriteImage(byte[] data) 
	{
		  
        //���������  
        FileOutputStream outStream;
		try {
			
			if (!(new File(this.path).isDirectory()))
			 {
				System.out.println(this.path);
	     	    new File(this.path).mkdirs();
	     	  }		
			 String  temp=this.path+"/"+this.filename;
	        //newһ���ļ�������������ͼƬ��Ĭ�ϱ��浱ǰ���̸�Ŀ¼  
	        File imageFile = new File(temp); 
			outStream = new FileOutputStream(imageFile);
			//д������  
	        outStream.write(data);  
	        //�ر������  
	        outStream.close(); 	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}  
        	return true;
	}
	public static byte[] readInputStream(InputStream inStream) throws Exception{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        //����һ��Buffer�ַ���  
        byte[] buffer = new byte[1024];  
        //ÿ�ζ�ȡ���ַ������ȣ����Ϊ-1������ȫ����ȡ���  
        int len = 0;  
        //ʹ��һ����������buffer������ݶ�ȡ����  
        while( (len=inStream.read(buffer)) != -1 ){  
            //���������buffer��д�����ݣ��м����������ĸ�λ�ÿ�ʼ����len�����ȡ�ĳ���  
            outStream.write(buffer, 0, len);  
        }  
        //�ر�������  
        inStream.close();  
        //��outStream�������д���ڴ�  
        return outStream.toByteArray();  
    } 
	//test
    public static void main(String[] args) throws Exception
    {   
    	//String path="C:\Users\admin\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\PhishingSites\hitwh\image\jquery-1.5.2.min.js";
    	//new Filewrite("C:/apppp", "add2.txt","����java �ļ�����\r\n").writetodisk();
   	     URL url = new URL("http://www.pku.edu.cn/images/content/2016-05/20160519130255395454.jpg");  
         //������  
         HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
         //��������ʽΪ"GET"  
         conn.setRequestMethod("GET");  
         //��ʱ��Ӧʱ��Ϊ5��  
         conn.setConnectTimeout(5 * 1000);  
         //ͨ����������ȡͼƬ����  
         InputStream inStream = conn.getInputStream();  
    	 //newһ��URL����           
         //�õ�ͼƬ�Ķ��������ݣ��Զ����Ʒ�װ�õ����ݣ�����ͨ����  
         byte[] data = readInputStream(inStream);  
         //"C:/Users/admin/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/PhishingSites/hitwh/images/content/2016-05"
        if( new Filewrite("C:/content/2016-05", "20160519130255395454.jpg","����java �ļ�����\r\n").WriteImage(data))
        {	System.out.println("ͼƬ����ɹ�");}
        else
        {
        	System.out.println("ͼƬ���ʧ��");
        }
        	
    }   

}  