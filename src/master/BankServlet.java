package master;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/BankServlet")
public class BankServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ifsc = request.getParameter("ifsc");
		PrintWriter out = response.getWriter();
		    if(ifsc.equalsIgnoreCase("punb123"))
		    {
		    	out.print("PNB NOIDA");
		    }
		    else if(ifsc.equalsIgnoreCase("sbi123"))
		    {
		    	out.print("SBI Delhi");
		    }
		 
	}

}
