package cst135.groupprojectpwrc;

public abstract class Payment {
	//Money menu
	//Track deposit / remainder
	//Dispense change
	private double balanceOwed;
	
	/**
	 * constructor for Payment objects
	 * @param balanceOwed the amount the customer owes before receiving the selected item
	 */
	public Payment(double balanceOwed) {
		this.balanceOwed = balanceOwed;
	}
	
	/**
	 * @return the balanceOwed
	 */
	public double getBalanceOwed() {
		return balanceOwed;
	}

	/**
	 * @param balanceOwed the balanceOwed to set
	 */
	public void setBalanceOwed(double balanceOwed) {
		this.balanceOwed = balanceOwed;
	}

	/**
	 * processes a payment from the user by keeping track of how much is owed
	 */
	public abstract void doPayment();
	
	/**
	 * gets the payment amount from the user in either cash or electronic form
	 * @return the amount of the payment from the user
	 */
	public abstract double getPaymentAmount();	
}
