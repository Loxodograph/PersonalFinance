import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubmitEditExpenseFunction implements ActionListener {

    private final CreateEditFrame expensePanel;
    private final UserInterface UI;
    private final MainScreen mainScreen;

    public SubmitEditExpenseFunction(CreateEditFrame expensePanel, UserInterface UI, MainScreen mainScreen) {
        this.expensePanel = expensePanel;
        this.UI = UI;
        this.mainScreen = mainScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Expense selectedExpense;
        String categoryText = expensePanel.categoryTextArea.getText();
        double amount = Double.parseDouble(expensePanel.amountTextLabel.getText());
        String monthText = String.valueOf(expensePanel.monthComboBox.getSelectedItem());
        String noteText = expensePanel.noteTextLabel.getText();

        int rowIndex = mainScreen.table.getSelectedRow();
        if (!mainScreen.filteredList.isEmpty()) {
            selectedExpense = mainScreen.filteredList.get(rowIndex);
        } else {
            selectedExpense = ExpenseRepository.dataList.get(rowIndex);
        }

        ExpenseRepository.editExpense(selectedExpense, categoryText, amount, monthText, noteText);

        mainScreen.additionalFrame.dispose();
        mainScreen.centerPanel.removeAll();
        if (!mainScreen.filteredList.isEmpty()) {
            mainScreen.drawCenterPanel(mainScreen.filteredList);
        } else {
            mainScreen.drawCenterPanel(ExpenseRepository.dataList);
        }

        UI.jframe.revalidate();
        UI.jframe.repaint();


    }
}
