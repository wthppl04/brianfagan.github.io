package cst135.groupprojectpwrc;

import java.util.List;

import org.apache.commons.lang3.StringUtils;


public class Administrator {
	//This class will allow an administrator to perform a variety
	//of functions on a vending machine, such as restocking,
	//reconfiguring, computing profit, etc.
	
	private String passCode;
	private String email;
	private boolean machineRunning;
	private VendingMachine vm;
	public static Cash cash;
	
	
	public Administrator(String passCode, String email) {
		this.passCode = passCode;
		this.email = email;
		this.machineRunning = true;
	}
	
	public Administrator() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the passCode
	 */
	public String getPassCode() {
		return passCode;
	}

	/**
	 * @param passCode the passCode to set
	 */
	public void setPassCode(String passCode) {
		this.passCode = passCode;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the runMachine
	 */
	public boolean isMachineRunning() {
		return machineRunning;
	}

	//Class methods
	/**
	 * initializes a vending machine by loading it with items
	 * @param vm the vending machine to initialize
	 */
	public void doInitializeMachine(VendingMachine vm) {
		//Set the VendingMachine instance
		
		this.vm = vm;
		
		
		
		//Load the machine with tasty items for sale
		doLoadMachine();
		
		//Set the machine to initialized so it can run
		vm.setMachineInitialized(true);
		vm.setMachineFull(true);
	}
	
	/**
	 * shows the cash menu
	 * @param balanceOwed the remaining balance to be paid for the item
	 */
	private void showAdminMenu() {
		System.out.println("\nrootrootrootrootrootrootrootroot");
		System.out.println("       ADMINISTRATOR MENU");
		System.out.println("rootrootrootrootrootrootrootroot");
		System.out.println(" 1. Restock All Items");
		System.out.println(" 2. Reconfigure Items");
		System.out.println(" 3. View Transactions");
		System.out.println(" 4. Refill Change");
		System.out.println(" 5. Shutdown Machine");
		System.out.println("-------------------------");
		System.out.println(" 0. Exit to Machine");
		System.out.println("-------------------------");
		System.out.println(" Selection:");
	}
	
	/**
	 * primary method for performing machine administration functions
	 * can run only with a vending machine that has been initialized
	 */
	public void runAdmin() {
		if(this.vm == null) {
			System.out.println("\n*** ERROR: Machine not initialized.");
		}
		else {
			boolean adminLoggedIn = true;
			final int EXIT_MENU = 0;
			final int MIN_MENU = 1;
			final int MAX_MENU = 4;
			do {
				adminLoggedIn = true;
				showAdminMenu();
				switch(FrontEnd.getIntFromUser(EXIT_MENU, MAX_MENU,
					"Oops, enter a value from " + MIN_MENU + " to " + MAX_MENU + " or " + EXIT_MENU + " to exit admin mode")) {
					case 1:
						doRestockAllItems();
						break;
					case 2:
						System.out.println("  Reconfiguring items COMING SOON");
						break;
					case 3:
						this.doPrintTransactions();
						break;
					case 4:
						vm.doFillChange();
						break;
					case 5:
						System.out.println("\n  *** Shutting down machine...");
						this.machineRunning = false;
						adminLoggedIn = false;
						break;
					case 0:
						adminLoggedIn = false;
						break;
				}
			} while(adminLoggedIn);
		}
	}
	
	/**
	 * checks inventory of each item in the machine and replenishes any
	 * tubes that are not full
	 */
	private void doRestockAllItems() {
		System.out.println("\n  Replenishing all stock:");
		System.out.println("  " + FrontEnd.makeHeaderString(29, '-'));
		if(vm.isMachineFull()) {
			System.out.println("  Machine already full");
		}
		else {
			for(int r = 0; r < vm.getNumRows(); r++) {
				for(int c = 0; c < vm.getNumCols(); c++) {
					if(vm.getItems()[r][c].getCurrentInventory(vm.getItems()[r][c].getDescription()) < vm.getItemsPerTube()) {
						System.out.println("  Restocking " + vm.getItems()[r][c].getDescription());
						vm.getItems()[r][c].setCurrentInventory(vm.getItemsPerTube(), vm.getItems()[r][c].getDescription());
					}
				}
			}
			//Reset the machine full flag
			vm.setMachineFull(true);
		}
		System.out.println("  " + FrontEnd.makeHeaderString(29, '-'));
	}
	
	/**
	 * Loads all items into the machine
	 * @param vm a vending machine object into which items will be loaded
	 */
	private void doLoadMachine() {
		
	Queries q = new Queries();
	List<Item> list = q.itemList();
		//double cost, String description, double salesPrice, int minStockLevel, int currentInventory
	/*	vm.getItems()[0][0] = new Item(0.25, "BBQ Chips", 0.75, 1, vm.getItemsPerTube(), 150);
		vm.getItems()[0][1] = new Item(0.25, "Nacho Chips", 0.75, 1, vm.getItemsPerTube(), 140);
		vm.getItems()[0][2] = new Item(0.25, "Potato Chips", 0.75, 1, vm.getItemsPerTube(), 160);
		vm.getItems()[1][0] = new Item(0.25, "Rice Krispy", 1.00, 1, vm.getItemsPerTube(), 260);
		vm.getItems()[1][1] = new Item(0.25, "Oreos", 1.25, 1, vm.getItemsPerTube(), 460);
		vm.getItems()[1][2] = new Item(0.25, "Hershey Bar", 1.25, 1, vm.getItemsPerTube(), 600);
		vm.getItems()[2][0] = new Item(0.25, "Pretzels", 1.50, 1, vm.getItemsPerTube(), 110);
		vm.getItems()[2][1] = new Item(0.25, "Beef Jerky", 1.75, 1, vm.getItemsPerTube(), 90);
		vm.getItems()[2][2] = new Item(0.25, "M & Ms", 0.50, 1, vm.getItemsPerTube(), 420);
		*/
		
	/*for (int i = 0; i < list.size(); i++) {
		for(int r = 0; r < 4; r++) {
		//	System.out.println(list.size());
			for(int c = 0; c < 4; c++) {
				
				System.out.println(i);
				vm.getItems()[r][c] = new Item(list.get(i).getCost(), list.get(i).getDescription(), list.get(i).getSalesPrice(), list.get(i).getMinStockLevel(),
						list.get(i).getCurrentInventory(), list.get(i).getCalories());
				
			//	vm.getItems()[r][c] = new Item(list.get(r+c).getCost(), list.get(r+c).getDescription(), list.get(r+c).getSalesPrice(), list.get(r+c).getMinStockLevel(),
				//		list.get(r+c).getCurrentInventory(), list.get(r+c).getCalories());
				
				
			}
		}
	}}*/
	int row = 0;
	int col = 0;
	int max= 0;
	
	
	for (int i = 0; i < list.size(); i++) {
		if (list.get(i).getDescription().length() > max) {
			max = list.get(i).getDescription().length();
		}
	}
	
	
	for (int i = 0; i < list.size(); i++) {
		
		if(i==0) {
			vm.getItems()[row][col] = new Item(list.get(i).getCost(), list.get(i).getDescription(), list.get(i).getSalesPrice(), list.get(i).getMinStockLevel(),
					list.get(i).getCurrentInventory(list.get(i).getDescription()), list.get(i).getCalories());
		}
		else if(i%4 == 0) {
			row++;
			col=0;
			
		}
		vm.getItems()[row][col] = new Item(list.get(i).getCost(), list.get(i).getDescription(), list.get(i).getSalesPrice(), list.get(i).getMinStockLevel(),
				list.get(i).getCurrentInventory(list.get(i).getDescription()), list.get(i).getCalories());
		
	
		col++;
	}
	
	
	}
	
	
	
	/**
	 * replenishes inventory for an item
	 * @param vm the vending machine being restocked
	 * @param row the row reference of the item being replenished
	 * @param col the col reference of the item being replenished
	 */
	public void restockItem(VendingMachine vm, int row, int col) {
		System.out.println("\n" + FrontEnd.makeHeaderString(37, 'A'));
		System.out.println("Replenishing stock for machine "
			
			+ vm.toString()
			+ ", item "
			+ vm.getItems()[vm.getRow()][vm.getCol()].getDescription());
		System.out.println("\n" + FrontEnd.makeHeaderString(37, 'A'));

		//Update the inventory
		vm.getItems()[vm.getRow()][vm.getCol()].setCurrentInventory(vm.getItemsPerTube(),vm.getItems()[vm.getRow()][vm.getCol()].getDescription());
		
		//Reset the alert flag so a new message can be sent about the item
		vm.getItems()[row][col].setAdminAlerted(false);
	}	
	
	/**
	 * prints a list of transactions for a vending machine object
	 */
	private void doPrintTransactions() {
		double totalCost = 0;
		double totalSales = 0;
		
		if(vm.getTransactions().size() == 0) {
			System.out.println("\n" + FrontEnd.makeHeaderString(29, '#'));
			System.out.println(" *** No transactions to print");
			System.out.println(FrontEnd.makeHeaderString(29, '#'));
		}
		else {
			System.out.println("\n" + FrontEnd.makeHeaderString(69, '$'));
			System.out.println("              TRANSACTION REPORT FOR MACHINE " + vm.getMachineID());
			System.out.println("       PERIOD: "
					+ Transaction.dateTime.format(vm.getTransactions().get(0).getTransDate()) + " to "
					+ Transaction.dateTime.format(vm.getTransactions().get(vm.getTransactions().size() - 1).getTransDate())
				);
			System.out.println(FrontEnd.makeHeaderString(69, '$') + "\n");
	
			System.out.println(FrontEnd.makeHeaderString(69, '-'));
			System.out.println("Machine\tDate-Time\t\tPos\tDescription\tCost\tSale");
			System.out.println(FrontEnd.makeHeaderString(69, '-'));
			if(vm.getTransactions().size() > 0) {
				for(Transaction t : vm.getTransactions()) {
					t.printTransaction();
					totalCost += t.getItemCost();
					totalSales += t.getPurchaseAmount();
				}
				System.out.println(FrontEnd.makeHeaderString(69, '-'));
				System.out.format("\t\t\t\t\t\tTOTALS:\t$%.2f\t$%.2f\n", totalCost, totalSales);
				System.out.println(FrontEnd.makeHeaderString(38, '-'));
				System.out.format("| TOTAL PROFIT FOR PERIOD: $%8.2f |\n", (totalSales - totalCost));
				System.out.println(FrontEnd.makeHeaderString(38, '-'));
			}
			else {
				System.out.println("\n" + FrontEnd.makeHeaderString(27, '@'));
				System.out.println("\n There are no transactions to print\n");
				System.out.println(FrontEnd.makeHeaderString(27, '@'));
			}
		}
	}
}
