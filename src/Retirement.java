/**
 * Created by RdDvls on 12/13/16.
 */
public class Retirement extends BankAccount implements Runnable{
    private String accountName;
    private double balance;
    private int type;

    public Retirement(String accountName, double balance, int type) {
//        this.accountName = accountName;
        setAccountName(accountName);
//        this.balance = balance;
        this.setAccountBalance(balance);
//        this.type = type;
        this.setType(type);
        Thread retirementThread = new Thread(this);
        retirementThread.start();
    }

    public double interest(){
        setAccountBalance(getAccountBalance() * 1.10);
        return getAccountBalance();
    }


    public void run(){
        try{
            while(BankingSystemRunner.runInterestThread){
                System.out.println("Running retirement thread...");
                setAccountBalance(getAccountBalance() * 1.10);
                Thread.sleep(120000);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
