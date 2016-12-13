/**
 * Created by RdDvls on 12/13/16.
 */
public class Savings extends BankAccount implements Runnable {
    private String accountName;
    private double balance;
    private int type;

    public Savings(String accountName, double balance, int type) {
        this.accountName = accountName;
        this.balance = balance;
        this.type = type;

        Thread savingsThread = new Thread(this);
        savingsThread.start();
    }

    public double interest(){
        setAccountBalance(getAccountBalance() + 1.05);
        return getAccountBalance();
    }

    public void run(){
        try{
            System.out.println("Running savings thread...");
            while (BankingSystemRunner.runInterestThread = true){
                setAccountBalance(getAccountBalance() *1.05);
                Thread.sleep(15000);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
