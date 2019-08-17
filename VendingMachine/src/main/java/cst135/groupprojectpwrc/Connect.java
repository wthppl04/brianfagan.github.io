package cst135.groupprojectpwrc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    Connection connection;
    public void ContactDataSource(){
      //  System.out.println("about to connect to mysql");
        String dbURL="jdbc:mysql://vendingmachine.chgctfew4axe.us-east-2.rds.amazonaws.com:3306";
        String user ="admin";
        String password="password123";
        //try with resources to open connection (closes connection automatically)
        // very important to close db connection
        
         try {
            connection = DriverManager.getConnection(dbURL,user,password);
           // System.out.println("Connected");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
    }
  
    public void close() {
    	try {
			connection.close();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
    }
    
}


//Port 3306
//admin
//password123