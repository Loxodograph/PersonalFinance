import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteExpenseFunction implements ActionListener {
    public final MainScreen mainScreen;

    public DeleteExpenseFunction(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Expense selectedExpense;
        int rowIndex = mainScreen.table.getSelectedRow();
        if (!mainScreen.filteredList.isEmpty()) {
            selectedExpense = mainScreen.filteredList.get(rowIndex);
        } else {
            selectedExpense = ExpenseRepository.dataList.get(rowIndex);
        }
        if (!mainScreen.filteredList.isEmpty()) {
            mainScreen.filteredList.remove(selectedExpense);
            ExpenseRepository.removeExpense(selectedExpense);
        } else {
            ExpenseRepository.removeExpense(selectedExpense);

        }

        mainScreen.centerPanel.removeAll();
        mainScreen.drawCenterPanel(mainScreen.filteredList);

        mainScreen.UI.jframe.revalidate();
        mainScreen.UI.jframe.repaint();
    }
}
