/**
 * Created by RdDvls on 12/13/16.
 */
public class Retirement extends BankAccount implements Runnable{
    private String accountName;
    private double balance;
    private int type;

    public Retirement(String accountName, double balance, int type) {
        this.accountName = accountName;
        this.balance = balance;
        this.type = type;
        Thread retirementThread = new Thread(this);
        retirementThread.start();
    }

    public double interest(){
        setAccountBalance(getAccountBalance() * 1.10);
        return getAccountBalance();
    }


    public void run(){
        try{
            System.out.println("Running retirement thread...");
            while(BankingSystemRunner.runInterestThread = true){
                setAccountBalance(getAccountBalance() *1.10);
                Thread.sleep(120000);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
