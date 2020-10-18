package webtest.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();

		// create some test data for display
		List<UserAccounts> userAccounts = new ArrayList<>();
		userAccounts.add(new UserAccounts("cguo", "123456"));
		userAccounts.add(new UserAccounts("jjones", "456789"));

		// store data in application scope
		getServletContext().setAttribute("accounts", userAccounts);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println(
				"<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><title>Lab 12</title></head><body>");
		out.println("<form action=\"Login\" method=\"POST\">");
		out.println("Username: <input type=\"text\" name=\"username\"><br><br>");
		out.println("Password: <input type=\"password\" name=\"password\" required><br><br>");
		out.println("<input type=\"submit\" value=\"Login\">");
		out.println("</form></body></html>");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// get the data
		ServletContext context = getServletContext();
		ArrayList<UserAccounts> userAccounts = (ArrayList<UserAccounts>) context.getAttribute("accounts");

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		for (int i = 0; i < userAccounts.size(); i++) {
			UserAccounts user = userAccounts.get(i);
			if ((username == user.getUsername()) && (password == user.getPassword())) {
				request.getSession().setAttribute("name", username);
				response.sendRedirect("Members");
			}
		}
		response.sendRedirect("Login");
	}

}
