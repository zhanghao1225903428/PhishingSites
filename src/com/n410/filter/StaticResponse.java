package com.n410.filter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class StaticResponse extends HttpServletResponseWrapper {
	private PrintWriter pw;
	
	/**
	 * String path��html�ļ�·����
	 * @param response
	 * @param path
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	public StaticResponse(HttpServletResponse response, String path) 
			throws FileNotFoundException, UnsupportedEncodingException {
		super(response);
		
		// ����һ����html�ļ�·����һ���������
		pw = new PrintWriter(path, "utf-8");
	}

	public PrintWriter getWriter() {
		// ����һ����html����һ���printWriter����
		// jsp��ʹ��������������������ݶ������html�ļ��ˡ�
		return pw;
	}
}
