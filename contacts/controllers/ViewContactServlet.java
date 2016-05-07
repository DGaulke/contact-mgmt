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
public class ViewContactServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException {

		try {
			HttpSession session = request.getSession();
			Contact contact = ContactDB.load(Integer.valueOf(
					request.getParameter("id")), 
					(String)session.getAttribute("login"));
			request.setAttribute("contact", contact);

			String url = "/view_contact.jsp";	
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
