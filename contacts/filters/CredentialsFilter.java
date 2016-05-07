//CredentialsFilter.java
//David Gaulke
//ICS 425 - Assignment 4
package contacts.filters;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import contacts.model.User;

public class CredentialsFilter implements Filter {
	private FilterConfig filterConfig;
	private HttpSession session;

	public void init(FilterConfig filterConfig){
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest)request;
		if (isPreviousButtonClicked(req) ||
				areCredentialsEntered(req)){
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse)response).sendRedirect("/contacts/register");
			return;
		}
	}
	
	public void destroy(){
		filterConfig = null;
	}

	private boolean areCredentialsEntered(HttpServletRequest request){
		User user; 
		boolean userName, password;
		HttpSession session = request.getSession();


		if (request.getParameter("userName") != null &&
				request.getParameter("password") != null){
			userName = request.getParameter("userName").length() > 0;
			password = request.getParameter("password").length() > 0;
		} else if ((user = (User)session.getAttribute("user")) != null &&
				user.getUserName() != null && user.getPassword() != null){
			userName = user.getUserName().length() > 0;
			password = user.getPassword().length() > 0;
		} else {
			return false;
		}
		return userName && password;
	}

	private boolean isPreviousButtonClicked(HttpServletRequest request){
		return request.getParameter("previous") != null &&
				request.getParameter("previous").equals("previous");
	}
}
