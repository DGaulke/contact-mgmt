//CredentialsServlet.java
//David Gaulke
//ICS 425 Assigment 2
package contacts.controllers;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import com.mysql.jdbc.exceptions.jdbc4.*;
import contacts.model.*;
import contacts.util.AppUtil;

@SuppressWarnings("serial")
public class CredentialsServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException {
			
		HttpSession session = request.getSession();
		String url = "";

		if (isPreviousButtonClicked(request)){
			url = "/register/address.jsp";
		} else { 
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");

			User user = (User)session.getAttribute("user"); 

			user.setUserName(userName);
			user.setPassword(password);
			
			try {
				UserDB.insert(user);
				session.setAttribute("stateoption", AppUtil.stateOption(""));
				url = "/register/confirmation.jsp";
			} catch (MySQLIntegrityConstraintViolationException ve){
				user.setUserName(null);
				user.setPassword(null);
				request.setAttribute("message","Username " + userName + 
						" is already in use.");
				url = "/register/credentials.jsp";
			}
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
		return request.getParameter("previous") != null && //Previous
				request.getParameter("previous").equals("previous");
	}

}
