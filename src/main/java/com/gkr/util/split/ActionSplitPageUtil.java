package com.gkr.util.split;

import javax.servlet.http.HttpServletRequest;

/**
 * 处理分页请求时页面的参数
 * @author Nate
 *
 */
public class ActionSplitPageUtil {
	private long currentPage = 1;//当前默认第一页	
	private int lineSize = 5;//每页默认五条数据；
	private String colunm;//模糊查询的列名
	private String keyword;//模糊查询关键字
	private HttpServletRequest request;
	
	public long getCurrentPage() {
		return currentPage;
	}

	public int getLineSize() {
		return lineSize;
	}

	public String getColunm() {
		return colunm;
	}

	public String getKeyword() {
		return keyword;
	}

	
	public ActionSplitPageUtil(HttpServletRequest request,String columnDate,String url) {
		request.setAttribute("columnDate", columnDate);
		request.setAttribute("url", url);
		this.request = request;
		handleParameter();
	}

	private void handleParameter() {
		try {
			this.currentPage = Long.parseLong(this.request.getParameter("cp"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			this.lineSize = Integer.parseInt(this.request.getParameter("cp"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.colunm = this.request.getParameter("col");
		this.keyword = this.request.getParameter("kw");
		
		this.request.setAttribute("currentPage", currentPage);
		this.request.setAttribute("lineSize", lineSize);
		this.request.setAttribute("colunm", colunm);
		this.request.setAttribute("keyword", keyword);
		
	}
}
