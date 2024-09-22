import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ATM {
    private double balance;
    private String pin;
    private List<String> transactionHistory;

    // Constructor to initialize ATM with an initial balance and PIN
    public ATM(double initialBalance, String initialPin) {
        this.balance = initialBalance;
        this.pin = initialPin;
        this.transactionHistory = new ArrayList<>();
    }

    // Method to check account balance
    public void checkBalance() {
        System.out.println("Current balance: Rs." + balance);
        transactionHistory.add("Checked balance: Rs." + balance);
    }

    // Method to withdraw cash
    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            balance -= amount;
            System.out.println("Withdrew: Rs." + amount);
            transactionHistory.add("Withdrew: Rs." + amount);
        }
    }

    // Method to deposit cash
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: Rs." + amount);
        transactionHistory.add("Deposited: Rs." + amount);
    }

    // Method to change the PIN
    public void changePin(String newPin) {
        this.pin = newPin;
        System.out.println("PIN changed successfully.");
    }

    // Method to display transaction history
    public void viewTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    // Method to validate PIN
    public boolean validatePin(String inputPin) {
        return pin.equals(inputPin);
    }
}

public class ATMApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM(1000.0, "1234"); // Initial balance of Rs.1000 and PIN "1234"
        boolean sessionActive = true;

        System.out.println("Welcome to the ATM!");

        while (sessionActive) {
            System.out.println("\nPlease enter your PIN:");
            String inputPin = scanner.nextLine();

            if (atm.validatePin(inputPin)) {
                while (true) {
                    System.out.println("\nSelect an option:");
                    System.out.println("1. Check Balance");
                    System.out.println("2. Withdraw Cash");
                    System.out.println("3. Deposit Cash");
                    System.out.println("4. Change PIN");
                    System.out.println("5. View Transaction History");
                    System.out.println("6. Exit");

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (choice) {
                        case 1:
                            atm.checkBalance();
                            break;
                        case 2:
                            System.out.println("Enter amount to withdraw:");
                            double withdrawAmount = scanner.nextDouble();
                            atm.withdraw(withdrawAmount);
                            break;
                        case 3:
                            System.out.println("Enter amount to deposit:");
                            double depositAmount = scanner.nextDouble();
                            atm.deposit(depositAmount);
                            break;
                        case 4:
                            System.out.println("Enter new PIN:");
                            String newPin = scanner.nextLine();
                            atm.changePin(newPin);
                            break;
                        case 5:
                            atm.viewTransactionHistory();
                            break;
                        case 6:
                            sessionActive = false;
                            System.out.println("Thank you for using the ATM!");
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }

                    if (!sessionActive) break;
                }
            } else {
                System.out.println("Incorrect PIN. Please try again.");
            }
        }
        
        scanner.close();
    }
}
