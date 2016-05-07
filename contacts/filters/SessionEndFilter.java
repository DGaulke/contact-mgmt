//SessionFilter.java
//David Gaulke
//ICS 425 - Assignment 4
package contacts.filters;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import contacts.model.*;

public class SessionEndFilter implements Filter {
	private FilterConfig filterConfig;

	public void init(FilterConfig filterConfig){
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {
		
		chain.doFilter(request, response);
		((HttpServletRequest)request).getSession().invalidate();
	}
	
	public void destroy(){
		filterConfig = null;
	}
}
