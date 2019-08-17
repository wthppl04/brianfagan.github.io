package cst135.groupprojectpwrc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Queries {
	   public List<Item> itemList() {

	        List<Item> itemList = new ArrayList<Item>();

	        String sql = "SELECT cost_to_owner, product_name, product_price, min_inventory, product_qty, product_cal FROM Vending_Machine.Inventory";

	        try{
	        	Connect connect = new Connect();
	        	connect.ContactDataSource();
	            Statement statement = connect.connection.createStatement();
	            ResultSet resultSet = statement.executeQuery(sql);
	            while (resultSet.next()) {
	                itemList.add(new Item(
	                        resultSet.getDouble("cost_to_owner"),
	                        resultSet.getString("product_name"),
	                        resultSet.getDouble("product_price"),
	                        resultSet.getInt("min_inventory"),
	                        resultSet.getInt("product_qty"),
	                        resultSet.getInt("product_cal")));
	            }
	        }

	        catch(SQLException e){
	            e.printStackTrace();

	        }

	        return itemList;

	     
	    }
	   
}
