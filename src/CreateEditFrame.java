import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


public class CreateEditFrame {
    public JTextField categoryTextArea;
    public JTextField amountTextLabel;
    public JTextField noteTextLabel;
    public MainScreen mainScreen;
    public JComboBox<String> monthComboBox;
    public JComboBox<String> categoryComboBox;
    public final UserInterface UI;
    public Font textFieldFont = new Font("Arial", Font.PLAIN, 10);
    public String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    public String[] categories = {"Food", "Gym", "Clothes", "Utilities", "Internet", "Telephone", "Rent", "Transportation", "Entertainment", "Other"};


    public CreateEditFrame(UserInterface UI, MainScreen mainScreen) {
        this.UI = UI;
        this.mainScreen = mainScreen;
    }

    public void createEditFrame() {
        EditButtonSubmit editButtonSubmit = new EditButtonSubmit(this, UI, mainScreen);

        EventQueue.invokeLater(() -> {
            int rowIndex = mainScreen.table.getSelectedRow();
            ArrayList<JPanel> panelArrayList = UI.mainScreen.createNewFrame();

            JPanel mainPanel = panelArrayList.get(0);
            JPanel inputPanel = panelArrayList.get(1);
            JPanel buttonPanel = panelArrayList.get(2);
            UI.mainScreen.additionalFrame.setLocationRelativeTo(null);
            UI.mainScreen.additionalFrame.setTitle("Edit Expense");
            Arrays.sort(categories);
            JLabel categoryLabel = new JLabel("Category: ");
            categoryComboBox = new JComboBox<>(categories);
            categoryComboBox.setPreferredSize(UI.mainScreen.maximumTextSize);
            categoryComboBox.setMaximumSize(UI.mainScreen.maximumTextSize);
            categoryComboBox.setFont(textFieldFont);

            JLabel amountLabel = new JLabel("Amount: ");
            amountTextLabel = new JTextField(10);
            if (!mainScreen.filteredList.isEmpty()) {
                amountTextLabel.setText(String.valueOf(mainScreen.filteredList.get(rowIndex).getAmount()));
            } else {
                amountTextLabel.setText(String.valueOf(ExpenseRepository.dataList.get(rowIndex).getAmount()));

            }
            amountTextLabel.setPreferredSize(UI.mainScreen.maximumTextSize);
            amountTextLabel.setMaximumSize(UI.mainScreen.maximumTextSize);
            amountTextLabel.setFont(textFieldFont);

            JLabel monthLabel = new JLabel("Month: ");
            monthComboBox = new JComboBox<>(months);
            monthComboBox.setPreferredSize(UI.mainScreen.maximumTextSize);
            monthComboBox.setMaximumSize(UI.mainScreen.maximumTextSize);
            monthComboBox.setFont(textFieldFont);

            JLabel noteLabel = new JLabel("Note: ");
            noteTextLabel = new JTextField(10);
            if (!mainScreen.filteredList.isEmpty()) {
                noteTextLabel.setText(mainScreen.filteredList.get(rowIndex).getNote());
            } else {
                noteTextLabel.setText(ExpenseRepository.dataList.get(rowIndex).getNote());

            }

            noteTextLabel.setPreferredSize(UI.mainScreen.maximumTextSize);
            noteTextLabel.setMaximumSize(UI.mainScreen.maximumTextSize);
            noteTextLabel.setFont(textFieldFont);

            JButton submit = new JButton("Submit");
            JButton cancel = new JButton("Cancel");

            submit.setMaximumSize(UI.mainScreen.maximumButtonSize);
            cancel.setMaximumSize(UI.mainScreen.maximumButtonSize);

            submit.setMargin(UI.mainScreen.insets);
            cancel.setMargin(UI.mainScreen.insets);
            submit.addActionListener(editButtonSubmit);
            cancel.addActionListener(_ -> mainScreen.additionalFrame.dispose());

            NewExpenseFrame.DrawNewFrame(mainPanel, inputPanel, buttonPanel, categoryLabel, amountLabel, monthLabel, noteLabel, submit, cancel, categoryComboBox, amountTextLabel, monthComboBox, noteTextLabel, UI);
        });
    }
}
