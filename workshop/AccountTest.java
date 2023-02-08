package workshop;

public class AccountTest {

    public static void main(String[] args) {

        Account myAcc = new Account(1122, 20000);
        myAcc.setAnnualInterestRate(0.045);

        myAcc.withdraw(2500);
        myAcc.deposit(3000);

        System.out.println("The balance of the account: " + myAcc.getBalance());

        System.out.println("The monthly interest based on current balance: "
                + myAcc.getMonthlyInterest());

        System.out.println("The date the account was created on: " + myAcc.getDateCreated().toString());

        

    }

}
