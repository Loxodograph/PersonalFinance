import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditButtonSubmit implements ActionListener {

    private final CreateEditFrame expensePanel;
    private final UserInterface UI;
    private final MainScreen mainScreen;

    public EditButtonSubmit(CreateEditFrame expensePanel, UserInterface UI, MainScreen mainScreen) {
        this.expensePanel = expensePanel;
        this.UI = UI;
        this.mainScreen = mainScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Expense selectedExpense;
        String categoryText = String.valueOf(expensePanel.categoryComboBox.getSelectedItem());
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
            mainScreen.drawMainCenterPanel(mainScreen.filteredList);
        } else {
            mainScreen.drawMainCenterPanel(ExpenseRepository.dataList);
        }

        UI.jframe.revalidate();
        UI.jframe.repaint();


    }
}
