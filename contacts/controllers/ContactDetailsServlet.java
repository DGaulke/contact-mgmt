//ContactDetailsServlet.java
//David Gaulke
//ICS 425 Assigment 3
package contacts.controllers;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;
import contacts.model.*;
import contacts.util.AppUtil;

@SuppressWarnings("serial")
public class ContactDetailsServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException {
			
		HttpSession session = request.getSession();
		String url = "";
		
		if (isCancelButtonClicked(request)){
			url = "/index.jsp";
		} else {
			
			Contact contact;
			if ((contact = (Contact)session.getAttribute("contact")) == null)
				contact = new Contact();
			contact.setFirstName(request.getParameter("firstName"));
			contact.setLastName(request.getParameter("lastName"));
			contact.setStreetAddress(request.getParameter("streetAddress"));
			contact.setCity(request.getParameter("city"));
			contact.setState(request.getParameter("state"));
			contact.setZip(request.getParameter("zip"));

			setPhoneNumbers(contact, request);
			setEmailAddresses(contact, request);

			session.setAttribute("contact", contact);

			session.setAttribute("stateoption", 
					AppUtil.stateOption(contact.getState()));

			String[] phoneTypes = new String[2];
			phoneTypes[0] = AppUtil.phoneType(0, 
					request.getParameter("phoneType0"));
			phoneTypes[1] = AppUtil.phoneType(1, 
					request.getParameter("phoneType1"));
			session.setAttribute("phoneoption", phoneTypes);
			
			String[] emailTypes = new String[2];
			emailTypes[0] = AppUtil.emailType(0, 
					request.getParameter("emailType0"));
			emailTypes[1] = AppUtil.emailType(1, 
					request.getParameter("emailType1"));
			session.setAttribute("emailoption", emailTypes);

			url = "/anniversaries.jsp";	
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

	private boolean isCancelButtonClicked(HttpServletRequest request){
		return request.getParameter("cancel") != null && 
				request.getParameter("cancel").equals("cancel");
	}
	private void setPhoneNumbers(Contact contact, HttpServletRequest request){
		List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
		if (request.getParameter("phone0").length() > 0){
			PhoneNumber pn = new PhoneNumber();
			pn.setPhoneType(request.getParameter("phoneType0"));
			pn.setPhoneNumber(request.getParameter("phone0"));
			phoneNumbers.add(pn);
		}
		if (request.getParameter("phone1").length() > 0){
			PhoneNumber pn = new PhoneNumber();
			pn.setPhoneType(request.getParameter("phoneType1"));
			pn.setPhoneNumber(request.getParameter("phone1"));
			phoneNumbers.add(pn);
		}
		if (phoneNumbers.size() > 0){
			PhoneNumber[] phoneNumberArray = 
					new PhoneNumber[phoneNumbers.size()];
			contact.setPhoneNumbers(phoneNumbers.toArray(phoneNumberArray));
		}
	}
	private void setEmailAddresses(Contact contact, HttpServletRequest request){
		List<EmailAddress> emailAddresses = new ArrayList<EmailAddress>();
		if (request.getParameter("email0").length() > 0){
			EmailAddress ea = new EmailAddress();
			ea.setEmailType(request.getParameter("emailType0"));
			ea.setEmailAddress(request.getParameter("email0"));
			emailAddresses.add(ea);
		}
		if (request.getParameter("email1").length() > 0){
			EmailAddress ea = new EmailAddress();
			ea.setEmailType(request.getParameter("emailType1"));
			ea.setEmailAddress(request.getParameter("email1"));
			emailAddresses.add(ea);
		}
		if (emailAddresses.size() > 0){
			EmailAddress[] emailAddressArray = 
					new EmailAddress[emailAddresses.size()];
			contact.setEmailAddresses(emailAddresses.toArray(
					emailAddressArray));
		}
	}
}
