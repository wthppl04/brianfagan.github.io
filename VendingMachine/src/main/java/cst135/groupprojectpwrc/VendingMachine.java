package cst135.groupprojectpwrc;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VendingMachine {
	// Class data fields
	private String selection;
	private int numRows;
	private int numCols;
	private int row;
	private int col;
	private Item[][] items;
	private final int ITEMS_PER_TUBE = 10;
	private String machineID;
	private double machineLatitude;
	private double machineLongitude;
	private Payment payment;
	private List<Transaction> transactions;
	private Administrator root;
	private boolean machineInitialized;
	private boolean machineFull;
	private int quarters;
	private int ones;
	/**
	 * Constructor for a new VendingMachine object
	 * @param numRows the number of horizontal rows in the machine
	 * @param numCols the number of vertical columns in the machine
	 * @param root is the Administrator object
	 */
	public VendingMachine(int numRows, int numCols, Administrator root) {
		 items = new Item[numRows][numCols];
		 this.numRows = numRows;
		 this.numCols = numCols;
		 this.machineID = "PWRC1";
		 this.machineLatitude = 33.512682;
		 this.machineLongitude = -112.113626;
		 this.transactions = new ArrayList<Transaction>();
		 this.root = root;
	}

	// Accessors and Mutators

	
	
	Connect db = new Connect();
	
	public VendingMachine() {
		// TODO Auto-generated constructor stub
	}

	public int getQuarters() {
		db.ContactDataSource();
		String sql = "SELECT quarters FROM Vending_Machine.change";
		try {
			Statement statement = db.connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				quarters = resultSet.getInt("quarters");
			}
			db.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return quarters;
	}

	public void setQuarters(int quarters) {
		
		db.ContactDataSource();
		String sql = "UPDATE Vending_Machine.change SET quarters = " + quarters + " where (change_id = 0)";
		try {
			Statement statement = db.connection.createStatement();
			statement.executeUpdate(sql);
			db.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	
				
	}

	public int getOnes() {
		
		db.ContactDataSource();
		String sql = "SELECT ones FROM Vending_Machine.change";
		try {
			Statement statement = db.connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				ones = resultSet.getInt("ones");
			}
			db.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		
		return ones;
	}

	public void setOnes(int ones) {	
		db.ContactDataSource();
		String sql = "UPDATE Vending_Machine.change SET ones = " + ones + " where (change_id = 0)";
		try {
			Statement statement = db.connection.createStatement();
			statement.executeUpdate(sql);
			db.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		
		
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
	 * @return the machineLatitude
	 */
	public double getMachineLatitude() {
		return machineLatitude;
	}

	/**
	 * @param machineLatitude the machineLatitude to set
	 */
	public void setMachineLatitude(double machineLatitude) {
		this.machineLatitude = machineLatitude;
	}

	/**
	 * @return the machineLongitude
	 */
	public double getMachineLongitude() {
		return machineLongitude;
	}

	/**
	 * @param machineLongitude the machineLongitude to set
	 */
	public void setMachineLongitude(double machineLongitude) {
		this.machineLongitude = machineLongitude;
	}

	/**
	 * @return the numRows
	 */
	public int getNumRows() {
		return numRows;
	}

	/**
	 * @return the numCols
	 */
	public int getNumCols() {
		return numCols;
	}

	/**
	 * @return the items
	 */
	public Item[][] getItems() {
		return items;
	}

	/**
	 * @return the itemsPerTube
	 */
	public int getItemsPerTube() {
		return ITEMS_PER_TUBE;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param rowSelect the row to set
	 */
	public void setRow(int rowSelect) {
		this.row = rowSelect;
	}

	/**
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @param colSelect the col to set
	 */
	public void setCol(int colSelect) {
		this.col = colSelect;
	}

	/**
	 * @return the payment
	 */
	public Payment getPayment() {
		return payment;
	}

	// Class methods

	/**
	 * @return the transactions
	 */
	public List<Transaction> getTransactions() {
		return transactions;
	}

	/**
	 * @return the root
	 */
	public Administrator getRoot() {
		return root;
	}

	/**
	 * @param root the root to set
	 */
	public void setRoot(Administrator root) {
		this.root = root;
	}

	/**
	 * @return the machineInitialized
	 */
	public boolean isMachineInitialized() {
		return machineInitialized;
	}

	/**
	 * @param machineInitialized the machineInitialized to set
	 */
	public void setMachineInitialized(boolean machineInitialized) {
		this.machineInitialized = machineInitialized;
	}

	/**
	 * @return the machineFull
	 */
	public boolean isMachineFull() {
		return machineFull;
	}

	/**
	 * @param machineFull the machineFull to set
	 */
	public void setMachineFull(boolean machineFull) {
		this.machineFull = machineFull;
	}

	/**
	 * returns the machine ID with the latitude and longitude of its location
	 */
	public String toString() {
		return this.machineID + ": lat " + this.machineLatitude + ", long " + this.machineLongitude;
	}

	// Class methods
	/**
	 * method that perpetually runs an initialized machine
	 */
	public void runMachine() {
		if(this.isMachineInitialized()) {
			do {
				
				//Show machine customer interface
				FrontEnd.displayMachineInterface(this.items);
				
				//Get the selection from the user (e.g. "B3")
				this.selection = FrontEnd.getItemSelection(root.getPassCode());
				
				//Check for entry of admin password
				if(this.selection.toUpperCase().equals(root.getPassCode().toUpperCase())) {
					//Open the admin screen
					root.runAdmin();
				}
				else {
					//Convert the selection into a row-column reference for the items array
					setRow(FrontEnd.selectionToRow());
					setCol(FrontEnd.selectionToCol());
					
					//Purchase and dispense items
					this.purchaseItem();				
				}
			} while(root.isMachineRunning());
		}
		else {
			System.out.println("\n*** ERROR: Machine uninitialized. Stopping.");
		}
	}

	public void removeQuarters(double amount) {
		int amt = (int) amount;
		setQuarters(getQuarters() - amt);
		
	}
	public void removeOnes(int amount) {
		setOnes(getOnes() - amount);
		
	}
	
	
	public void doFillChange() {
		setQuarters(200);
		setOnes(100);
	}
	
	
	/**
	 * the primary method for purchasing items from the machine gets a selection
	 * from the user, determines if the item is available, gets cash payment,
	 * dispenses item, updates inventory, and records the transaction for
	 * administrator analysis
	 */
	private void purchaseItem() {
		//Check availability of item: if available, get payment and dispense item;
		//if not, alert user
		if(itemIsAvailable()) {
			//Tell customer what item is selected
			FrontEnd.showPurchasedItem(items[getRow()][getCol()].getDescription());
			
			//Get payment type from user
			int payType = getPaymentType();
			if(payType == 1) {
				payment = new Cash(items[getRow()][getCol()].getSalesPrice());
			}
			else if(payType == 2) {
				payment = new Electronic(items[getRow()][getCol()].getSalesPrice());
			}
			else if(payType == 3) {
				payment = new CreditCard(items[getRow()][getCol()].getSalesPrice());
			}
			payment.doPayment();

			// Dispense item
			dispenseItem();

			// Update inventory of the item
			decrementInventory();
			this.setMachineFull(false);

			// Record the transaction
			recordTransaction();
		} else {
			FrontEnd.showItemNotAvailable(items[getRow()][getCol()].getDescription());
		}
	}
	
	private int getPaymentType() {
		FrontEnd.showPaymentMenu();
		return FrontEnd.getIntFromUser(1, 3, "\n Payment must be cash or phone");
	}

	/**
	 * determines if an item is available
	 * 
	 * @return the boolean value of item inventory > 0
	 */
	private boolean itemIsAvailable() {
		return items[getRow()][getCol()].getCurrentInventory(items[getRow()][getCol()].getDescription()) > 0;
	}

	/**
	 * dispenses an item to the user (simulated since no physical machine) outputs a
	 * message to the user
	 */
	private void dispenseItem() {
		// Dispense the item to the user
		System.out.println("\n<<<< whirring >>>>");
		System.out.println("<<<< clunk >>>>");
		System.out.println("\n" + items[getRow()][getCol()].getConsumerMessage());
	}

	/**
	 * decrements the item inventory and alerts the administrator of low inventory
	 */
	private void decrementInventory() {
		// Decrement the item inventory
		int currentInventory = items[getRow()][getCol()].getCurrentInventory(items[getRow()][getCol()].getDescription());
		//System.out.println("current: " + currentInventory);
		int newInventory = currentInventory - 1;
		//System.out.println("new: " + newInventory);
		items[getRow()][getCol()].setCurrentInventory(newInventory,items[getRow()][getCol()].getDescription());

		// Alert administrator of low inventory
		if (items[getRow()][getCol()].getCurrentInventory(items[getRow()][getCol()].getDescription()) <= items[getRow()][getCol()].getMinStockLevel()) {
			this.alertLowStock();
		}
	}

	/**
	 * adds a transaction to the transaction list
	 */
	private  void recordTransaction() {
		transactions.add(
			new Transaction(this.machineID,
			items[getRow()][getCol()].getDescription(),
			this.selection,
			items[getRow()][getCol()].getCost(),
			items[getRow()][getCol()].getSalesPrice())
		);
	}

	/**
	 * Alerts the machine administrator of low inventory for an item
	 */
	private void alertLowStock() {
		EmailService email = new EmailService();
		//Administrator admin = new Administrator();
		if (!items[getRow()][getCol()].isAdminAlerted()) {
			System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println(" Messaging administrator:");
			try {
				EmailService.sendEmail(root.getEmail(), items[getRow()][getCol()].getDescription());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//messageAdmin("Low stock of " + items[getRow()][getCol()].getDescription());
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

			items[getRow()][getCol()].setAdminAlerted(true);
		}
	}

	/**
	 * sends message to machine administrator
	 * 
	 * @param message
	 */
	private void messageAdmin(String message) {
		try {
			System.out.println(" Sending message to " + root.getEmail() + "\n \"" + message + "\"");
			Runtime.getRuntime().exec("sendemail -f vending.machine.cst135@gmail.com -m \"" + message + "\"  -t "
				+ root.getEmail()
				+ "@vtext.com -s smtp.gmail.com:587 -xu vending.machine.cst135@gmail.com -xp vendingMachine135");
			System.out.println("\n <<<< swoosh....message sent >>>>");
		} catch (IOException e) {
			System.out.println("\n <<<< UNABLE TO SEND MESSAGE >>>>");
			//e.printStackTrace();
		}
	}
}
