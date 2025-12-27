import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainScreen {

    final private UserInterface UI;
    final private Font buttonFont = new Font("Arial", Font.PLAIN, 10);
    JPanel mainPanel = new JPanel(new BorderLayout());
    JPanel headerPanel = new JPanel();
    JPanel sideBarPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    int buttonWidth = 120;
    int buttonHeight = 30;
    int buttonPadding = 20;
    int buttonStartX = 100;

    public MainScreen(UserInterface UI) {
        this.UI = UI;
    }

    public void draw() {

        headerPanel.setLayout(null);

        centerPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 0, 0, Color.darkGray));

        headerPanel.setOpaque(true);
        sideBarPanel.setOpaque(true);
        centerPanel.setOpaque(true);

        headerPanel.setPreferredSize(new Dimension(UI.screenWidth, 100));
        sideBarPanel.setPreferredSize(new Dimension(100, UI.screenHeight));



        drawHeader();
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(sideBarPanel, BorderLayout.WEST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        UI.jframe.add(mainPanel);
    }

    public void drawHeader() {
        JButton summaryView = new JButton("Summary View");
        JButton monthlyView = new JButton("Monthly View");
        JButton mainView = new JButton("Main View");

        // set button fonts
        mainView.setFont(buttonFont);
        summaryView.setFont(buttonFont);
        monthlyView.setFont(buttonFont);


        //position buttons and add to main panel
        mainView.setBounds(buttonStartX, 50, buttonWidth, buttonHeight);
        headerPanel.add(mainView);

        summaryView.setBounds(buttonStartX + buttonPadding + buttonWidth, 50, buttonWidth, buttonHeight);
        headerPanel.add(summaryView);

        monthlyView.setBounds(buttonStartX + buttonPadding * 2 + buttonWidth * 2, 50, buttonWidth, buttonHeight);
        headerPanel.add(monthlyView);

        try {
            BufferedImage image = ImageIO.read(new File("logo.png"));
            BufferedImage resizedIcon = ImageResizer.resizeImage(image, 100, 100);
            ImageIcon icon = new ImageIcon(resizedIcon);

            JLabel picLabel = new JLabel(icon);
            picLabel.setBounds(10, 10, 100, 100);

            headerPanel.add(picLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawSideBar() {
        JButton filterCategory = new JButton("Filter Category");
        JButton filterDate = new JButton("Filter Date");
        JButton editExpense = new JButton("Edit Expense");
        JButton deleteExpense = new JButton("Delete Expense");

        filterCategory.setFont(buttonFont);
        filterDate.setFont(buttonFont);
        editExpense.setFont(buttonFont);
        deleteExpense.setFont(buttonFont);
    }

}
