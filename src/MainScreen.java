import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class MainScreen {

    final public UserInterface UI;
    final private Font buttonFont = new Font("Arial", Font.PLAIN, 10);
    public ArrayList<Expense> filteredList = new ArrayList<>();
    public JTable table;
    public Object[][] dataObject;
    public JFrame additionalFrame;
    public Dimension maximumButtonSize = new Dimension(100, 30);
    public Dimension maximumTextSize = new Dimension(100, 20);
    public Insets insets = new Insets(0, 0, 0, 0);
    final private ActionListener deleteExpenseFunction = new DeleteExpenseFunction(this);
    public State state;
    FilterFrame filterFrame = new FilterFrame(this);
    FilterDateFrame filterDateFrame = new FilterDateFrame(this);
    JPanel mainPanel = new JPanel(new BorderLayout());
    JPanel headerPanel = new JPanel();
    JPanel sideBarPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    int buttonWidth = 120;
    int buttonHeight = 30;
    int buttonPadding = 20;
    int buttonStartX = 100;
    public String[] categories = {"Food", "Gym", "Clothes", "Utilities", "Internet", "Telephone", "Rent", "Transportation", "Entertainment", "Other"};
    public String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

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
        drawMainCenterPanel(ExpenseRepository.dataList);

        //add mainPanel to frame
        UI.jframe.add(mainPanel);
        UI.jframe.revalidate();
        UI.jframe.repaint();
    }

    public JTable drawTable(ArrayList<Expense> dataList, String[] columnNames) {
        //set up column names of table

        //initialize dataobject, length of which is size of our expense list
        dataObject = new Object[dataList.size()][4];
        //iterate through expense list
        //add expense information to dataObject
        for (int i = 0; i < dataList.size(); i++) {
            for (int j = 0; j < 4; j++) {
                Object[] object = dataList.get(i).toObject();
                dataObject[i][j] = object[j];
                System.out.println(dataObject[i][j]);
            }
        }

        return new JTable(dataObject, columnNames);

    }

    public void drawMonthlyCenterPanel(ArrayList<Expense> dataList) {
        state = State.MONTHLY;
        centerPanel.setLayout(new BorderLayout());
        String[] columnNames = {"Month", "Amount"};
        String[][] dataObject = new String[months.length][2];

        for (int i = 0; i < months.length; i++) {
            // for month in month
            double totalSum = 0;
            for (int j = 0; j < dataList.size(); j++) {
                //for expense in datalist
                if (months[i].equals(dataList.get(j).getMonth())) {
                    totalSum += dataList.get(j).getAmount();
                }
            }
            dataObject[i][0] = months[i];
            if (totalSum > 0) {
                dataObject[i][1] = String.valueOf(totalSum);
            } else {
                dataObject[i][1] = "0";
            }
        }
        JTable table = new JTable(dataObject, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        //add to centerPanel
        centerPanel.add(scrollPane, BorderLayout.CENTER);
    }

    public void drawSummaryCenterPanel(ArrayList<Expense> dataList) {
        state = State.SUMMARY;
        centerPanel.setLayout(new BorderLayout());
        String[] columnNames = {"Category", "Amount"};
        String[][] dataObject = new String[categories.length][2];

        for (int i = 0; i < categories.length; i++) {
            // for category in categories
            double totalSum = 0;
            for (int j = 0; j < dataList.size(); j++) {
                //for expense in datalist
                if (categories[i].equals(dataList.get(j).getCategory())) {
                    totalSum += dataList.get(j).getAmount();
                }
            }
            dataObject[i][0] = categories[i];
            if (totalSum > 0) {
                dataObject[i][1] = String.valueOf(totalSum);
            } else {
                dataObject[i][1] = "0";
            }
        }
        JTable table = new JTable(dataObject, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        //add to centerPanel
        centerPanel.add(scrollPane, BorderLayout.CENTER);


    }

    public void drawMainCenterPanel(ArrayList<Expense> dataList) {
        //Centerpanel layout
        state = State.MAIN;
        centerPanel.setLayout(new BorderLayout());
        String[] columnNames = {"Category", "Amount", "Month", "Note"};
        //Display dataObject in a table
        table = drawTable(dataList, columnNames);
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

        //action listeners

        mainView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerPanel.removeAll();
                drawMainCenterPanel(ExpenseRepository.dataList);
                UI.jframe.revalidate();
                UI.jframe.repaint();
            }
        });

        monthlyView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerPanel.removeAll();
                drawMonthlyCenterPanel(ExpenseRepository.dataList);
                UI.jframe.revalidate();
                UI.jframe.repaint();
            }
        });

        summaryView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerPanel.removeAll();
                drawSummaryCenterPanel(ExpenseRepository.dataList);
                UI.jframe.revalidate();
                UI.jframe.repaint();
            }
        });


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
        JButton resetFilter = new JButton("Reset Filter");

        filterCategory.setRolloverEnabled(false);
        filterDate.setRolloverEnabled(false);
        addExpense.setRolloverEnabled(false);
        editExpense.setRolloverEnabled(false);
        deleteExpense.setRolloverEnabled(false);
        resetFilter.setRolloverEnabled(false);

        //add action listeners
        addExpense.addActionListener(new AddButtonFunction(new NewExpenseFrame(UI), this));
        editExpense.addActionListener(new EditButtonFunction(new CreateEditFrame(UI, this), this));

        //Delete Expense Action Listener
        deleteExpense.addActionListener(deleteExpenseFunction);

        //Filter Category Action Listener
        filterCategory.addActionListener(e -> {
            if (state == State.MAIN) {
                filterFrame.createFrame();
            }
        });

        //resetFilter action listener

        resetFilter.addActionListener(_ -> {
            if (state == State.MAIN) {
                centerPanel.removeAll();

                drawMainCenterPanel(ExpenseRepository.dataList);
                UI.jframe.revalidate();
                UI.jframe.repaint();
            }

        });

        //filterDate action listener
        filterDate.addActionListener(e -> {
            if (state == State.MAIN) {
                filterDateFrame.createFrame();
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
        resetFilter.setMaximumSize(maxSize);

        filterCategory.setAlignmentX(Component.CENTER_ALIGNMENT);
        filterDate.setAlignmentX(Component.CENTER_ALIGNMENT);
        addExpense.setAlignmentX(Component.CENTER_ALIGNMENT);
        editExpense.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteExpense.setAlignmentX(Component.CENTER_ALIGNMENT);
        resetFilter.setAlignmentX(Component.CENTER_ALIGNMENT);

        filterCategory.setFont(buttonFont);
        filterDate.setFont(buttonFont);
        addExpense.setFont(buttonFont);
        editExpense.setFont(buttonFont);
        deleteExpense.setFont(buttonFont);
        resetFilter.setFont(buttonFont);


        filterCategory.setMargin(insets);
        filterDate.setMargin(insets);
        addExpense.setMargin(insets);
        editExpense.setMargin(insets);
        deleteExpense.setMargin(insets);
        resetFilter.setMargin(insets);

        //Layout sidePanel buttons

        sideBarPanel.add(Box.createVerticalStrut(20));
        sideBarPanel.add(addExpense);

        sideBarPanel.add(Box.createVerticalStrut(10));
        sideBarPanel.add(editExpense);

        sideBarPanel.add(Box.createVerticalStrut(10));
        sideBarPanel.add(deleteExpense);

        sideBarPanel.add(Box.createVerticalStrut(10));
        sideBarPanel.add(filterCategory);

        sideBarPanel.add(Box.createVerticalStrut(10));
        sideBarPanel.add(filterDate);


        sideBarPanel.add(Box.createVerticalStrut(10));
        sideBarPanel.add(resetFilter);


    }

    public ArrayList<JPanel> createNewFrame() {


        additionalFrame = new JFrame();
        additionalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        ArrayList<JPanel> panelList = new ArrayList<>();
        panelList.add(mainPanel);
        panelList.add(inputPanel);
        panelList.add(buttonPanel);
        return panelList;
    }

}
