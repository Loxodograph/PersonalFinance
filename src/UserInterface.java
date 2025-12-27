import javax.swing.*;
import java.awt.*;

public class UserInterface {
    JFrame jframe = new JFrame("Personal Finance");
    TitleScreen titleScreen;
    MainScreen mainScreen;
    CreateFrame createFrame;
    public Font mainMenuFont = new Font("Droid Sans", Font.PLAIN, 50);
    public Font buttonFont = new Font("Droid Sans", Font.PLAIN, 30);

    public int screenWidth = 900;
    public int screenHeight = 600;

    public UserInterface() {

        titleScreen = new TitleScreen(this);
        mainScreen = new MainScreen(this);
        createFrame = new CreateFrame(this);
    }
    public void drawTitle() {
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(screenWidth, screenHeight);
        jframe.setResizable(false);
        // Call Title Screen
        titleScreen.draw();
        jframe.setVisible(true);
    }

    public void drawMainScreen() {
        clearInterface();
        mainScreen.draw();
    }

    public void clearInterface() {
        jframe.getContentPane().removeAll();
        jframe.getContentPane().revalidate();
        jframe.getContentPane().repaint();

    }

    public static void refreshInterface() {

    }
}
