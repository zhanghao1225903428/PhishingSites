package com.n410.addsites;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class movesites extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		session.removeAttribute("realurl");
		
		String  topath=request.getParameter("path");
		String  frompath=request.getServletContext().getRealPath("/hitwh");
		System.out.println("from:"+frompath);
		System.out.println("to:"+topath);
		String result="movefailed";
		if(MoveFolderAndFileWithSelf(frompath,topath))
			result="movesuccess";//System.out.println("�ƶ��ɹ�");
		else
			result="movefailed";//System.out.println("�ƶ�ʧ��");
		response.sendRedirect(request.getContextPath() + "/destory.jsp?movestate="+result);
		
	}

	
		public static boolean MoveFolderAndFileWithSelf(String from, String to) {
			try {
				File dir = new File(from);
				// Ŀ��
				to +=  File.separator + dir.getName();
				File moveDir = new File(to);
				if(dir.isDirectory()){
					if (!moveDir.exists()) {
						moveDir.mkdirs();
					}
				}else{
					File tofile = new File(to);
					dir.renameTo(tofile);
					return true;
				}
				
				//System.out.println("dir.isDirectory()"+dir.isDirectory());
				//System.out.println("dir.isFile():"+dir.isFile());
				
				// �ļ�һ��
				File[] files = dir.listFiles();
				if (files == null)
					return true;

				// �ļ��ƶ�
				for (int i = 0; i < files.length; i++) {
					System.out.println("�ļ�����"+files[i].getName());
					if (files[i].isDirectory()) {
						MoveFolderAndFileWithSelf(files[i].getPath(), to);
						// �ɹ���ɾ��ԭ�ļ�
						files[i].delete();
					}
					File moveFile = new File(moveDir.getPath() + File.separator + files[i].getName());
					// Ŀ���ļ����´��ڵĻ���ɾ��
					if (moveFile.exists()) {
						moveFile.delete();
					}
					files[i].renameTo(moveFile);
				}
				dir.delete();
			} catch (Exception e) {
				//throw e;
				return false;
			}
			return true;
		}
		
	    /**
	     * ���Ƶ����ļ�(�ɸ�������)
	     * @param oldPathFile ׼�����Ƶ��ļ�Դ
	     * @param newPathFile �������¾���·�����ļ���(ע��Ŀ¼·������ļ���)
	     * @return
	     */
	    public static void CopySingleFile(String oldPathFile, String newPathFile) {
	        try {
	            int bytesum = 0;
	            int byteread = 0;
	            File oldfile = new File(oldPathFile);
	            if (oldfile.exists()) { //�ļ�����ʱ
	                InputStream inStream = new FileInputStream(oldPathFile); //����ԭ�ļ�
	                FileOutputStream fs = new FileOutputStream(newPathFile);
	                byte[] buffer = new byte[1444];
	                while ((byteread = inStream.read(buffer)) != -1) {
	                    bytesum += byteread; //�ֽ��� �ļ���С
	                    //System.out.println(bytesum);
	                    fs.write(buffer, 0, byteread);
	                }
	                inStream.close();
	            }
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	    }

	    /**
	     * ���Ƶ����ļ�(ԭ������)
	     * @param oldPathFile ׼�����Ƶ��ļ�Դ
	     * @param newPathFile �������¾���·�����ļ���(ע��Ŀ¼·������ļ���)
	     * @return
	     */
	    public static void CopySingleFileTo(String oldPathFile, String targetPath) {
	        try {
	            int bytesum = 0;
	            int byteread = 0;
	            File oldfile = new File(oldPathFile);
	            String targetfile = targetPath + File.separator +  oldfile.getName();
	            if (oldfile.exists()) { //�ļ�����ʱ
	                InputStream inStream = new FileInputStream(oldPathFile); //����ԭ�ļ�
	                FileOutputStream fs = new FileOutputStream(targetfile);
	                byte[] buffer = new byte[1444];
	                while ((byteread = inStream.read(buffer)) != -1) {
	                    bytesum += byteread; //�ֽ��� �ļ���С
	                    //System.out.println(bytesum);
	                    fs.write(buffer, 0, byteread);
	                }
	                inStream.close();
	            }
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	    }

	    /**
	     * ���������ļ��е�����(������)
	     * @param oldPath ׼��������Ŀ¼
	     * @param newPath ָ������·������Ŀ¼
	     * @return
	     */
	    public static void copyFolderWithSelf(String oldPath, String newPath) {
	        try {
	            new File(newPath).mkdirs(); //����ļ��в����� �������ļ���
	            File dir = new File(oldPath);
				// Ŀ��
	            newPath +=  File.separator + dir.getName();
				File moveDir = new File(newPath);
				if(dir.isDirectory()){
					if (!moveDir.exists()) {
						moveDir.mkdirs();
					}
				}
	            String[] file = dir.list();
	            File temp = null;
	            for (int i = 0; i < file.length; i++) {
	                if (oldPath.endsWith(File.separator)) {
	                    temp = new File(oldPath + file[i]);
	                } else {
	                    temp = new File(oldPath + File.separator + file[i]);
	                }
	                if (temp.isFile()) {
	                    FileInputStream input = new FileInputStream(temp);
	                    FileOutputStream output = new FileOutputStream(newPath +
	                            "/" +
	                            (temp.getName()).toString());
	                    byte[] b = new byte[1024 * 5];
	                    int len;
	                    while ((len = input.read(b)) != -1) {
	                        output.write(b, 0, len);
	                    }
	                    output.flush();
	                    output.close();
	                    input.close();
	                }
	                if (temp.isDirectory()) { //��������ļ���
	                	copyFolderWithSelf(oldPath + "/" + file[i], newPath);
	                }
	            }
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	    }


		/**
		 * @param args
		 * @throws Exception 
		 */
		public static void main(String[] args) throws Exception {
			// TODO Auto-generated method stub
			movesites databean=new movesites();
			//File fl=new File("F:\\111\\222\\333\\444\\");
			//fl.mkdirs();//����ֱ�ӽ����༶Ŀ¼
			//databean.MoveFolderAndFileWithSelf("F:\\JBuilder 2006 ����J2EE������Ӧ�ó���.pdf","J:\\J\\");
			databean.MoveFolderAndFileWithSelf("F:\\MyWeb\\PhishingSites\\hitwh","F:");
			
			//databean.CopySingleFile("F:\\JBuilder 2006 ����J2EE������Ӧ�ó���.pdf","J:\\J\\JBuilder 2006 ����J2EE������Ӧ�ó���.pdf");
			//databean.copyFolderWithSelf("F:\\test","J:\\J\\www");
			System.out.println("���");
		}
	
	
}
