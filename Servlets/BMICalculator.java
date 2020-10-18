package webtest.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/BMICalculator")
public class BMICalculator extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BMICalculator() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	response.setContentType("text/html");
    	PrintWriter out = response.getWriter();

    	out.println("<!DOCTYPE html><html lang=\"en\"><head><title>CS3220 Lab 10</title></head><body>");
    	out.println("<form action='BMICalculator' method='post'>");
		out.println("Please enter your height: <input type='text' name='ft' /> feet and ");
		out.println("<input type='text' name='in'/> inches <br><br> Please enter your weight: ");
		out.println("<input type='text' name='wt' /> pounds<br><br>");
		out.println("");
		out.println("<input type='submit' value='Calculate BMI' />");
		out.println("</form></body></html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int ft = Integer.parseInt(request.getParameter("ft"));
		int in = Integer.parseInt(request.getParameter("in"));
		int wt = Integer.parseInt(request.getParameter("wt"));
		int height = (ft*12) + in;
		double bmi = (wt / Math.pow(height, 2)) * 703;
		NumberFormat formatter = new DecimalFormat("#00.0");
		String bmiType;
		String feedback = ". You are ";

		if (bmi < 18.5) {
			bmiType = "underweight.";
			feedback += bmiType;
		}
		else if (bmi < 24.9) {
			bmiType = "of normal weight.";
			feedback += bmiType;
		} 
		else if (bmi < 29.9) {
			bmiType = "overweight.";
			feedback += bmiType;
		}
		else {
			bmiType = "obese.";
			feedback += bmiType;
		}


		response.setContentType("text/html");
    	PrintWriter out = response.getWriter();

    	out.println("<!DOCTYPE html><html lang=\"en\"><head><title>CS3220 Lab 10</title></head><body>");
    	out.println("Your BMI is " + String.valueOf(formatter.format(bmi)) + feedback);
    	out.println("<br><p><a href='BMICalculator'>Back to BMI Calculator</a></p>");
    	
    	//out.println("");
		out.println("</body></html>");


	}

}

