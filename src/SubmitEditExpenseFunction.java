import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.util.List;

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
        String categoryText = expensePanel.categoryTextArea.getText();
        double amount = Double.parseDouble(expensePanel.amountTextLabel.getText());
        String monthText = String.valueOf(expensePanel.monthComboBox.getSelectedItem());
        String noteText = expensePanel.noteTextLabel.getText();

        int rowIndex = mainScreen.table.getSelectedRow();

        ExpenseRepository.editExpense(rowIndex, categoryText, amount, monthText, noteText);

        expensePanel.frame.dispose();
        UI.clearInterface();
        UI.drawMainScreen();
    }
}
