package com.n410.filter;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StaticFilter implements Filter {
	private FilterConfig config;
	public void destroy() {}
	public void init(FilterConfig fConfig) throws ServletException {
		this.config = fConfig;
	}
	
	public void doFilter(ServletRequest request, 
			ServletResponse response, FilterChain chain) 
					throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		/*
		 * 1. 第一次访问时，查找请求对应的html页面是否存在，如果存在重定向到html
		 * 2. 如果不存在，放行！把servlet访问数据库后，输出给客户端的数据保存到一个html文件中
		 *   再重定向到html
		 */
		/*
		 * 一、获取category参数！
		 * category有四种可能：
		 * * null --> null.html
		 * * 1 --> 1.html
		 * * 2 --> 2.html
		 * * 3 --> 3.html
		 * 
		 * html页面的保存路径, htmls目录下
		 * 
		 * 判断对应的html文件是否存在，如果存在，直接重定向！
		 */
		String category = request.getParameter("category");
		String htmlPage = category + ".html";//得到对应的文件名称
		String htmlPath = config.getServletContext().getRealPath("/htmls");//得到文件的存放目录
		File destFile = new File(htmlPath, htmlPage);
		
		if(destFile.exists()) {//如果文件存在
			// 重定向到这个文件
			res.sendRedirect(req.getContextPath() + "/htmls/" + htmlPage);
			return;
		}
		
		/*
		 * 二、如果html文件不存在，我们要生成html
		 * 1. 放行，show.jsp会做出很多的输出，我们要让它别再输出给客户端，而是输出到我们指定的一个html文件中
		 * 完成：
		 * * 调包response，让它的getWriter()与一个html文件绑定，那么show.jsp的输出就到了html文件中
		 */
		StaticResponse sr = new StaticResponse(res, destFile.getAbsolutePath());
		chain.doFilter(request, sr);//放行，即生成了html文件		
		// 这时页面已经存在，重定向到html文件
		res.sendRedirect(req.getContextPath() + "/htmls/" + htmlPage);
	}
}