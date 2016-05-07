
//ViewContact.java
//David Gaulke
//ICS 425 Assigment 3
package contacts.controllers;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import contacts.model.*;
import contacts.util.AppUtil;

@SuppressWarnings("serial")
public class DeleteContactServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException {

		try {
			String login = (String)request.getSession().getAttribute("login");
			int contactId = Integer.valueOf(request.getParameter("contactId"));
			Contact contact = ContactDB.load(contactId, login);

			ContactDB.delete(contactId, login);

			String url = "/view_contacts.jsp";	
			request.setAttribute("message", contact.getFirstName() + " " + 
					contact.getLastName() + " has been deleted.");
			request.getSession().removeAttribute("contact");
			RequestDispatcher dispatcher = 
					getServletContext().getRequestDispatcher(url);
			dispatcher.forward(request, response);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) 
			throws ServletException { 
		this.doPost(request, response);
	}

}
