//Anniversaries.java
//David Gaulke
//ICS 425 Assigment 3
package contacts.controllers;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;
import java.sql.*;
import contacts.model.*;
import contacts.util.AppUtil;

@SuppressWarnings("serial")
public class AnniversariesServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException {
			
		HttpSession session = request.getSession();
		String url = "";	

		if (isPreviousButtonClicked(request)){
			url = "/add_contact.jsp";
		} else if (isCancelButtonClicked(request)){
			session.removeAttribute("contact");
			url = "/index.jsp";
		} else { 
			Contact contact;
			if ((contact = (Contact)session.getAttribute("contact")) == null)
				contact = new Contact();
			setAnniversaries(contact, request);

			try {
				ContactDB.insert(contact, (String)session.getAttribute("login"));
				request.setAttribute("message", contact.getFirstName() + " " +
						contact.getLastName() + " successfully added!");
			} catch (SQLException sqle){
				request.setAttribute("message", sqle.getMessage());
			}

			url = "/view_contacts.jsp";	
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
	private boolean isCancelButtonClicked(HttpServletRequest request){
		return request.getParameter("cancel") != null && //Previous
				request.getParameter("cancel").equals("cancel");
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
		}
	}
}
