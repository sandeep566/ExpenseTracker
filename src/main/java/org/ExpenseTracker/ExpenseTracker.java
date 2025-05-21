package org.ExpenseTracker;

import org.ExpenseTracker.Pojo.Transaction;
import org.ExpenseTracker.Service.TransactionManager;

import java.time.LocalDate;
import java.util.Scanner;

public class ExpenseTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TransactionManager manager = new TransactionManager();

        while (true) {
            System.out.println("\n=== Expense Tracker Menu ===");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Monthly Summary");
            System.out.println("4. Load Transactions from File");
            System.out.println("5. Save Transactions to File");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addTransaction(scanner, manager, Transaction.Type.INCOME);
                case 2 -> addTransaction(scanner, manager, Transaction.Type.EXPENSE);
                case 3 -> {
                    System.out.print("Enter year (e.g., 2025): ");
                    int year = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter month (1-12): ");
                    int month = Integer.parseInt(scanner.nextLine());
                    manager.printMonthlySummary(year, month);
                }
                case 4 -> {
                    System.out.print("Enter filename to load: ");
                    String filename = scanner.nextLine();
                    manager.loadFromFile(filename);
                }
                case 5 -> {
                    System.out.print("Enter filename to save: ");
                    String filename = scanner.nextLine();
                    manager.saveToFile(filename);
                }
                case 6 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    private static void addTransaction(Scanner scanner, TransactionManager manager, Transaction.Type type) {
        System.out.print("Enter date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        String category;
        if (type == Transaction.Type.INCOME) {
            System.out.print("Enter income category (e.g., Salary, Business): ");
        } else {
            System.out.print("Enter expense category (e.g., Food, Rent, Travel): ");
        }
        category = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        Transaction transaction = new Transaction(type, date, category, amount);
        manager.addTransaction(transaction);
        System.out.println("Transaction added.");
    }
}

