import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by RdDvls on 12/13/16.
 */
public class Bank {
    private String bankName = "First Bank Of Sampson";
    ArrayList <Customer> accountHolders = new ArrayList<Customer>();
    Scanner inputScanner = new Scanner(System.in);

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public ArrayList<Customer> getAccountHolders() {
        return accountHolders;
    }

    public void setAccountHolders(ArrayList<Customer> accountHolders) {
        this.accountHolders = accountHolders;
    }

    public void createUser(){
        System.out.println("Welcome new customer!");
        System.out.println("\tPlease enter your name: ");
        Customer newCustomer = new Customer(inputScanner.nextLine());
        accountHolders.add(newCustomer);
    }

    public void createAccount() {
        int accountChoice;
        double initialDeposit;
        int index = 1;
        System.out.println("Please select a customer:");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        for (Customer currentCustomer : accountHolders) {
            System.out.println(index + ": " + currentCustomer.getUserName());
            index++;
        }
        int customerChoice = Integer.valueOf(inputScanner.nextLine());
        Customer myCustomer = accountHolders.get(customerChoice-1);


        while (true) {
            System.out.println("Please choose an account to open\n");
            System.out.println("1.Checking");
            System.out.println("2.Savings");
            System.out.println("3.Retirement");
            System.out.println("========");
            System.out.println("0.Exit");
            System.out.println("========");
            accountChoice = Integer.valueOf(inputScanner.nextLine());
            if (accountChoice == 1) {
                System.out.println("Please name your Checking account");
                String acctName = inputScanner.nextLine();
                System.out.println("How much money would you like to put into your account?\n");
                initialDeposit = Double.valueOf(inputScanner.nextLine());
                myCustomer.customerAccounts.put(acctName, new Checking(acctName, initialDeposit,1));
                System.out.println(myCustomer.customerAccounts.get(acctName).getAccountBalance());

            } else if (accountChoice == 2) {
                System.out.println("Please name your Savings account");
                String acctName = inputScanner.nextLine();
                System.out.println("How much money would you like to put into your account?");
                initialDeposit = Double.valueOf(inputScanner.nextLine());
                myCustomer.customerAccounts.put(acctName, new Savings(acctName, initialDeposit, 2));
                System.out.println(myCustomer.customerAccounts.get(acctName).getAccountBalance());

            } else if (accountChoice == 3) {
                System.out.println("Please name your Retirement account");
                String acctName = inputScanner.nextLine();
                System.out.println("How much money would you like to put into your account?");
                initialDeposit = Double.valueOf(inputScanner.nextLine());
                myCustomer.customerAccounts.put(acctName, new Retirement(acctName, initialDeposit, 3));
                System.out.println(myCustomer.customerAccounts.get(acctName).getAccountBalance());

            }else if(accountChoice == 0){
                break;
            }
        }
    }

    public void printInfo() {
        int counter = 1;
        System.out.println(getBankName());
        for (Customer customer : accountHolders){
            System.out.println(customer.getUserName());
        }

        for (Customer myCustomer : accountHolders) {
            for (HashMap.Entry<String, BankAccount> currAcct : myCustomer.customerAccounts.entrySet()) {
                System.out.println(counter++ + " - " + currAcct.getKey() + " : " + currAcct.getValue().getAccountBalance());
            }
        }

    }

    public void writeBank (Bank myBank){
        FileWriter bankWriter = null;
        FileWriter customerWriter = null;
        try{
            File bankFile = new File("bank.txt");
            File accountFile = new File("customer-name-accounts.txt");
            bankWriter = new FileWriter(bankFile);
            for(Customer myCustomers : accountHolders){
                bankWriter.write(myCustomers.getUserName() + ",");
                File customerFile = new File(myCustomers.getUserName() + ".txt");
                customerWriter = new FileWriter(customerFile);
                for(HashMap.Entry<String, BankAccount> currentAccount : myCustomers.customerAccounts.entrySet()){
                    customerWriter.write("Account Name = " + currentAccount.getKey() + "\n" + "Account Balance = " + currentAccount.getValue().getAccountBalance() + "\n" + "Account Type = " + currentAccount.getValue().getType() + "\n");
                }
                customerWriter.close();
            }
            bankWriter.close();
        }catch (Exception ex){
            System.out.println("Bank Error");
            ex.printStackTrace();
        }finally {
            if(bankWriter != null){
                try{
                    bankWriter.close();
                }catch (Exception ex){
                    System.out.println("Bank Error");
                    ex.printStackTrace();
                }
            }
        }
    }

