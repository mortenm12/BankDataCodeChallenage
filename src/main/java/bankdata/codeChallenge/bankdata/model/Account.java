package bankdata.codeChallenge.bankdata.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private int accountNumber;

    private String firstName;

    private String lastName;

    private double balance; //double may not be the most precise value for saving money

    public Account(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        balance = 0;
    }

    private Account(){}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance (double balance) {
        this.balance = balance; //this is bad, balance can be manipulated without accountService involvement
    }
}
