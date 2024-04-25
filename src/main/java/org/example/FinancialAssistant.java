package org.example;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FinancialAssistant {
    private double balance = 0;
    private ArrayList<Expense> expenses = new ArrayList<>();
    private ArrayList<Income> incomes = new ArrayList<>();
    private ArrayList<Reminder> reminders = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private static final String DATE_REGEX = "\\d{4}-\\d{2}-\\d{2}";

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(int n){
        this.balance = n;
    }

    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    public ArrayList<Income> getIncomes() {
        return incomes;
    }

    public ArrayList<Reminder> getReminders() {
        return reminders;
    }

    public boolean isValidDate(String date) {
        Pattern pattern = Pattern.compile(DATE_REGEX);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

    public void recordExpense(String date, double amount, String category) {
        expenses.add(new Expense(date, amount, category));
    }

    public void recordIncome(String date, double amount, String source) {
        incomes.add(new Income(date, amount, source));
    }

    public void addReminder(String date, String description) {
        reminders.add(new Reminder(date, description));
    }

    public void viewExpenses() {
        for (Expense expense : expenses) {
            System.out.println("Date: "+ expense.date + ", Amount: " + expense.amount + ", Category: " + expense.category);
        }
    }

    public void viewIncomes() {
        for (Income income : incomes) {
            System.out.println("Date: " + income.date + ", Amount: " + income.amount + ", Source: " + income.source);
        }
    }

    public void viewReminders() {
        for (Reminder reminder : reminders) {
            System.out.println("Date: " + reminder.date + ", Description: " + reminder.description);
        }
    }

    private String inputValidDate() {
        while (true) {
            System.out.println("Enter date (YYYY-MM-DD): ");
            String date = scanner.next();
            if (isValidDate(date)) {
                return date;
            } else {
                System.out.println("Invalid date format. Please use YYYY-MM-DD format.");
            }
        }
    }

    public void displayMenu() {
        while (true) {
            System.out.println("Available balance: "+getBalance()+"\nWhat would you like to do?");
            System.out.println("1. Record an expense");
            System.out.println("2. Record an income");
            System.out.println("3. Add a reminder");
            System.out.println("4. View expenses");
            System.out.println("5. View incomes");
            System.out.println("6. View reminders");
            System.out.println("7. Quit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    String dateExpense = inputValidDate();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter expense amount: ");
                    double expenseAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter expense category: ");
                    String expenseCategory = scanner.nextLine();
                    recordExpense(dateExpense, expenseAmount, expenseCategory);
                    break;
                case 2:
                    String dateIncome = inputValidDate();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter income amount: ");
                    double incomeAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter income source: ");
                    String incomeSource = scanner.nextLine();
                    recordIncome(dateIncome,incomeAmount, incomeSource);
                    break;
                case 3:
                    String reminderDate = inputValidDate();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter reminder description: ");
                    String reminderDescription = scanner.nextLine();
                    addReminder(reminderDate, reminderDescription);
                    break;
                case 4:
                    System.out.println("--- Expenses ---");
                    viewExpenses();
                    break;
                case 5:
                    System.out.println("--- Incomes ---");
                    viewIncomes();
                    break;
                case 6:
                    System.out.println("--- Reminders ---");
                    viewReminders();
                    break;
                case 7:
                    System.out.println("Thank you for using the Financial Assistant.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}