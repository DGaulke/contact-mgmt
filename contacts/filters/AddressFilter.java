//AddressFilter.java
//David Gaulke
//ICS 425 - Assignment 4
package contacts.filters;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import contacts.model.User;

public class AddressFilter implements Filter {
	private FilterConfig filterConfig;

	public void init(FilterConfig filterConfig){
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest)request;		
		if (isPreviousButtonClicked(req) ||
				isAddressEntered(req)){
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse)response).sendRedirect("/contacts/register");
			return;
		}
	}
	
	public void destroy(){
		filterConfig = null;
	}

	private boolean isAddressEntered(HttpServletRequest request){
		User user; 
		boolean address1, city, state, zip;
		HttpSession session = request.getSession();
		if (request.getParameter("street_address") != null &&
				request.getParameter("city") != null &&
				request.getParameter("state") != null &&
				request.getParameter("zip") != null){
			address1 = request.getParameter("street_address").length() > 0;
			city = request.getParameter("city").length() > 0;
			state = request.getParameter("state").length() > 0;
			zip = request.getParameter("zip").length() > 0;

			return true;
		} else if ((user = (User)session.getAttribute("user")) != null &&
				user.getStreetAddress() != null && user.getCity() != null &&
				user.getState() != null & user.getZip() != null){
			address1 =  user.getStreetAddress().length() > 0;
			city = user.getCity().length() > 0;
			state = user.getState().length() > 0;
			zip = user.getZip().length() > 0;
		} else {
			return false;
		}
		return address1 && city && state && zip;
	}

	private boolean isPreviousButtonClicked(HttpServletRequest request){
		return request.getParameter("previous") != null && 
				request.getParameter("previous").equals("previous");
	}
}
