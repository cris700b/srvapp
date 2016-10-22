package it.app.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class RequestLoggingFilter
 */
@WebFilter("/requestLoggingFilter")
public class RequestLoggingFilter implements Filter {

	private ServletContext context;
	
    /**
     * Default constructor. 
     */
    public RequestLoggingFilter() {

    }

    /**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		Enumeration<String> params = req.getParameterNames();
		while(params.hasMoreElements()){
			
			String strParamName = params.nextElement();
			String strParamValue = req.getParameter(strParamName);
			
			this.context.log(req.getRemoteAddr() + " :: Request Params :: { " 
							 + strParamName + " = " + strParamValue + " }" );
		}
		
		Cookie[] arrCookies = req.getCookies();
		if(null != arrCookies){
			
			for(Cookie cookie : arrCookies){
				
				this.context.log(req.getRemoteAddr() + " :: Cookie :: { "
								 + cookie.getName() + " = " + cookie.getValue() + " }");
			}
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	
		this.context = fConfig.getServletContext();
		this.context.log(this.getClass().getName() + " initialized");
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}
}
