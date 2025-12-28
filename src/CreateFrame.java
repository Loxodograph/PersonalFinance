import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CreateFrame {
    public JTextField categoryTextArea;
    public JTextField amountTextLabel;
    public JTextField noteTextLabel;
    public JComboBox<String> monthComboBox;
    public final UserInterface UI;
    public Font textFieldFont = new Font("Arial", Font.PLAIN, 10);
    public String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    public CreateFrame(UserInterface UI) {
        this.UI = UI;
    }

    public void createFrame() {
        SubmitNewExpenseFunction submitNewExpenseFunction = new SubmitNewExpenseFunction(this, UI);

        EventQueue.invokeLater(() -> {

            ArrayList<JPanel> panelArrayList = UI.mainScreen.createNewFrame();
            JPanel mainPanel = panelArrayList.get(0);
            JPanel inputPanel = panelArrayList.get(1);
            JPanel buttonPanel = panelArrayList.get(2);

            UI.mainScreen.additionalFrame.setTitle("Add New Expense");

            JLabel categoryLabel = new JLabel("Category: ");
            categoryTextArea = new JTextField(10);
            categoryTextArea.setPreferredSize(UI.mainScreen.maximumTextSize);
            categoryTextArea.setMaximumSize(UI.mainScreen.maximumTextSize);
            categoryTextArea.setFont(textFieldFont);

            JLabel amountLabel = new JLabel("Amount: ");
            amountTextLabel = new JTextField(10);
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

            noteTextLabel.setPreferredSize(UI.mainScreen.maximumTextSize);
            noteTextLabel.setMaximumSize(UI.mainScreen.maximumTextSize);

            noteTextLabel.setFont(textFieldFont);

            JButton submit = new JButton("Submit");
            JButton cancel = new JButton("Cancel");

            submit.setMaximumSize(UI.mainScreen.maximumButtonSize);
            cancel.setMaximumSize(UI.mainScreen.maximumButtonSize);

            submit.setMargin(UI.mainScreen.insets);
            cancel.setMargin(UI.mainScreen.insets);
            submit.addActionListener(submitNewExpenseFunction);
            cancel.addActionListener(_ -> UI.mainScreen.additionalFrame.dispose());

            DrawNewFrame(mainPanel, inputPanel, buttonPanel, categoryLabel, amountLabel, monthLabel, noteLabel, submit, cancel, categoryTextArea, amountTextLabel, monthComboBox, noteTextLabel, UI);
        });
    }

    static void DrawNewFrame(JPanel mainPanel, JPanel inputPanel, JPanel buttonPanel, JLabel categoryLabel, JLabel amountLabel, JLabel monthLabel, JLabel noteLabel, JButton submit, JButton cancel, JTextField categoryTextArea, JTextField amountTextLabel, JComboBox<String> monthComboBox, JTextField noteTextLabel, UserInterface ui) {
        buttonPanel.add(submit);
        buttonPanel.add(cancel);


        inputPanel.add(categoryLabel);
        inputPanel.add(categoryTextArea);
        inputPanel.add(Box.createHorizontalStrut(10));
        inputPanel.add(amountLabel);
        inputPanel.add(amountTextLabel);
        inputPanel.add(Box.createHorizontalStrut(10));
        inputPanel.add(monthLabel);
        inputPanel.add(monthComboBox);
        inputPanel.add(Box.createHorizontalStrut(10));
        inputPanel.add(noteLabel);
        inputPanel.add(noteTextLabel);
        inputPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(inputPanel);
        mainPanel.add(buttonPanel);

        ui.mainScreen.additionalFrame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        ui.mainScreen.additionalFrame.setSize(600, 100);
        ui.mainScreen.additionalFrame.setLocationByPlatform(true);
        ui.mainScreen.additionalFrame.setVisible(true);
        ui.mainScreen.additionalFrame.setResizable(false);
    }
}
