package webtest.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Members")
public class Members extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Members() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String name = (String) request.getSession().getAttribute("name");

		if (request.getSession().getAttribute("name") != null) {
			out.println(
					"<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><title>Lab 12</title></head><body>");
			out.println("<form action=\"Members\" method=\"post\">");
			out.println(
					"<h3 style=\"margin-left: 40px;\">Hi, </h3>" + name + " <h3>. Welcome to the Members Area</h3>");
			out.println("<p style=\"margin-left: 40px;\"><a href=\"Logout\">Logout</a></p>");
			out.println("<button type=\"submit\"style=\"margin-left: 40px;border: none; background: none;\">"
					+ "<a href=\"#\">Logout</a></button>");
			out.println("</form></body></html>");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect("Login");
	}

}
