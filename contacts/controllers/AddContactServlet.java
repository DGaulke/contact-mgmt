//AddContactsServlet.java
//David Gaulke
//ICS 425 Assigment 2
package contacts.controllers;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import contacts.model.User;
import contacts.util.AppUtil;

@SuppressWarnings("serial")
public class AddContactServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException {
			
		HttpSession session = request.getSession();

		if (session.getAttribute("stateoption") == null)
			session.setAttribute("stateoption", 
					AppUtil.stateOption());
			
		if (session.getAttribute("phoneoption") == null){
			String[] options = new String[2];
			options[0] = AppUtil.phoneType(0);
			options[1] = AppUtil.phoneType(1);
			session.setAttribute("phoneoption", options);
		}
		if (session.getAttribute("emailoption") == null){
			String[] options = new String[2];
			options[0] = AppUtil.emailType(0);
			options[1] = AppUtil.emailType(1);
			session.setAttribute("emailoption", options);
		}
		try {
			String url = "/add_contact.jsp";
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

}
