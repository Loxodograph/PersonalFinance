import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MainScreen {

    final private UserInterface UI;
    final private Font buttonFont = new Font("Arial", Font.PLAIN, 10);
    public JTable table;
    public Object[][] dataObject;
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
        //Refresh Page
        headerPanel.removeAll();
        sideBarPanel.removeAll();
        centerPanel.removeAll();

        headerPanel.setLayout(null);

        //Create Border for Center panel
        centerPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 0, 0, Color.darkGray));
        //Set all to opaque
        headerPanel.setOpaque(true);
        sideBarPanel.setOpaque(true);
        centerPanel.setOpaque(true);

        //Set preferred size for borders
        headerPanel.setPreferredSize(new Dimension(UI.screenWidth, 100));
        sideBarPanel.setPreferredSize(new Dimension(100, UI.screenHeight));
        // Add borders to correct location
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(sideBarPanel, BorderLayout.WEST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        //draw borders
        drawHeader();
        drawSideBar();
        drawCenterPanel();

        //add mainPanel to frame
        UI.jframe.add(mainPanel);
        UI.jframe.revalidate();
        UI.jframe.repaint();
    }

    public void drawCenterPanel() {
        //Centerpanel layout
        centerPanel.setLayout(new BorderLayout());

        //set up column names of table
        String[] columnNames = {"Category", "Amount", "Month", "Note"};

        //initialize dataobject, length of which is size of our expense list
        dataObject = new Object[ExpenseRepository.dataList.size()][4];
        //iterate through expense list
        //add expense information to dataObject
        for (int i = 0; i < ExpenseRepository.dataList.size(); i++) {
            for (int j = 0; j < 4; j++) {
                Object[] object = ExpenseRepository.dataList.get(i).toObject();
                dataObject[i][j] = object[j];

            }
        }

        //Display dataObject in a table
        table = new JTable(dataObject, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        //add to centerPanel
        centerPanel.add(scrollPane, BorderLayout.CENTER);
    }

    public void drawHeader() {
        //initialize buttons
        JButton summaryView = new JButton("Summary View");
        JButton monthlyView = new JButton("Monthly View");
        JButton mainView = new JButton("Main View");

        summaryView.setRolloverEnabled(false);
        monthlyView.setRolloverEnabled(false);
        mainView.setRolloverEnabled(false);

        summaryView.setContentAreaFilled(true);
        monthlyView.setContentAreaFilled(true);
        mainView.setContentAreaFilled(true);

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
        //Load label
        try {
            BufferedImage image = ImageIO.read(getClass().getResource("/logo.png"));
            BufferedImage resizedIcon = ImageResizer.resizeImage(image, 100, 100);
            ImageIcon icon = new ImageIcon(resizedIcon);

            JLabel picLabel = new JLabel(icon);
            picLabel.setBounds(0, 10, 100, 100);

            headerPanel.add(picLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawSideBar() {
        BoxLayout boxLayout = new BoxLayout(sideBarPanel, BoxLayout.Y_AXIS);
        sideBarPanel.setLayout(boxLayout);

        //initialize buttons
        JButton filterCategory = new JButton("Filter Category");
        JButton filterDate = new JButton("Filter Date");
        JButton addExpense = new JButton("Add Expense");
        JButton editExpense = new JButton("Edit Expense");
        JButton deleteExpense = new JButton("Delete Expense");

        filterCategory.setRolloverEnabled(false);
        filterDate.setRolloverEnabled(false);
        addExpense.setRolloverEnabled(false);
        editExpense.setRolloverEnabled(false);
        deleteExpense.setRolloverEnabled(false);

        //add action listeners
        addExpense.addActionListener(new AddButtonFunction(new CreateFrame(UI)));
        editExpense.addActionListener(new EditButtonFunction(new CreateEditFrame(UI, this)));
        deleteExpense.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ExpenseRepository.removeExpense(table.getSelectedRow());
                UI.clearInterface();
                UI.drawMainScreen();
            }
        });

        //Define Dimensions
        Insets insets = new Insets(0, 0, 0, 0);
        Dimension maxSize = new Dimension(120, 30);

        //Adjust button appearance
        filterCategory.setMaximumSize(maxSize);
        filterDate.setMaximumSize(maxSize);
        addExpense.setMaximumSize(maxSize);
        editExpense.setMaximumSize(maxSize);
        deleteExpense.setMaximumSize(maxSize);

        filterCategory.setAlignmentX(Component.CENTER_ALIGNMENT);
        filterDate.setAlignmentX(Component.CENTER_ALIGNMENT);
        addExpense.setAlignmentX(Component.CENTER_ALIGNMENT);
        editExpense.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteExpense.setAlignmentX(Component.CENTER_ALIGNMENT);

        filterCategory.setFont(buttonFont);
        filterDate.setFont(buttonFont);
        addExpense.setFont(buttonFont);
        editExpense.setFont(buttonFont);
        deleteExpense.setFont(buttonFont);


        filterCategory.setMargin(insets);
        filterDate.setMargin(insets);
        addExpense.setMargin(insets);
        editExpense.setMargin(insets);
        deleteExpense.setMargin(insets);

        //Layout sidePanel buttons

        sideBarPanel.add(Box.createVerticalStrut(20));
        sideBarPanel.add(filterCategory);
        sideBarPanel.add(Box.createVerticalStrut(10));

        sideBarPanel.add(filterDate);
        sideBarPanel.add(Box.createVerticalStrut(10));

        sideBarPanel.add(addExpense);
        sideBarPanel.add(Box.createVerticalStrut(10));

        sideBarPanel.add(editExpense);
        sideBarPanel.add(Box.createVerticalStrut(10));

        sideBarPanel.add(deleteExpense);
        sideBarPanel.add(Box.createVerticalStrut(10));

    }

}
