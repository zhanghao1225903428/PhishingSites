package com.n410.filter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class StaticResponse extends HttpServletResponseWrapper {
	private PrintWriter pw;
	
	/**
	 * String path：html文件路径！
	 * @param response
	 * @param path
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	public StaticResponse(HttpServletResponse response, String path) 
			throws FileNotFoundException, UnsupportedEncodingException {
		super(response);
		
		// 创建一个与html文件路径在一起的流对象
		pw = new PrintWriter(path, "utf-8");
	}

	public PrintWriter getWriter() {
		// 返回一个与html绑定在一起的printWriter对象
		// jsp会使用它进行输出，这样数据都输出到html文件了。
		return pw;
	}
}
