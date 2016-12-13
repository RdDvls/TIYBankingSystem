import java.util.Scanner;

/**
 * Created by RdDvls on 12/13/16.
 */
public class BankingSystemRunner {
    public static boolean runInterestThread = true;

    public static void main(String[] args) {
        Bank myBank = new Bank();
        Scanner inputScanner = new Scanner(System.in);
//        myBank.readBank(myBank.accountHolders);
        System.out.println("Welcome to " +myBank.getBankName());
        while(true){
            System.out.println("\t1. Sign In");
            System.out.println("\t2. Add A Customer");
            System.out.println("\t3. Create An Account");
            System.out.println("\t4. Check Bank Info");
            System.out.println("\t5. Exit");
            int menuChoice = Integer.valueOf(inputScanner.nextLine());
            if(menuChoice ==1){
                myBank.accountInteract();
            }else if(menuChoice ==2){
                myBank.createUser();
            }else if(menuChoice == 3){
                myBank.createAccount();
            }

        }
    }
}
