package bankuppgiften;

import java.util.ArrayList;

public class DataHolder {
    private ArrayList<Account> databaseAccounts;
    
    public DataHolder(){
        databaseAccounts = new ArrayList<>();
    }
    /////////////////////////////////////////////////////////////
            
    public final void addAccount(Account newAccount){
        this.databaseAccounts.add(newAccount);
    }     
    public Account getAccount (int accountNumber){
        for(Account currentAccount : databaseAccounts){
            if(currentAccount.getAccountNumber() == accountNumber){
                return currentAccount;
            }
        }
        return null; //TODO Change to throw exception!
    }
    public boolean authenticateUser(int userAccountNumber, String userPIN){
        Account userAccount = getAccount(userAccountNumber);
        if(userAccount != null){
            return userAccount.validatePIN(userPIN);
        } else return false;
    }
    public double getAvailableBalance(int userAccountNumber){
        return getAccount(userAccountNumber).getAvailableBalance();
    }
    public double getTotalBalance(int userAccountNumber){
        return getAccount(userAccountNumber).getTotalBalance();
    }
    public void credit(int userAccountNumber, double amount){
        getAccount(userAccountNumber).credit(amount);
    }
    public void debit(int userAccountNumber, double amount){
        getAccount(userAccountNumber).debit(amount);
    }    
}
