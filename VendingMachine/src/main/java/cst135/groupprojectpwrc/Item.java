package cst135.groupprojectpwrc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Item {
	private double cost;
	private String description;
	private double salesPrice;
	private int minStockLevel;
	private boolean adminAlerted;
	private int currentInventory;
	private int calories;
	private String consumerMessage;
	private static final double CALORIES_PER_HOUR = 400;
	Connect db = new Connect();
	/*
	 * Stair Climbing. The more you weigh, the more calories you'll burn climbing stairs.
	 * Harvard Health Publications reports that a 125-pound person expends 360 calories using a stair step machine for one hour,
	 * a 155-pound individual burns 446 calories and a 185-pound person expends 532 calories per hour climbing stairs.
	 */
	
	/**
	 * @param cost the machine vendor's item cost
	 * @param description the description of the item
	 * @param salesPrice the customer price of the item
	 * @param minStockLevel the minimum stocking level before alerting of low inventory
	 * @param currentInventory the current inventory of the item in the machine
	 * @param calories the total number of calories for the item
	 */
	public Item(double cost, String description, double salesPrice, int minStockLevel, int currentInventory, int calories) {
		super();
		this.cost = cost;
		this.description = description;
		this.salesPrice = salesPrice;
		this.minStockLevel = minStockLevel;
		this.adminAlerted = false;
		this.currentInventory = currentInventory;
		this.calories = calories;
		this.consumerMessage = "Enjoy your " + this.description + " and note that it will take "
			+ (this.calories / CALORIES_PER_HOUR) + " hours on the stair climber to burn it off.";
	}

	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the salesPrice
	 */
	public double getSalesPrice() {
		return salesPrice;
	}

	/**
	 * @param salesPrice the salesPrice to set
	 */
	public void setSalesPrice(double salesPrice) {
		this.salesPrice = salesPrice;
	}

	/**
	 * @return the minStockLevel
	 */
	public int getMinStockLevel() {
		return minStockLevel;
	}

	/**
	 * @param minStockLevel the minStockLevel to set
	 */
	public void setMinStockLevel(int minStockLevel) {
		this.minStockLevel = minStockLevel;
	}

	/**
	 * @return the adminAlerted
	 */
	public boolean isAdminAlerted() {
		return adminAlerted;
	}

	/**
	 * @param adminAlerted the adminAlerted to set
	 */
	public void setAdminAlerted(boolean adminAlerted) {
		this.adminAlerted = adminAlerted;
	}

	/**
	 * @return the currentInventory
	 */
	public int getCurrentInventory(String name) {
		db.ContactDataSource();
		
		String sql = "SELECT product_qty FROM Vending_Machine.Inventory WHERE product_name =" +"\""+name+"\";";
		try {
			Statement statement = db.connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				currentInventory = resultSet.getInt("product_qty");
			}
			db.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return currentInventory;
	}

	/**
	 * @param currentInventory the currentInventory to set
	 */
	public void setCurrentInventory(int newInventory, String name) {
		this.currentInventory = newInventory;
			db.ContactDataSource();
		String sql = "UPDATE Vending_Machine.Inventory SET product_qty = " + currentInventory + " where (product_name = " 
		+"\""+name+"\")";
		try {
			Statement statement = db.connection.createStatement();
			statement.executeUpdate(sql);
			db.close();
			
		} 
		catch (SQLException e) {

			e.printStackTrace();
		}
		
	}
	
	/**
	 * @return the calories
	 */
	public int getCalories() {
		return calories;
	}

	/**
	 * @param calories the calories to set
	 */
	public void setCalories(int calories) {
		this.calories = calories;
	}

	/**
	 * @return the consumerMessage
	 */
	public String getConsumerMessage() {
		return consumerMessage;
	}

	/**
	 * @param consumerMessage the consumerMessage to set
	 */
	public void setConsumerMessage(String consumerMessage) {
		this.consumerMessage = consumerMessage;
	}

	/*@Override
	public String toString() {
		return "Item [cost=" + cost + ", description=" + description + ", salesPrice=" + salesPrice + ", minStockLevel="
				+ minStockLevel + ", adminAlerted=" + adminAlerted + ", currentInventory=" + currentInventory
				+ ", calories=" + calories + ", consumerMessage=" + consumerMessage + "]";
	}
*/
	//Class methods
	/**
	 * returns a String representation of an item object
	 */
	public String toString() {
		return description + "," + cost + "," + salesPrice;
	}
	
	
	
	
}
