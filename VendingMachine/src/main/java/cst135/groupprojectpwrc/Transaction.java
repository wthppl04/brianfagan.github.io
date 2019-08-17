package cst135.groupprojectpwrc;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
	//Class data fields
	private Date transDate;
	private String machineID;
	private String itemPurchased;
	private String itemPositionCode;
	private double itemCost;
	private double purchaseAmount;
	private static final String DELIM = "\t";
	public static final SimpleDateFormat dateTime = new SimpleDateFormat("MM/dd/YYYY HH:mm:ss");
	private static final NumberFormat money = NumberFormat.getCurrencyInstance();
	
	//Constructor	
	/**
	 * @param machineID the ID for the machine
	 * @param itemPurchased the description of hte item purchased
	 * @param itemPositionCode the position code in the machine, e.g. "A1"
	 * @param itemCost the machine vendor's item cost
	 * @param purchaseAmount the customer purchase amount for the item
	 */
	public Transaction(String machineID, String itemPurchased, String itemPositionCode, double itemCost,
			double purchaseAmount) {
		this.transDate = new Date();
		this.machineID = machineID;
		this.itemPurchased = itemPurchased;
		this.itemPositionCode = itemPositionCode;
		this.itemCost = itemCost;
		this.purchaseAmount = purchaseAmount;
	}

	//Accessors and mutators
	/**
	 * @return the transDate
	 */
	public Date getTransDate() {
		return transDate;
	}

	/**
	 * @param transDate the transDate to set
	 */
	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	/**
	 * @return the machineID
	 */
	public String getMachineID() {
		return machineID;
	}

	/**
	 * @param machineID the machineID to set
	 */
	public void setMachineID(String machineID) {
		this.machineID = machineID;
	}

	/**
	 * @return the itemPurchased
	 */
	public String getItemPurchased() {
		return itemPurchased;
	}

	/**
	 * @param itemPurchased the itemPurchased to set
	 */
	public void setItemPurchased(String itemPurchased) {
		this.itemPurchased = itemPurchased;
	}

	/**
	 * @return the itemPositionCode
	 */
	public String getItemPositionCode() {
		return itemPositionCode;
	}

	/**
	 * @param itemPositionCode the itemPositionCode to set
	 */
	public void setItemPositionCode(String itemPositionCode) {
		this.itemPositionCode = itemPositionCode;
	}

	/**
	 * @return the itemCost
	 */
	public double getItemCost() {
		return itemCost;
	}

	/**
	 * @param itemCost the itemCost to set
	 */
	public void setItemCost(double itemCost) {
		this.itemCost = itemCost;
	}

	/**
	 * @return the purchaseAmount
	 */
	public double getPurchaseAmount() {
		return purchaseAmount;
	}

	/**
	 * @param purchaseAmount the purchaseAmount to set
	 */
	public void setPurchaseAmount(double purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}
	
	/**
	 * @return the string representation of a transaction with formatting
	 * for date and currency and a delimiter between values
	 */
	public String toString() {
		return this.machineID
			+ DELIM + dateTime.format(this.transDate)
			+ DELIM + this.itemPositionCode
			+ DELIM + this.itemPurchased + (this.itemPurchased.length() < 8 ? DELIM : "")
			+ DELIM + money.format(this.itemCost)
			+ DELIM + money.format(this.purchaseAmount);
	}
	
	public void printTransaction() {
		System.out.println(this.toString());
	}
}
