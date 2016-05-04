
package bankuppgiften;

public class RequestBalance extends Transaction{

    public RequestBalance(int userAccountNumber, Screen atmScreen, DataHolder atmBankDatabase) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
    }

    @Override
    public void execute() {
        DataHolder bankDatabase = getBankDatabase();
        Screen screen = getScreen(); 
        double availableBalance =
                bankDatabase.getAvailableBalance(getAccountNumber());
        double totalBalance = 
                bankDatabase.getTotalBalance(getAccountNumber());
        
        //display the balance information on screen
        screen.displayMessageLine("\nBalance Information: ");
        screen.displayMessage(" - Available balance: ");
        screen.displayDollarAmount(availableBalance);
        screen.displayMessage("\n - Total balance: ");
        screen.displayDollarAmount(totalBalance);
        screen.displayMessageLine("");
    }
}
