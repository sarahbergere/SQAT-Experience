package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class FinancialAssistant {
    private final ArrayList<Expense> expenses = new ArrayList<>();
    private final ArrayList<Income> incomes = new ArrayList<>();
    private final ArrayList<Reminder> reminders = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

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

    public void displayMenu() {
        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Record an expense");
            System.out.println("2. Record an income");
            System.out.println("3. Add a reminder");
            System.out.println("4. View expenses");
            System.out.println("5. View incomes");
            System.out.println("6. View reminders");
            System.out.println("7. Quit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter expense date (YYYY-MM-DD): ");
                    String dateExpense = scanner.next();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter expense amount: ");
                    double expenseAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter expense category: ");
                    String expenseCategory = scanner.nextLine();
                    recordExpense(dateExpense, expenseAmount, expenseCategory);
                }
                case 2 -> {
                    System.out.println("Enter income date (YYYY-MM-DD): ");
                    String dateIncome = scanner.next();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter income amount: ");
                    double incomeAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter income source: ");
                    String incomeSource = scanner.nextLine();
                    recordIncome(dateIncome, incomeAmount, incomeSource);
                }
                case 3 -> {
                    System.out.println("Enter reminder date (YYYY-MM-DD): ");
                    String reminderDate = scanner.next();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter reminder description: ");
                    String reminderDescription = scanner.nextLine();
                    addReminder(reminderDate, reminderDescription);
                }
                case 4 -> {
                    System.out.println("--- Expenses ---");
                    viewExpenses();
                }
                case 5 -> {
                    System.out.println("--- Incomes ---");
                    viewIncomes();
                }
                case 6 -> {
                    System.out.println("--- Reminders ---");
                    viewReminders();
                }
                case 7 -> {
                    System.out.println("Thank you for using the Financial Assistant.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    public static void main(String[] args) {
        FinancialAssistant assistant = new FinancialAssistant();
        assistant.displayMenu();
    }
}