package bankuppgiften;
public class Account {
    private int accountNumber;
    private String pin;
    private double availableBalance;
    private double totalBalance;
    
    public Account(int theAccountNumber, String thePIN,
            double theAvailableBalance, double theTotalBalance){
        accountNumber = theAccountNumber;
        pin = thePIN;
        availableBalance = theAvailableBalance;
        totalBalance = theTotalBalance;
    }
    ////////////////////////////////////////////////////////////
    
    public double getAvailableBalance() {
        return availableBalance;
    }
    public double getTotalBalance() {
        return totalBalance;
    }
    public int getAccountNumber() {
        return accountNumber;
    }
    public boolean validatePIN(String userPIN){
        return (userPIN == null ? pin == null : userPIN.equals(pin));
    }
    public void credit(double amount){
        totalBalance += amount;
    }
    public void debit(double amount){
        availableBalance -= amount;
        totalBalance -= amount;
    }
}
