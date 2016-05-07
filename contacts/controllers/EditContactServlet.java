//EditContactsServlet.java
//David Gaulke
//ICS 425 Assigment 3
package contacts.controllers;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import contacts.model.*;
import contacts.util.AppUtil;

@SuppressWarnings("serial")
public class EditContactServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException {
			
		try {
			int contactId = Integer.valueOf(request.getParameter("id"));
			HttpSession session = request.getSession();

			Contact contact = ContactDB.load(contactId,
					(String)session.getAttribute("login"));

			session.setAttribute("contact", contact);
			session.setAttribute("stateoption",
					AppUtil.stateOption(contact.getState()));


			String[] phoneTypes = new String[2];
			String phoneType0 = (contact.getPhoneNumbers() != null && 
					contact.getPhoneNumbers().length > 0) ? 
					contact.getPhoneNumbers()[0].getPhoneType() : "";
			String phoneType1 = (contact.getPhoneNumbers() != null && 
					contact.getPhoneNumbers().length > 1) ? 
					contact.getPhoneNumbers()[1].getPhoneType() : "";
			phoneTypes[0] = AppUtil.phoneType(0, phoneType0);
			phoneTypes[1] = AppUtil.phoneType(1, phoneType1);
			session.setAttribute("phoneoption", phoneTypes);
			
			String[] emailTypes = new String[2];
			String emailType0 = (contact.getEmailAddresses() != null && 
					contact.getEmailAddresses().length > 0) ? 
					contact.getEmailAddresses()[0].getEmailType() : "";
			String emailType1 = (contact.getEmailAddresses() != null && 
					contact.getEmailAddresses().length > 1) ? 
					contact.getEmailAddresses()[1].getEmailType() : "";
			emailTypes[0] = AppUtil.emailType(0, emailType0);
			emailTypes[1] = AppUtil.emailType(1, emailType1);
			session.setAttribute("emailoption", emailTypes);


			String url = "/edit_contact.jsp";
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
