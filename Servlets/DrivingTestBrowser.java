package lab15.controller;

import lab15.model.Question;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DrivingTestBrowser")
public class DrivingTestBrowser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DrivingTestBrowser() {
        super();
    }

    public void init() throws ServletException {
        ServletContext context = getServletContext();
        List<Question> questions = new ArrayList<>();

        try {
            Scanner in = new Scanner(new File(context.getRealPath("/WEB-INF/DrivingTest.txt")));
            while (in.hasNextLine()) {
                String desc = in.nextLine();
                String ansA = in.nextLine();
                String ansB = in.nextLine();
                String ansC = in.nextLine();
                int corr = Integer.parseInt(in.nextLine());

//                questions.add(new Question(in.nextLine(), in.nextLine(), in.nextLine(), in.nextLine(), Integer.parseInt(in.nextLine())));
                questions.add(new Question(desc, ansA, ansB, ansC, corr));
                in.nextLine();
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        context.setAttribute("questions", questions);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String index = (String) request.getParameter("index");
        int currentIndex = 0;

        List<Question> questions = (List<Question>) getServletContext().getAttribute("questions");

        if (index != null) {
            currentIndex = Integer.parseInt(index);
        }
        if (currentIndex >= questions.size()) {
            currentIndex = 0;
        }

        request.setAttribute("currentQuestion", questions.get(currentIndex));
        request.setAttribute("index", currentIndex);
        request.getRequestDispatcher("/WEB-INF/Question.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
