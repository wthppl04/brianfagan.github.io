package cst135.groupprojectpwrc;

public class CreditCard extends Payment {

    public CreditCard(double balanceOwed) {
        super(balanceOwed);
        // TODO Auto-generated constructor stub
    }

    //private double balanceOwed;
    

    
    /**
     * processes the phone payment from the user by keeping track of how much is owed and
     * dispensing change
     */
    public void doPayment() {
        getPaymentAmount();
    }
    
    /**
     *
     * @return the dollar amount the user "input"
     */
    public double getPaymentAmount() {
        System.out.println("\n" + FrontEnd.makeHeaderString(32, '$'));
        System.out.println("Swipe your card please");
        System.out.println("\n<< Processing >>");
        System.out.println("\nThank you!");
        System.out.println(FrontEnd.makeHeaderString(32, '$'));
        
        return 0;
    }    
}