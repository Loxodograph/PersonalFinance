import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FilterFrame {
    public static Font textFieldFont = new Font("Arial", Font.PLAIN, 10);
    public MainScreen mainScreen;

    public FilterFrame(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
    }

    public void createFrame() {
        EventQueue.invokeLater(() -> {
            ArrayList<JPanel> panelArrayList = mainScreen.createNewFrame();
            JPanel mainPanel = panelArrayList.get(0);
            JPanel inputPanel = panelArrayList.get(1);
            JPanel buttonPanel = panelArrayList.get(2);

            mainScreen.additionalFrame.setTitle("Filter by Category");

            JLabel categoryLabel = new JLabel("Category: ");
            JTextField categoryTextArea = new JTextField(10);
            categoryTextArea.setPreferredSize(mainScreen.maximumTextSize);
            categoryTextArea.setMaximumSize(mainScreen.maximumTextSize);
            categoryTextArea.setFont(textFieldFont);

            JButton submit = new JButton("Submit");
            JButton cancel = new JButton("Cancel");

            submit.setMaximumSize(mainScreen.maximumButtonSize);
            cancel.setMaximumSize(mainScreen.maximumButtonSize);

            submit.setMargin(mainScreen.insets);
            cancel.setMargin(mainScreen.insets);
            submit.addActionListener(_ -> {
                String category = categoryTextArea.getText();
                ArrayList<Expense> filteredList = new ArrayList<>();
                for (Expense expense : ExpenseRepository.dataList) {
                    if (expense.getCategory().equals(category)) {
                        filteredList.add(expense);

                    }
                }
                mainScreen.filteredList = filteredList;
                mainScreen.centerPanel.removeAll();


                mainScreen.drawCenterPanel(filteredList);
                mainScreen.UI.jframe.revalidate();
                mainScreen.UI.jframe.repaint();

                mainScreen.additionalFrame.dispose();
            });
            cancel.addActionListener(_ -> mainScreen.additionalFrame.dispose());

            buttonPanel.add(submit);
            buttonPanel.add(cancel);


            inputPanel.add(categoryLabel);
            inputPanel.add(categoryTextArea);

            mainPanel.add(inputPanel);
            mainPanel.add(buttonPanel);

            mainScreen.additionalFrame.getContentPane().add(BorderLayout.CENTER, mainPanel);
            mainScreen.additionalFrame.setSize(400, 100);
            mainScreen.additionalFrame.setLocationByPlatform(true);
            mainScreen.additionalFrame.setVisible(true);
            mainScreen.additionalFrame.setResizable(false);
        });

    }
}