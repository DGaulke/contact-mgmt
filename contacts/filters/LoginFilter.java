//LoginFilter.java
//David Gaulke
//ICS 425 - Assignment 4
package contacts.filters;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import contacts.model.User;

public class LoginFilter implements Filter {
	private FilterConfig filterConfig;
	private HttpSession session;

	public void init(FilterConfig filterConfig){
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {

		if (isLoggedIn(request)){
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse)response).sendRedirect("/contacts/login.jsp");
			return;
		}
	}
	
	public void destroy(){
		filterConfig = null;
	}
	
	private boolean isLoggedIn(ServletRequest request){
		HttpSession session = ((HttpServletRequest)request).getSession();
		return session.getAttribute("login") != null && 
			session.getAttribute("login").toString().length() > 0;
	}
}
