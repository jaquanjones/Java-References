package lab13.controller;
import lab13.model.CXResult;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = "/CXCalculator", loadOnStartup =1)
public class CXCalculator extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CXCalculator() {
        super();
    }
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/CXForm.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String from = (String) request.getParameter("from");
        String to = (String) request.getParameter("to");
        String amount = (String) request.getParameter("amount");

//        if (from == null || from.trim().length() == 0 || to == null || to.trim().length() == 0 || amount == null || amount.trim().length() == 0) {
//            response.sendRedirect("CXCalculator");
//            return;
//        }

        CXResult cxRes = new CXResult(from.trim(), to.trim(), amount.trim());
        String result = (String) cxRes.convert(from, to, amount);
        request.setAttribute("from", from);
        request.setAttribute("to", to);
        request.setAttribute("amount", amount);
        getServletContext().setAttribute("result", result);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/CXResult.jsp");
        dispatcher.forward(request, response);


    }

}
