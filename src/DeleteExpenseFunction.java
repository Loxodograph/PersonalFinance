import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public record DeleteExpenseFunction(MainScreen mainScreen) implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (mainScreen.state == State.MAIN) {
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
            if (!mainScreen.filteredList.isEmpty()) {
                mainScreen.drawMainCenterPanel(mainScreen.filteredList);

            } else {
                mainScreen.drawMainCenterPanel(ExpenseRepository.dataList);
            }

            mainScreen.UI.jframe.revalidate();
            mainScreen.UI.jframe.repaint();
        }

    }
}
