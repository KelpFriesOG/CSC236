package labs.lab1;

public class BankAccount {

    // Instance variable
    double balance = 0;

    // #region Constructors

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public BankAccount() {

    }

    // #endregion

    // #region Methods

    void withdraw(double amount) throws BankAccountException {

        if (amount > balance) {
            throw new BankAccountException("Insufficent funds in account.");
        } else {
            balance -= amount;
        }

    }

    void deposit(double amount) throws BankAccountException {

        if (amount < 0) {
            throw new BankAccountException("Cannot deposit a negative amount of money!");
        } else {
            balance += amount;
        }

    }

    public double getBalance() {
        return balance;
    }

    // #endregion

}
