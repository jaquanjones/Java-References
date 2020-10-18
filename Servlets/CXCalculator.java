package lab13.controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab13.model.CXResult;

@WebServlet("/CXCalculator")
public class CXCalculator extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CXCalculator() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/CXForm.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String from = (String) request.getParameter("from");
        String to = (String) request.getParameter("to");
        String amount = (String) request.getParameter("amount");

        if (from == null || from.trim().length() == 0 || to == null || to.trim().length() == 0 || amount == null || amount.trim().length() == 0) {
            response.sendRedirect("CXCalculator");
            return;
        }

        CXResult cxRes = new CXResult(from, to, amount);
        String result = (String) cxRes.convert();
        request.setAttribute("from", from);
        request.setAttribute("to", to);
        request.setAttribute("amount", amount);
        request.setAttribute("result", result);
        request.getRequestDispatcher("/WEB-INF/views/CXForm.jsp").forward(request, response);


    }

}
