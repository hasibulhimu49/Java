package projectbankaccountmanagement;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

// BankAccount class
class BankAccount {
    private String accountHolder;
    private String accountNumber;
    private double balance;
    private ArrayList<String> transactionHistory; // Transaction list

    // Constructor
    public BankAccount(String accountHolder, String accountNumber) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add(new Date() + " - Account created for " + accountHolder);
    }

    // Deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful!");
            transactionHistory.add(new Date() + " - Deposited $" + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    // Withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful!");
            transactionHistory.add(new Date() + " - Withdrew $" + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
    }

    // Show account info
    public void showDetails() {
        System.out.println("\nAccount Details:");
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.printf("Balance: $%.2f\n", balance);
    }

    // Show transaction history
    public void showTransactionHistory() {
        System.out.println("\nTransaction History:");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String t : transactionHistory) {
                System.out.println(t);
            }
        }
    }
}

// Main class
public class ProjectBankAccountManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount account = null;

        while (true) {
            System.out.println("\n===== Bank Account Menu =====");
            System.out.println("1. Create New Account");
            System.out.println("2. View Account Details");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. View Transaction History");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    if (account == null) {
                        System.out.print("Enter account holder name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter account number: ");
                        String accNumber = sc.nextLine();
                        account = new BankAccount(name, accNumber);
                        System.out.println("Account created successfully!");
                    } else {
                        System.out.println("Account already exists!");
                    }
                    break;

                case 2:
                    if (account != null) {
                        account.showDetails();
                    } else {
                        System.out.println("No account found! Please create one first.");
                    }
                    break;

                case 3:
                    if (account != null) {
                        System.out.print("Enter deposit amount: ");
                        double amount = sc.nextDouble();
                        account.deposit(amount);
                    } else {
                        System.out.println("No account found! Please create one first.");
                    }
                    break;

                case 4:
                    if (account != null) {
                        System.out.print("Enter withdrawal amount: ");
                        double amount = sc.nextDouble();
                        account.withdraw(amount);
                    } else {
                        System.out.println("No account found! Please create one first.");
                    }
                    break;

               

                case 5:
                    if (account != null) {
                        account.showTransactionHistory();
                    } else {
                        System.out.println("No account found! Please create one first.");
                    }
                    break;
                    
                 case 6:
                    System.out.println("Exiting... Thank you for using the Bank System!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
package projectbankaccountmanagement;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

// BankAccount class
class BankAccount {
    private String accountHolder;
    private String accountNumber;
    private double balance;
    private ArrayList<String> transactionHistory; // Transaction list

    // Constructor
    public BankAccount(String accountHolder, String accountNumber) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add(new Date() + " - Account created for " + accountHolder);
    }

    // Deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful!");
            transactionHistory.add(new Date() + " - Deposited $" + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    // Withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful!");
            transactionHistory.add(new Date() + " - Withdrew $" + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
    }

    // Show account info
    public void showDetails() {
        System.out.println("\nAccount Details:");
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.printf("Balance: $%.2f\n", balance);
    }

    // Show transaction history
    public void showTransactionHistory() {
        System.out.println("\nTransaction History:");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String t : transactionHistory) {
                System.out.println(t);
            }
        }
    }
}

// Main class
public class ProjectBankAccountManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount account = null;

        while (true) {
            System.out.println("\n===== Bank Account Menu =====");
            System.out.println("1. Create New Account");
            System.out.println("2. View Account Details");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Exit");
            System.out.println("6. View Transaction History");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    if (account == null) {
                        System.out.print("Enter account holder name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter account number: ");
                        String accNumber = sc.nextLine();
                        account = new BankAccount(name, accNumber);
                        System.out.println("Account created successfully!");
                    } else {
                        System.out.println("Account already exists!");
                    }
                    break;

                case 2:
                    if (account != null) {
                        account.showDetails();
                    } else {
                        System.out.println("No account found! Please create one first.");
                    }
                    break;

                case 3:
                    if (account != null) {
                        System.out.print("Enter deposit amount: ");
                        double amount = sc.nextDouble();
                        account.deposit(amount);
                    } else {
                        System.out.println("No account found! Please create one first.");
                    }
                    break;

                case 4:
                    if (account != null) {
                        System.out.print("Enter withdrawal amount: ");
                        double amount = sc.nextDouble();
                        account.withdraw(amount);
                    } else {
                        System.out.println("No account found! Please create one first.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting... Thank you for using the Bank System!");
                    sc.close();
                    return;

                case 6:
                    if (account != null) {
                        account.showTransactionHistory();
                    } else {
                        System.out.println("No account found! Please create one first.");
                    }
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