    public void readBank(ArrayList<Customer> accountHolders){
        try{
            HashMap<String, BankAccount> customerAccounts;
            File bankFile = new File("bank.txt");
            Scanner fileScanner = new Scanner(bankFile);
            while(fileScanner.hasNext()){
                String nextLine = fileScanner.nextLine();
                String[] currName = nextLine.split(",");
                for(String currentName : currName){
                    Customer myCustomer = new Customer(currentName);
                    customerAccounts = new HashMap<>();
                    accountHolders.add(myCustomer);
                    File accountFile = new File(currentName + ".txt");
                    Scanner accountScanner = new Scanner(accountFile);
                    while (accountScanner.hasNext()){
                        String accountName = accountScanner.nextLine().split("=")[1];
                        double balance = Double.valueOf(accountScanner.nextLine().split("=")[1]);
                        int type = Integer.valueOf(accountScanner.nextLine().split("=")[1]);
                        if(type == 1){
                            BankAccount myAccount = new Checking(accountName,balance,type);
                            customerAccounts.put(accountName, myAccount);
                        }else if (type == 2){
                            BankAccount myAccount = new Savings(accountName,balance,type);
                            customerAccounts.put(accountName,myAccount);
                        }else if (type == 3){
                            BankAccount myAccount = new Retirement(accountName,balance,type);
                            customerAccounts.put(accountName,myAccount);
                        }
                        System.out.println("Recovered Account");
                    }
                    myCustomer.setCustomerAccounts(customerAccounts);
                }
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
    public void accountInteract(){
        while(true){
            int index = 1;
            for(Customer currentCustomer : accountHolders){
                System.out.println(index + ":" + currentCustomer.getUserName());
                index++;
            }
            int customerChoice = Integer.valueOf(inputScanner.nextLine());
            Customer myCustomer = accountHolders.get(customerChoice -1);

            System.out.println("Which account would you like to access?");
            System.out.println("Enter \"exit\" to exit\n");
            for(String accountName : myCustomer.customerAccounts.keySet()){
                System.out.println(accountName);
            }
            String userChoice = inputScanner.nextLine();
            System.out.println("You chose: "+ userChoice);
            if(userChoice == null || userChoice.equalsIgnoreCase("exit")){
                break;
            }
            BankAccount userChosenAccount = myCustomer.getCustomerAccounts().get(userChoice);
            if(userChosenAccount == null){
                System.out.println("Try Again");
            }else{
                while(true){
                    System.out.println("What would you like to do?");
                    System.out.println("1. Deposit");
                    System.out.println("2. Withdraw");
                    System.out.println("3. Print Info");
                    System.out.println("4. Transfer Between Accounts");
                    System.out.println("5. Exit");
                    int bankingChoice = Integer.valueOf(inputScanner.nextLine());
                    if(bankingChoice == 1){
                        System.out.println("\tHow much would you like to desposit?");
                        double depositAmount = Double.valueOf(inputScanner.nextLine());
                        userChosenAccount.deposit(depositAmount);
                    }else if(bankingChoice == 2){
                        System.out.println("\tHow much would you like to withdraw?");
                        double withdrawAmount = Double.valueOf(inputScanner.nextLine());
                        userChosenAccount.withdraw(withdrawAmount);
                    }else if(bankingChoice == 3){
                        userChosenAccount.getInfo();
                    }else if(bankingChoice == 4){
                        System.out.println("How much would you like to transfer?\n");
                        double toTransfer = Double.valueOf(inputScanner.nextLine());
                        System.out.println("Which account would you like to transfer to?\n");
                        for(String accountName : myCustomer.customerAccounts.keySet()){
                            System.out.println(accountName);
                        }
                        String transferChoice = inputScanner.nextLine();
                        System.out.println("We will transfer " + toTransfer + " to " + transferChoice);
                        BankAccount transferAccount = myCustomer.customerAccounts.get(transferChoice);
                        if(transferAccount == null){
                            System.out.println("Account Does Not Exist");
                            break;
                        }else userChosenAccount.withdraw(toTransfer);
                        transferAccount.deposit(toTransfer);
                        System.out.println("Transfer Complete");
                    }else if(bankingChoice == 5){
                        break;
                    }
                }
            }
        }
    }

}
