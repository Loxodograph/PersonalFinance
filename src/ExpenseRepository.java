import java.util.ArrayList;

public class ExpenseRepository {

    public static ArrayList<Expense> dataList = new ArrayList<>();

    public static Expense createExpense(String category, double amount, String month, String note) {
        return new Expense(category, amount, month, note);
    }

    public static void addExpense(String category, double amount, String month, String note) {
        dataList.add(ExpenseRepository.createExpense(category, amount, month, note));
    }

    public static void editExpense(Expense expense, String category, double amount, String month, String note) {
        expense.setCategory(category);
        expense.setAmount(amount);
        expense.setMonth(month);
        expense.setNote(note);
    }

    public static void removeExpense(Expense expense) {
        dataList.remove(expense);
    }
}

