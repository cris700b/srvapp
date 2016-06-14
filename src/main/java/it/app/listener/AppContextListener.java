package it.app.listener;

import it.app.db.DbConnectionManager;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Application Lifecycle Listener implementation class AppContextListener
 *
 */
@WebListener
public class AppContextListener implements ServletContextListener {
	
    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent servletContextEvent)  { 

    	ServletContext ctx = servletContextEvent.getServletContext();

    	// initialize the db connection
    	String strDbUrl = ctx.getInitParameter("DB_URL");
    	String strUser = ctx.getInitParameter("DB_USER");
    	String strPass = ctx.getInitParameter("DB_PASS");
    	
    	// create db connection from init params 
    	// and set it to context
    	try {
		
    		DbConnectionManager dbConnectionManager = new DbConnectionManager(strDbUrl, strUser, strPass);
			ctx.setAttribute("DbConnection", dbConnectionManager.getConnection());
			
			System.out.println("Database connection initialized successfully.");
			
			// initialize log4j
			String log4jConfig = ctx.getInitParameter("log4j-config");
			if(null != log4jConfig){

				String strWebAppPath = ctx.getRealPath("/");
				String strLog4jProp = strWebAppPath + log4jConfig;
				File log4jCgfFile = new File(strLog4jProp);
				if(log4jCgfFile.exists()){
					
					System.out.println("Initializing log4j with : " + strLog4jProp);
					DOMConfigurator.configure(strLog4jProp);
				}
				else {
					
					System.err.println(strLog4jProp + "file not found"
										+ ", initializing log4j with BasicConfigurator");
				}
			}
			else{
				
				System.err.println("No log4j-config init param, "
									+ "initializing log4j with BasicConfigurator");
				BasicConfigurator.configure();
			}
			
			System.out.println("log4j configured properly");
		} 
    	catch (ClassNotFoundException e) {

    		e.printStackTrace();
		} 
    	catch (SQLException e) {

    		e.printStackTrace();
		} 	
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent servletContextEvent)  { 

    	try {
		
    		ServletContext ctx = servletContextEvent.getServletContext();
			Connection connection = (Connection) ctx.getAttribute("DbConnection");
			connection.close();
			System.out.println("Database connection closed for srvApp");
		} 
    	catch (SQLException e) {

    		e.printStackTrace();
		}
    	
    	
    }

	
}
