//NameServlet.java
//David Gaulke
//ICS 425 Assigment 2
package contacts.controllers;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import contacts.model.User;
import contacts.util.AppUtil;

@SuppressWarnings("serial")
public class NameServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException {
			
		HttpSession session = request.getSession();
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		
		User user;
		if ((user = (User)session.getAttribute("user")) == null)
			user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		session.setAttribute("user", user);
		session.setAttribute("stateoption", 
				AppUtil.stateOption(user.getState()));
		String url = "/register/address.jsp";

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

	private boolean isCancelButtonClicked(HttpServletRequest request){
		return request.getParameter("cancel") != null && 
				request.getParameter("cancel").equals("cancel");
	}

}
