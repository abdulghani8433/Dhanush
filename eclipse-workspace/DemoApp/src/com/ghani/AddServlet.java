package com.ghani;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class AddServlet extends HttpServlet
{
public void service(HttpServletRequest req, HttpServlet res) {
int i=Integer.parseInt(req.getParameter("num1"));
int j=Integer.parseInt(res.getInitParameter("num2"));
int k=i+j;
System.out.println("retuslt is"+k);

	
}
}

