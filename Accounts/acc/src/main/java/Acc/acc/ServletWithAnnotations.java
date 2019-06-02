package Acc.acc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/hello/*")
public class ServletWithAnnotations extends HttpServlet {
	private static final long serialVersionUID = -3462096228274971485L;
	@Override
	protected void doGet(HttpServletRequest reqest, HttpServletResponse response) 
			throws ServletException, IOException {
		response.getWriter().println("Hello World!");
	}
	protected void doPost(
			HttpServletRequest request, 
			HttpServletResponse response) 	throws ServletException, IOException {

		String pathInfo = request.getPathInfo();

		if(pathInfo == null || pathInfo.equals("/")){


		}
		else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
	}

}