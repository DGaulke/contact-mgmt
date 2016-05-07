//AddressServlet.java
//David Gaulke
//ICS 425 Assigment 2
package contacts.controllers;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import contacts.model.User;
import contacts.util.AppUtil;

@SuppressWarnings("serial")
public class AddressServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException {

		HttpSession session = request.getSession(); 
		String url = "";

		if (isPreviousButtonClicked(request)){
			url = "/register/name.jsp";
		} else { 
			String streetAddress = request.getParameter("street_address");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			session.setAttribute("stateoption", AppUtil.stateOption(state));
			String zip = request.getParameter("zip");

			User user = (User)session.getAttribute("user"); 

			user.setStreetAddress(streetAddress);
			user.setCity(city);
			user.setState(state);
			user.setZip(zip);
				
			url = "/register/credentials.jsp";
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
