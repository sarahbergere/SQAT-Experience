package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class FinancialAssistantTest {
    private FinancialAssistant assistant;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        assistant = new FinancialAssistant();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testRecordExpenses() {
        assistant.recordExpense("2024-04-26", 50.0, "Food");
        assertEquals(1, assistant.getExpenses().size());
        Expense expense = assistant.getExpenses().get(0);
        assertEquals(50.0, expense.getAmount(), 0.001);
        assertEquals("Food", expense.getCategory());
        assertEquals("2024-04-26", expense.getDate());

        assistant.recordExpense("2024-04-26", 150.0, "Freelance work");
        assistant.recordExpense("2024-04-27", 300.0, "Investments");

        ArrayList<Expense> expenses = assistant.getExpenses();
        assertEquals(3, expenses.size());
        assertEquals(150.0, expenses.get(1).getAmount(), 0.001);
        assertEquals("Freelance work", expenses.get(1).getCategory());
        assertEquals(300.0, expenses.get(2).getAmount(), 0.001);
        assertEquals("Investments", expenses.get(2).getCategory());
    }


    @Test
    public void testRecordIncomes() {
        assistant.recordIncome("2024-04-25", 200.0, "Part-time job");
        assertEquals(1, assistant.getIncomes().size());
        Income income = assistant.getIncomes().get(0);
        assertEquals(200.0, income.getAmount(), 0.001);
        assertEquals("Part-time job", income.getSource());
        assertEquals("2024-04-25", income.getDate());

        assistant.recordIncome("2024-03-16", 50.0, "Birthday gift");
        assistant.recordIncome("2024-02-17", 15.8, "reimbursement from a friend");
        assistant.recordIncome("2024-02-05", 7.4, "reimbursement from a friend");

        ArrayList<Income> incomes = assistant.getIncomes();
        assertEquals(4, incomes.size());
        assertEquals("2024-03-16", incomes.get(1).getDate());
        assertEquals(50.0, incomes.get(1).getAmount(),0.001);
        assertEquals("2024-02-17", incomes.get(2).getDate());
        assertEquals(15.8, incomes.get(2).getAmount(),0.001);
        assertEquals("2024-02-05", incomes.get(3).getDate());
        assertEquals(7.4, incomes.get(3).getAmount(),0.001);
    }

    @Test
    public void testReminders(){
        assistant.addReminder("2024-05-30", "Pay rent");
        assertEquals(1, assistant.getReminders().size());
        Reminder reminder = assistant.getReminders().get(0);
        assertEquals("2024-05-30", reminder.getDate());
        assertEquals("Pay rent", reminder.getDescription());

        assistant.addReminder("2024-07-30", "Pay rent");
        ArrayList<Reminder> reminders = assistant.getReminders();
        assertEquals(2, reminders.size());
        assertEquals("2024-07-30", reminders.get(1).getDate());
        assertEquals("Pay rent", reminders.get(1).getDescription());
    }

    @Test
    public void testViewExpenses() {
        assistant.recordExpense("2024-04-25", 50.0, "Food");
        assistant.recordExpense("2024-04-26", 30.0, "Transport");
        assistant.viewExpenses();
        assertEquals("Date: 2024-04-25, Amount: 50.0, Category: Food\r\nDate: 2024-04-26, Amount: 30.0, Category: Transport\r\n", outputStreamCaptor.toString());
    }

    @Test
    public void testViewIncomes() {
        assistant.recordIncome("2024-04-25", 200.0, "Part-time job");
        assistant.recordIncome("2024-04-26", 150.0, "Freelance work");
        assistant.viewIncomes();
        assertEquals("Date: 2024-04-25, Amount: 200.0, Source: Part-time job\r\nDate: 2024-04-26, Amount: 150.0, Source: Freelance work\r\n", outputStreamCaptor.toString());
    }

    @Test
    public void testViewReminders() {
        assistant.addReminder("2024-04-25", "Buy groceries");
        assistant.addReminder("2024-04-26", "Pay bills");
        assistant.viewReminders();
        assertEquals("Date: 2024-04-25, Description: Buy groceries\r\nDate: 2024-04-26, Description: Pay bills\r\n", outputStreamCaptor.toString());
    }

    @Test
    public void testIsValidDate_ValidDate() {
        assertTrue(assistant.isValidDate("2024-04-25"));
    }

    @Test
    public void testIsValidDate_InvalidDate() {
        assertFalse(assistant.isValidDate("2024-13-45"));
        assertFalse(assistant.isValidDate("2024/04/25"));
        assertFalse(assistant.isValidDate("abcd-ef-gh"));
    }

    @Test
    public void testGetSetBalance() {
        assistant.setBalance(100);
        assertEquals(100, assistant.getBalance());
    }
}
