//LoginServlet.java
//David Gaulke
//ICS 425 Assigment 4
package contacts.controllers;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import com.mysql.jdbc.exceptions.jdbc4.*;
import contacts.model.*;
import contacts.util.AppUtil;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException {
			
		HttpSession session = request.getSession();
		String url = "";

		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		User user = UserDB.load(userName);

		if (user != null && user.isValid(password)) {
			request.removeAttribute("message");
			session.setAttribute("login", userName);
			url = "/view_contacts.jsp";
		} else {
			request.setAttribute("message", "Login invalid");
			url = "/login.jsp";
		}

		try {
			RequestDispatcher dispatcher = 
					getServletContext().getRequestDispatcher(url);
			dispatcher.forward(request, response);
		} catch (IOException ioe){
			ioe.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) 
			throws ServletException { 
		this.doPost(request, response);
	}

	private boolean isPreviousButtonClicked(HttpServletRequest request){
		return request.getParameter("previous") != null && 
				request.getParameter("previous").equals("previous");
	}

}
