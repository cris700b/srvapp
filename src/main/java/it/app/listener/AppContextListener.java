package it.app.listener;

import java.util.logging.Logger;

import it.app.db.DbConnectionManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class AppContextListener
 *
 */
@WebListener
public class AppContextListener implements ServletContextListener {

	private Logger log = Logger.getLogger(this.getClass().getName());
	
    /**
     * Default constructor. 
     */
    public AppContextListener() {

    }

    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent servletContextEvent)  { 

    	ServletContext ctx = servletContextEvent.getServletContext();
    	
    	String strDbUrl = ctx.getInitParameter("DB_URL");
    	String strUser = ctx.getInitParameter("DB_USER");
    	String strPass = ctx.getInitParameter("DB_PASS");
    	
    	// create db connection from init params 
    	// and set it to context
    	DbConnectionManager dbManager = new DbConnectionManager(strDbUrl, strUser, strPass);
    	ctx.setAttribute("DbManager", dbManager);
    	
    	this.log.info("Database connection initialized for Application."); 	
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent servletContextEvent)  { 

    	ServletContext ctx = servletContextEvent.getServletContext();
    	DbConnectionManager dbManager = (DbConnectionManager) ctx.getAttribute("DbManager");
    	dbManager.closeConnection();
    	
    	this.log.info("Database connection closed for Application");
    }

	
}
