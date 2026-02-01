import java.util.Scanner;

// Class to represent user's bank account
class BankAccount {
    private double balance;

    // Constructor
    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("✅ Amount deposited successfully.");
        } else {
            System.out.println("❌ Invalid deposit amount.");
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("❌ Insufficient balance.");
        } else if (amount <= 0) {
            System.out.println("❌ Invalid withdrawal amount.");
        } else {
            balance -= amount;
            System.out.println("✅ Please collect your cash.");
        }
    }

    // Check balance method
    public void checkBalance() {
        System.out.println("💰 Current Balance: ₹" + balance);
    }
}

// Class to represent ATM machine
public class ATMInterface {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankAccount account = new BankAccount(5000); // initial balance

        int choice;

        System.out.println("🏦 Welcome to ATM Machine");

        do {
            System.out.println("\n--- ATM MENU ---");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to withdraw: ₹");
                    double withdrawAmount = sc.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;

                case 2:
                    System.out.print("Enter amount to deposit: ₹");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                    break;

                case 3:
                    account.checkBalance();
                    break;

                case 4:
                    System.out.println("👋 Thank you for using ATM.");
                    break;

                default:
                    System.out.println("❌ Invalid option. Try again.");
            }

        } while (choice != 4);

        sc.close();
    }
}
