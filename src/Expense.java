public class Expense {

    private String category;
    private double amount;
    private String month;
    private String note;
    public Expense(String category, double amount, String month, String note) {
        this.category = category;
        this.amount = amount;
        this.month = month;
        this.note = note;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public String getMonth() {
        return month;
    }

    public String getNote() {
        return note;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Object[] toObject() {
        return new Object[]{category, String.valueOf(amount), month, note};
    }
}
