//ViewUsersFilter.java
//David Gaulke
//ICS 425 - Assignment 4
package contacts.filters;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import contacts.model.*;

public class ViewUsersFilter implements Filter {
	private FilterConfig filterConfig;
	private HttpSession session;

	public void init(FilterConfig filterConfig){
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {
		
		request.setAttribute("users", UserDB.loadAll());
		chain.doFilter(request, response);
	}
	
	public void destroy(){
		filterConfig = null;
	}

}
