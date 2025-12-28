import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubmitNewExpenseFunction implements ActionListener {

    private final CreateFrame expensePanel;
    private final UserInterface UI;

    public SubmitNewExpenseFunction(CreateFrame expensePanel, UserInterface UI) {
        this.expensePanel = expensePanel;
        this.UI = UI;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String categoryText = expensePanel.categoryTextArea.getText();
        double amount = Double.parseDouble(expensePanel.amountTextLabel.getText());
        String monthText = String.valueOf(expensePanel.monthComboBox.getSelectedItem());
        String noteText = expensePanel.noteTextLabel.getText();

        ExpenseRepository.addExpense(categoryText, amount, monthText, noteText);
        UI.mainScreen.additionalFrame.dispose();
        UI.clearInterface();
        UI.drawMainScreen();
    }
}
