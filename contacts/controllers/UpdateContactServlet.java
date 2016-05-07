//UpdateContactServlet.java
//David Gaulke
//ICS 425 Assigment 3
package contacts.controllers;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;
import contacts.model.*;
import contacts.util.AppUtil;

@SuppressWarnings("serial")
public class UpdateContactServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException {
			
		try {
			HttpSession session = request.getSession();
			String url = "";	

			if (isCancelButtonClicked(request)){
				url = "/index.jsp";
			} else { 
				int contactId = Integer.valueOf(
						request.getParameter("contactId"));
				Contact contact = ContactDB.load(contactId,
						(String)session.getAttribute("login"));


				contact.setFirstName(request.getParameter("firstName"));
				contact.setLastName(request.getParameter("lastName"));
				contact.setStreetAddress(request.getParameter("streetAddress"));
				contact.setCity(request.getParameter("city"));
				contact.setState(request.getParameter("state"));
				contact.setZip(request.getParameter("zip"));

				setPhoneNumbers(contact, request);
				setEmailAddresses(contact, request);
				setAnniversaries(contact, request);	

				ContactDB.update(contact);
				request.setAttribute("message", contact.getFirstName() + " " +
						contact.getLastName() + " successfully updated!");

				url = "/view_contacts.jsp";	
			}
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

	private boolean isPreviousButtonClicked(HttpServletRequest request){
		return request.getParameter("previous") != null && 
				request.getParameter("previous").equals("previous");
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
			contact.setEmailAddresses(
					emailAddresses.toArray(emailAddressArray));
		}
	}
	private void setAnniversaries(Contact contact, HttpServletRequest request){
		List<Anniversary> anniversaries = new ArrayList<Anniversary>();
		if (request.getParameter("anniversary0").length() > 0){
			Anniversary a = new Anniversary();
			a.setAnniversaryType(request.getParameter("anniversaryType0"));
			a.setAnniversary(request.getParameter("anniversary0"));
			anniversaries.add(a);
		}
		if (request.getParameter("anniversary1").length() > 0){
			Anniversary a = new Anniversary();
			a.setAnniversaryType(request.getParameter("anniversaryType1"));
			a.setAnniversary(request.getParameter("anniversary1"));
			anniversaries.add(a);
		}
		if (request.getParameter("anniversary2").length() > 0){
			Anniversary a = new Anniversary();
			a.setAnniversaryType(request.getParameter("anniversaryType2"));
			a.setAnniversary(request.getParameter("anniversary2"));
			anniversaries.add(a);
		}
		if (anniversaries.size() > 0){
			Anniversary[] anniversaryArray = 
					new Anniversary[anniversaries.size()];
			contact.setAnniversaries(anniversaries.toArray(anniversaryArray));
		} else {
			contact.setAnniversaries(null);
		}
	}
}
