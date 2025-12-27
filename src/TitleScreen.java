import javax.swing.*;

public class TitleScreen {

    final private UserInterface UI;
    JButton startButton;
    StartButton startFunction;

    public TitleScreen(UserInterface UI) {
        this.UI = UI;
        startFunction = new StartButton(this.UI);
    }

    public void draw() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        mainPanel.setBounds(0, 0, UI.screenWidth, UI.screenHeight);

        //Create Jlabel, set the font and text and position
        JLabel textArea = new JLabel();
        textArea.setFont(UI.mainMenuFont);
        textArea.setText("Personal Finance Tracker");
        textArea.setBounds(0, 0, UI.screenWidth, 60);
        textArea.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(textArea);

        //create Start button, set font text and position

        startButton = new JButton("Start");
        startButton.setFont(UI.buttonFont);
        startButton.setFocusable(false);
        startButton.addActionListener(startFunction);
        startButton.setBounds((UI.screenWidth / 2) - 50, UI.screenHeight / 2, 100, 30);
        startButton.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(startButton);

        UI.jframe.add(mainPanel);
        UI.jframe.revalidate();
        UI.jframe.repaint();
    }
}
