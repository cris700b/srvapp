package it.app.listener;

import java.util.logging.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class MyServletRequestListener
 *
 */
@WebListener
public class MyServletRequestListener implements ServletRequestListener {

	private Logger log = Logger.getLogger(this.getClass().getName());
    
	/**
     * Default constructor. 
     */
    public MyServletRequestListener() {

    }

	/**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    public void requestDestroyed(ServletRequestEvent servletRequestEvent)  { 
    	
    	ServletRequest req = servletRequestEvent.getServletRequest();
    	this.log.info("Servlet Request destroyed. Remote IP = " + req.getRemoteAddr());
    }

	/**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    public void requestInitialized(ServletRequestEvent servletRequestEvent)  { 
    	
    	ServletRequest req = servletRequestEvent.getServletRequest();
    	this.log.info("Servlet Request initialized. Remote IP = " + req.getRemoteAddr());
    }
	
}
