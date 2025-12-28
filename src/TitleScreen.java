import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TitleScreen {

    final private UserInterface UI;
    JButton startButton;

    public TitleScreen(UserInterface UI) {
        this.UI = UI;
    }

    public void draw() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        mainPanel.setBounds(0, 0, UI.screenWidth, UI.screenHeight);

        try {
            BufferedImage image = ImageIO.read(getClass().getResource("/logo.png"));
            BufferedImage resizedIcon = ImageResizer.resizeImage(image, 300, 300);
            ImageIcon icon = new ImageIcon(resizedIcon);

            JLabel picLabel = new JLabel(icon);
            picLabel.setBounds(UI.screenWidth/ 3, UI.screenHeight / 4, 300, 300);

            mainPanel.add(picLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        startButton.setBounds((UI.screenWidth / 2) - 50, UI.screenHeight - 150, 100, 30);
        startButton.setHorizontalAlignment(SwingConstants.CENTER);

        startButton.addActionListener(e -> UI.drawMainScreen());
        mainPanel.add(startButton);

        UI.jframe.add(mainPanel);
        UI.jframe.revalidate();
        UI.jframe.repaint();
    }
}
