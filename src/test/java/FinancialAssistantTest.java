import org.example.Expense;
import org.example.Income;
import org.example.FinancialAssistant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class FinancialAssistantTest {
    private FinancialAssistant assistant;

    @BeforeEach
    public void setUp() {
        assistant = new FinancialAssistant();
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

        ArrayList<Expense> records = assistant.getExpenses();
        assertEquals(3, records.size());
        assertEquals(150.0, records.get(1).getAmount(), 0.001);
        assertEquals("Freelance work", records.get(1).getCategory());
        assertEquals(300.0, records.get(2).getAmount(), 0.001);
        assertEquals("Investments", records.get(2).getCategory());
    }

    @Test
    public void testRecordIncome() {
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
        assertEquals(3, incomes.size());
        assertEquals("2024-03-16", incomes.get(1).getDate());
        assertEquals(50.0, incomes.get(1).getAmount(),0.001);
        assertEquals("2024-02-17", incomes.get(2).getDate());
        assertEquals(15.8, incomes.get(2).getAmount(),0.001);
        assertEquals("2024-02-05", incomes.get(3).getDate());
        assertEquals(7.4, incomes.get(3).getAmount(),0.001);
    }

    @Test
    public void testReminder(){
    }
}