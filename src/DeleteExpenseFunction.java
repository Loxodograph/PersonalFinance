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

        ExpenseRepository.removeExpense(selectedExpense);

        mainScreen.centerPanel.removeAll();
        if (!mainScreen.filteredList.isEmpty()) {
            mainScreen.drawCenterPanel(mainScreen.filteredList);
        } else {
            mainScreen.drawCenterPanel(ExpenseRepository.dataList);
        }

        mainScreen.UI.jframe.revalidate();
        mainScreen.UI.jframe.repaint();
    }
}
