import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateFrame {
    public JTextField categoryTextArea;
    public JTextField amountTextLabel;
    public JTextField noteTextLabel;
    public JComboBox<String> monthComboBox;
    public JFrame frame;
    public final UserInterface UI;
    public Font textFieldFont = new Font("Arial", Font.PLAIN, 10);
    public String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    public CreateFrame(UserInterface UI) {
        this.UI = UI;
    }

    public void createFrame() {
        SubmitNewExpenseFunction submitNewExpenseFunction = new SubmitNewExpenseFunction(this, UI);

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                Dimension maximumSize = new Dimension(100, 30);
                Dimension maximumTextSize = new Dimension(100, 20);
                Insets insets = new Insets(0, 0, 0, 0);

                frame = new JFrame("Test");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                JPanel mainPanel = new JPanel();
                mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
                mainPanel.setOpaque(true);

                JPanel inputPanel = new JPanel();
                inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));

                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));


                JLabel categoryLabel = new JLabel("Category: ");
                categoryTextArea = new JTextField(10);
                categoryTextArea.setPreferredSize(maximumTextSize);
                categoryTextArea.setMaximumSize(maximumTextSize);
                categoryTextArea.setFont(textFieldFont);

                JLabel amountLabel = new JLabel("Amount: ");
                amountTextLabel = new JTextField(10);
                amountTextLabel.setPreferredSize(maximumTextSize);
                amountTextLabel.setMaximumSize(maximumTextSize);
                amountTextLabel.setFont(textFieldFont);

                JLabel monthLabel = new JLabel("Month: ");
                monthComboBox = new JComboBox<>(months);
                monthComboBox.setPreferredSize(maximumTextSize);
                monthComboBox.setMaximumSize(maximumTextSize);
                monthComboBox.setFont(textFieldFont);

                JLabel noteLabel = new JLabel("Note: ");
                noteTextLabel = new JTextField(10);

                noteTextLabel.setPreferredSize(maximumTextSize);
                noteTextLabel.setMaximumSize(maximumTextSize);

                noteTextLabel.setFont(textFieldFont);

                JButton submit = new JButton("Submit");
                JButton cancel = new JButton("Cancel");

                submit.setMaximumSize(maximumSize);
                cancel.setMaximumSize(maximumSize);

                submit.setMargin(insets);
                cancel.setMargin(insets);
                submit.addActionListener(submitNewExpenseFunction);
                cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });

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

                frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
                frame.setSize(600, 100);
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
                frame.setResizable(false);
//                input.requestFocus();
            }
        });
    }
}
