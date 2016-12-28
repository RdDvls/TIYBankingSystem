/**
 * Created by RdDvls on 12/13/16.
 */
public class Savings extends BankAccount implements Runnable {
    private String accountName;
    private double balance;
    private int type;

    public Savings(String accountName, double balance, int type) {
//        this.accountName = accountName;
        setAccountName(accountName);
//        this.balance = balance;
        this.setAccountBalance(balance);
        this.setType(type);
//        this.type = type;

        Thread savingsThread = new Thread(this);
        savingsThread.start();
    }

    public double interest(){
        setAccountBalance(getAccountBalance() + 1.05);
        return getAccountBalance();
    }

    public void run(){
        try{
            while (BankingSystemRunner.runInterestThread){
                System.out.println("Running savings thread...");
                setAccountBalance(getAccountBalance() *1.05);
                Thread.sleep(15000);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
