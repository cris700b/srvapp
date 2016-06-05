package it.app.db;

import java.sql.Connection;

/**
 * 
 * @author Birzu
 *
 */
public class DbConnectionManager {

	private String strDbUrl;
	private String strUser;
	private String strPass;
	private Connection con;
	
	/**
	 * 
	 * @param strDbUrl
	 * @param strUser
	 * @param strPass
	 */
	public	DbConnectionManager(String strDbUrl, String strUser, String strPass) {
		
		this.strDbUrl = strDbUrl;
		this.strUser = strUser;
		this.strPass= strPass;
		
		// create db connection here
	}
	
	/**
	 * 
	 * @return
	 */
	public Connection getConnection() {
		
		return this.con;
	}
	
	/**
	 * 
	 */
	public void closeConnection() {
		
		// close db connection here
	}
}
