/**
 * Created by RdDvls on 12/13/16.
 */
public class Checking extends BankAccount {

    private String accountName;
    private double balance;
    private int type;

    public Checking(String accountName, double balance, int type){
        setAccountName(accountName);
        this.setAccountBalance(balance);
        this.setType(type);
    }

}
