public class Account {
    private int accountNumber;
    private String name;
    private String mobile;
    private double balance;

    // Constructor
    public Account(int accountNumber, String name, String mobile, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.mobile = mobile;
        this.balance = balance;
    }

    // Getters
    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public double getBalance() {
        return balance;
    }

    // Deposit
    public void deposit(double amount) {
        balance += amount;
        System.out.println("₹" + amount + " deposited successfully.");
    }

    // Withdraw
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn successfully.");
        } else {
            System.out.println("Insufficient Balance!");
        }
    }

    // Display Details
    public void displayAccount() {
        System.out.println("\n------ Account Details ------");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Customer Name  : " + name);
        System.out.println("Mobile Number  : " + mobile);
        System.out.println("Balance        : ₹" + balance);
    }
}
import java.util.ArrayList;

public class Bank {

    private ArrayList<Account> accounts = new ArrayList<>();

    // Create Account
    public void createAccount(int accNo, String name, String mobile, double balance) {
        Account account = new Account(accNo, name, mobile, balance);
        accounts.add(account);
        System.out.println("Account Created Successfully.");
    }

    // Search Account
    public Account searchAccount(int accNo) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accNo) {
                return acc;
            }
        }
        return null;
    }

    // Deposit
    public void deposit(int accNo, double amount) {
        Account acc = searchAccount(accNo);

        if (acc != null) {
            acc.deposit(amount);
        } else {
            System.out.println("Account Not Found!");
        }
    }

    // Withdraw
    public void withdraw(int accNo, double amount) {
        Account acc = searchAccount(accNo);

        if (acc != null) {
            acc.withdraw(amount);
        } else {
            System.out.println("Account Not Found!");
        }
    }

    // Balance Enquiry
    public void checkBalance(int accNo) {
        Account acc = searchAccount(accNo);

        if (acc != null) {
            System.out.println("Available Balance : ₹" + acc.getBalance());
        } else {
            System.out.println("Account Not Found!");
        }
    }

    // Display Account Details
    public void displayAccount(int accNo) {
        Account acc = searchAccount(accNo);

        if (acc != null) {
            acc.displayAccount();
        } else {
            System.out.println("Account Not Found!");
        }
    }

    // Transfer Money
    public void transferMoney(int fromAcc, int toAcc, double amount) {

        Account sender = searchAccount(fromAcc);
        Account receiver = searchAccount(toAcc);

        if (sender == null || receiver == null) {
            System.out.println("Invalid Account Number!");
            return;
        }

        if (sender.getBalance() >= amount) {
            sender.withdraw(amount);
            receiver.deposit(amount);
            System.out.println("Money Transferred Successfully.");
        } else {
            System.out.println("Insufficient Balance!");
        }
    }

    // Display All Accounts
    public void displayAllAccounts() {

        if (accounts.isEmpty()) {
            System.out.println("No Accounts Available.");
            return;
        }

        for (Account acc : accounts) {
            acc.displayAccount();
            System.out.println("----------------------------");
        }
    }
}
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();

        while (true) {

            System.out.println("\n========== BANK MANAGEMENT SYSTEM ==========");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Balance Enquiry");
            System.out.println("5. Transfer Money");
            System.out.println("6. Display Account Details");
            System.out.println("7. Display All Accounts");
            System.out.println("8. Exit");
            System.out.print("Enter Your Choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Account Number: ");
                    int accNo = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Customer Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Mobile Number: ");
                    String mobile = sc.nextLine();

                    System.out.print("Enter Initial Deposit: ");
                    double balance = sc.nextDouble();

                    bank.createAccount(accNo, name, mobile, balance);
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    accNo = sc.nextInt();

                    System.out.print("Enter Deposit Amount: ");
                    double deposit = sc.nextDouble();

                    bank.deposit(accNo, deposit);
                    break;

                case 3:
                    System.out.print("Enter Account Number: ");
                    accNo = sc.nextInt();

                    System.out.print("Enter Withdraw Amount: ");
                    double withdraw = sc.nextDouble();

                    bank.withdraw(accNo, withdraw);
                    break;

                case 4:
                    System.out.print("Enter Account Number: ");
                    accNo = sc.nextInt();

                    bank.checkBalance(accNo);
                    break;

                case 5:
                    System.out.print("Enter Sender Account Number: ");
                    int fromAcc = sc.nextInt();

                    System.out.print("Enter Receiver Account Number: ");
                    int toAcc = sc.nextInt();

                    System.out.print("Enter Amount: ");
                    double amount = sc.nextDouble();

                    bank.transferMoney(fromAcc, toAcc, amount);
                    break;

                case 6:
                    System.out.print("Enter Account Number: ");
                    accNo = sc.nextInt();

                    bank.displayAccount(accNo);
                    break;

                case 7:
                    bank.displayAllAccounts();
                    break;

                case 8:
                    System.out.println("Thank you for using Banking Management System.");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}