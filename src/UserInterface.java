import javax.swing.*;
import java.awt.*;

public class UserInterface {
    JFrame jframe = new JFrame("Personal Finance");
    Font mainMenuFont = new Font("Arial Black", Font.PLAIN, 50);
    Font buttonFont = new Font("Arial", Font.PLAIN, 30);
    JButton startButton;
    int screenWidth = 900;
    int screenHeight = 600;
    public void draw() {
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(screenWidth, screenHeight);
        // Call Main Menu
        mainMenu();
        jframe.setVisible(true);
    }

    public void mainMenu() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;

        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.weighty = -1.0;

        //Create Jlabel, set the font and text
        JLabel textArea = new JLabel();
        textArea.setFont(mainMenuFont);
        textArea.setText("Personal Finance Tracker");
        mainPanel.add(textArea, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weighty = 0;

        startButton = new JButton("Start");
        startButton.setFont(buttonFont);
        startButton.setFocusable(false);
        mainPanel.add(startButton, gbc);

        jframe.add(mainPanel);
    }
}
