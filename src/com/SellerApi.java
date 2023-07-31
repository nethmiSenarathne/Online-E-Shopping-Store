package com;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@WebServlet("/SellerApi")
public class SellerApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  Seller sellerObj = new Seller();
  
    public SellerApi() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = sellerObj.insertItem
				(request.getParameter("FirstName"),
				request.getParameter("LastName"),
				request.getParameter("ContactNumber"),
				request.getParameter("Email"),
				request.getParameter("CompanyName"));
				response.getWriter().write(output);
	}

	private static Map getParasMap(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
		
		try
		{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			
			String queryString = scanner.hasNext() ?
			scanner.useDelimiter("\\A").next() : "";
			
			scanner.close();
			
			String[] params = queryString.split("&");
			
			for (String param : params)
			{
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		}
		catch (Exception e)
		{
		}
			return map;
		}
	
	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		
		String output = sellerObj.updateItem(paras.get("hidItemIDSave").toString(),
		paras.get("FirstName").toString(),
		paras.get("LastName").toString(),
		paras.get("ContactNumber").toString(),
		paras.get("Email").toString(),
		paras.get("CompanyName").toString());

		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		
		String output = sellerObj.deleteItem(paras.get("SellersId").toString());
		
		response.getWriter().write(output);
	}

}
