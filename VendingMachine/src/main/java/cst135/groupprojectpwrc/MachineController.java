package cst135.groupprojectpwrc;

public class MachineController {
	
	/**
	 * method that calls the runMachine method for the vending machine
	 * @param args the String args array (not implemented in this application)
	 */
	public static void main(String[] args) {
		//Make the two required objects to run the machine
		//Connect c = new Connect();
		Queries q = new Queries();
		Administrator root = new Administrator("<<rootkey>>", "charlesrayedwards@gmail.com");
		System.out.println("I N I T I A L I Z I N G . . . . ");
		VendingMachine vm = new VendingMachine(4, 4, root);
		
		
		//Initialize the machine (loads it with tasty items and sets to initialized)
		
		//c.ContactDataSource();
		//q.itemList();
		root.doInitializeMachine(vm);
	
		//Run the machine and rake in the money
		vm.runMachine();
	}
}
