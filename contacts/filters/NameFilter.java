//NameFilter.java
//David Gaulke
//ICS 425 - Assignment 4
package contacts.filters;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import contacts.model.User;

public class NameFilter implements Filter {
	private FilterConfig filterConfig;

	public void init(FilterConfig filterConfig){
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {

		if (isNameEntered((HttpServletRequest)request)){
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse)response).sendRedirect("/contacts/register");
			return;
		}
	}
	
	public void destroy(){
		filterConfig = null;
	}

	private boolean isNameEntered(HttpServletRequest request){
		User user; 
		boolean firstName, lastName;
		HttpSession session = request.getSession();
		if (request.getParameter("firstName") != null &&
				request.getParameter("lastName")!= null){

			firstName = request.getParameter("firstName").length() > 0;
			lastName = request.getParameter("lastName").length() > 0;
		} else if ((user = (User)session.getAttribute("user")) != null &&
				user.getFirstName() != null && user.getLastName() != null){

			firstName = user.getFirstName().length() > 0;
			lastName = user.getLastName().length() > 0;
		} else {
			return false;
		}
		return firstName && lastName;
	}
}
