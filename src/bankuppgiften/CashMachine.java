package bankuppgiften;

public class CashMachine {
    private boolean userAuthenticated;
    private int currentAccountNumber;
    private Screen screen;
    private Keypad keypad;
    private CashDispenser cashDispenser;
    private DepositSlot depositSlot;
    private DataHolder bankDatabase;
    
    //main menu options
    private static final int BALANCE_REQUEST = 1;
    private static final int WITHDRAWAL = 2;
    private static final int DEPOSIT = 3;
    private static final int EXIT = 4;
    
    public CashMachine(){
        userAuthenticated = false;
        currentAccountNumber = 0;
        screen = new Screen();
        keypad = new Keypad();
        cashDispenser = new CashDispenser();
        depositSlot = new DepositSlot();
        bankDatabase = new DataHolder();
        
        //two test acocunts
        createUser(123, "pin", 1200, 1500);
        createUser(345, "pin", 900, 1800);
    }
    //////////////////////////////////////////////
    
    public void run(){
        //authenticates user; perform transactions
        while(true){
            //loops until user is not authenticated
            while(!userAuthenticated){
                screen.displayMessageLine("\nWelcome!");
                authenticateUser();
            }
            
            performTransactions();
            userAuthenticated = false;
            currentAccountNumber = 0;
            screen.displayMessage("\nThank you! Goodbye!");
        }
    }
    private void createUser(int AccountNumber, String PIN, 
            int balance, int totalBalance){
        bankDatabase.addAccount(new Account(AccountNumber, PIN, balance, totalBalance));
    }
    private void authenticateUser(){
        screen.displayMessage("\nPlease enter your account number: ");
        int accountNumber = keypad.getInt();
        screen.displayMessage("\nEnter your Pin: ");
        String pin = keypad.getString();
        
        userAuthenticated = bankDatabase.authenticateUser(accountNumber, pin);
        
        //check if authentication was succsessful
        if(userAuthenticated){
            currentAccountNumber = accountNumber;
        } else{
            screen.displayMessageLine(
                    "Invalid account number or PIN. Please try again");
        }        
    }
    private void performTransactions(){
        Transaction currentTransaction = null; //store current transaction
        boolean userExited = false; //user has not chosen to exit yet
        
        //while user has not chosen option to exit
        while (!userExited){
            int mainMenuSelection = displayMainMenu();
            
            switch(mainMenuSelection){
                case BALANCE_REQUEST:
                case WITHDRAWAL:
                case DEPOSIT:
                    currentTransaction = 
                            createTransaction(mainMenuSelection);
                    currentTransaction.execute();
                    break;
                case EXIT:
                    screen.displayMessageLine("\nExiting the system..");
                    userExited = true; 
                    break;
                default:
                    screen.displayMessageLine(
                    "\nYou did not enter a valid selection. Try again!");
                    break;        
            }
        }
    }
    private int displayMainMenu(){
        screen.displayMessageLine("\nMain Menu:");
        screen.displayMessageLine("1 - View my balance  ");
        screen.displayMessageLine("2 - Withdraw cash    ");
        screen.displayMessageLine("3 - Deposit founds   ");
        screen.displayMessageLine("4 - Exit\n           ");
        screen.displayMessage("Enter a choice: ");
        return keypad.getInt();
    }
    private Transaction createTransaction(int type){
        Transaction temp = null;
        
        switch(type){
            case BALANCE_REQUEST:
                temp = new RequestBalance(
                    currentAccountNumber, screen, bankDatabase);
                break;
            case WITHDRAWAL:
                temp = new Withdrawal(
                    currentAccountNumber, screen, bankDatabase, keypad, cashDispenser);
                break;
            case DEPOSIT:
                temp = new Deposit(
                    currentAccountNumber, screen, bankDatabase, keypad, depositSlot);    
                break;
        }
        return temp;
    }         
}//end of ATM()
