package common.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseCommunicator {
	private static DatabaseCommunicator dbc;
	private Connection conn;
	private String error = "";
	
	private DatabaseCommunicator() {
		try{
		    Class.forName("org.postgresql.Driver");
		    } catch (ClassNotFoundException cnfe){
		    	error = "Could not find the JDBC driver!";
		    	System.out.println(error);
		    	System.exit(1);
		    }
		try {
		    conn = DriverManager.getConnection
		                   ("jdbc:postgresql://djupfeldt.se:5432/tda367dragon",
		                		   "tda367dragon", "tda367dragon");
		     } catch (SQLException sqle) {
		    	 sqle.printStackTrace();
		    	 error = "Could not connect";
		    	 System.out.println(error);
		    	 System.exit(1);
		     }
	}
	
	public static DatabaseCommunicator getInstance() {
		if(dbc == null) {
			dbc = new DatabaseCommunicator();
		}
		return dbc;
	}
	
	public Connection getConnection() {
		return conn;
	}
	
	/**
	 * Only for testing.
	 * @return "" if there is no errors while connecting to the database.
	 */
	public String getError() {
		return error;
	}
}
