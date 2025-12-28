import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FilterDateFrame {

    public static Font textFieldFont = new Font("Arial", Font.PLAIN, 10);
    public MainScreen mainScreen;
    public String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    public JComboBox<String> monthComboBox;


    public FilterDateFrame(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
    }

    public void createFrame() {
        EventQueue.invokeLater(() -> {
            ArrayList<JPanel> panelArrayList = mainScreen.createNewFrame();

            JPanel mainPanel = panelArrayList.get(0);
            JPanel inputPanel = panelArrayList.get(1);
            JPanel buttonPanel = panelArrayList.get(2);

            mainScreen.additionalFrame.setTitle("Filter by Date");


            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.setOpaque(true);

            inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));

            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

            JLabel monthLabel = new JLabel("Month: ");
            monthComboBox = new JComboBox<>(months);
            monthComboBox.setPreferredSize(mainScreen.maximumTextSize);
            monthComboBox.setMaximumSize(mainScreen.maximumTextSize);
            monthComboBox.setFont(textFieldFont);

            JButton submit = new JButton("Submit");
            JButton cancel = new JButton("Cancel");

            submit.setMaximumSize(mainScreen.maximumButtonSize);
            cancel.setMaximumSize(mainScreen.maximumButtonSize);

            submit.setMargin(mainScreen.insets);
            cancel.setMargin(mainScreen.insets);
            submit.addActionListener(e -> {
                String month = String.valueOf(monthComboBox.getSelectedItem());
                ArrayList<Expense> filteredList = new ArrayList<>();
                for (Expense expense : ExpenseRepository.dataList) {
                    if (expense.getMonth().equals(month)) {
                        filteredList.add(expense);
                    }
                }
                mainScreen.centerPanel.removeAll();
                mainScreen.filteredList = filteredList;

                mainScreen.drawMainCenterPanel(filteredList);
                mainScreen.UI.jframe.revalidate();
                mainScreen.UI.jframe.repaint();

                mainScreen.additionalFrame.dispose();
            });
            cancel.addActionListener(e -> mainScreen.additionalFrame.dispose());

            buttonPanel.add(submit);
            buttonPanel.add(cancel);


            inputPanel.add(monthLabel);
            inputPanel.add(monthComboBox);

            mainPanel.add(inputPanel);
            mainPanel.add(buttonPanel);

            mainScreen.additionalFrame.getContentPane().add(BorderLayout.CENTER, mainPanel);
            mainScreen.additionalFrame.setSize(400, 100);
            mainScreen.additionalFrame.setLocationByPlatform(true);
            mainScreen.additionalFrame.setVisible(true);
            mainScreen.additionalFrame.setResizable(false);
            mainScreen.additionalFrame.setLocationRelativeTo(null);
        });

    }
}
