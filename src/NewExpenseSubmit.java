import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewExpenseSubmit implements ActionListener {

    private final NewExpenseFrame expensePanel;
    private final UserInterface UI;

    public NewExpenseSubmit(NewExpenseFrame expensePanel, UserInterface UI) {
        this.expensePanel = expensePanel;
        this.UI = UI;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String categoryText = String.valueOf(expensePanel.categoryComboBox.getSelectedItem());
        double amount = Double.parseDouble(expensePanel.amountTextLabel.getText());
        String monthText = String.valueOf(expensePanel.monthComboBox.getSelectedItem());
        String noteText = expensePanel.noteTextLabel.getText();

        ExpenseRepository.addExpense(categoryText, amount, monthText, noteText);
        UI.mainScreen.additionalFrame.dispose();
        UI.clearInterface();
        UI.drawMainScreen();
    }
}
