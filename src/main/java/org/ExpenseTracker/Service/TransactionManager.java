package org.ExpenseTracker.Service;

import org.ExpenseTracker.Pojo.Transaction;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TransactionManager {
    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\venka\\Downloads\\ExpenseTracker\\src\\main\\java\\org\\ExpenseTracker\\"+filename))) {
            String line;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Transaction.Type type = Transaction.Type.valueOf(parts[0].toUpperCase());
                LocalDate date = LocalDate.parse(parts[1], formatter);
                String category = parts[2];
                double amount = Double.parseDouble(parts[3]);
                transactions.add(new Transaction(type, date, category, amount));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\venka\\Downloads\\ExpenseTracker\\src\\main\\java\\org\\ExpenseTracker\\"+filename))) {
            for (Transaction t : transactions) {
                writer.write(t.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    public void printMonthlySummary(int year, int month) {
        double totalIncome = 0, totalExpense = 0;
        System.out.println("Summary for " + year + "-" + String.format("%02d", month));
        for (Transaction t : transactions) {
            if (t.getDate().getYear() == year && t.getDate().getMonthValue() == month) {
                System.out.printf(" - %s: %s - %.2f\n", t.getType(), t.getCategory(), t.getAmount());
                if (t.getType() == Transaction.Type.INCOME) totalIncome += t.getAmount();
                else totalExpense += t.getAmount();
            }
        }
        System.out.println("Total Income: " + totalIncome);
        System.out.println("Total Expense: " + totalExpense);
        System.out.println("Net Savings: " + (totalIncome - totalExpense));
    }
}
