import java.util.HashMap;

/**
 * Created by RdDvls on 12/13/16.
 */
public class Customer {
    private String userName;
    HashMap<String, BankAccount> customerAccounts = new HashMap<>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public HashMap<String, BankAccount> getCustomerAccounts() {

        return customerAccounts;
    }

    public void setCustomerAccounts(HashMap<String, BankAccount> customerAccounts) {
        this.customerAccounts = customerAccounts;
    }

    public Customer(String userName) {
        this.userName = userName;
    }
}
