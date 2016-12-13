import java.time.*;

/**
 * Created by RdDvls on 12/13/16.
 */

public abstract class BankAccount {
    private String accountName;
    private double accountBalance = 0.00;
    private int type;
    private float lastTransactionDate;

    public float getLastTransactionDate() {
        return lastTransactionDate;
    }

    public void setLastTransactionDate(float lastTransactionDate) {
        this.lastTransactionDate = lastTransactionDate;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double withdraw(double withdrawAmount){
        accountBalance -= withdrawAmount;
        return accountBalance;
    }

    public double deposit(double depositAmount){
        accountBalance += depositAmount;
        return accountBalance;
    }

    public void getInfo(){
        System.out.println(getAccountName());
        System.out.println(getAccountBalance());
    }
}
