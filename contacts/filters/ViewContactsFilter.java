//ViewContactsFilter.java
//David Gaulke
//ICS 425 - Assignment 4
package contacts.filters;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import contacts.model.*;

public class ViewContactsFilter implements Filter {
	private FilterConfig filterConfig;
	private HttpSession session;

	public void init(FilterConfig filterConfig){
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {
		
		try {
			request.setAttribute("contacts", 
					ContactDB.loadAll((String)((HttpServletRequest)request).
						getSession().getAttribute("login")));
			 chain.doFilter(request, response);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	public void destroy(){
		filterConfig = null;
	}

}
