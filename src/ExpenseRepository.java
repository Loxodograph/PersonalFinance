import java.util.ArrayList;

public class ExpenseRepository {

    public static ArrayList<Expense> dataList = new ArrayList<>();

    public static Expense createExpense(String category, double amount, String month, String note) {
        return new Expense(category, amount, month, note);
    }

    public static void addExpense(String category, double amount, String month, String note) {
        dataList.add(ExpenseRepository.createExpense(category, amount, month, note));
    }

    public static void editExpense(int index, String category, double amount, String month, String note) {
        dataList.get(index).setCategory(category);
        dataList.get(index).setAmount(amount);
        dataList.get(index).setMonth(month);
        dataList.get(index).setNote(note);
    }

    public static void removeExpense(int index) {
        dataList.remove(index);
    }
}

