package bankuppgiften;
public abstract class Transaction {
    private int accountNumber; 
    private Screen screen;
    private DataHolder bankDatabase;
    
    public Transaction (int userAccountNumber, Screen atmScreen,
            DataHolder atmBankDatabase){
        accountNumber = userAccountNumber;
        screen = atmScreen;
        bankDatabase = atmBankDatabase;
    }
    //////////////////////////////////////////////

    public int getAccountNumber() {
        return accountNumber;
    }

    public Screen getScreen() {
        return screen;
    }

    public DataHolder getBankDatabase() {
        return bankDatabase;
    }
    abstract public void execute(); //abstract forces subclasses to overwrite
}
