package it.app.listener;

import java.util.logging.Logger;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class AppContextAttributeListener
 *
 */
@WebListener
public class AppContextAttributeListener implements ServletContextAttributeListener {

	private Logger log = Logger.getLogger(this.getClass().getName());
	
    /**
     * Default constructor. 
     */
    public AppContextAttributeListener() {

    }

	/**
     * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
     */
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent)  { 

    	this.log.info("Sevlet Context Attribute added :: { " 
    					+ servletContextAttributeEvent.getName()
    					+ ", " + servletContextAttributeEvent.getValue() 
    					+ " }");
    }

	/**
     * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
     */
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent)  { 

    	this.log.info("Sevlet Context Attribute removed :: { " 
    					+ servletContextAttributeEvent.getName()
    					+ ", " + servletContextAttributeEvent.getValue()
    					+ " }");
    }

	/**
     * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
     */
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent)  { 
    	
    	this.log.info("Sevlet Context Attribute replaced :: { " 
    					+ servletContextAttributeEvent.getName()
    					+ ", " + servletContextAttributeEvent.getValue()
    					+ " }");
    }
}
