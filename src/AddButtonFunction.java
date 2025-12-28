import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddButtonFunction implements ActionListener {
    public NewExpenseFrame newExpenseFrame;
    public MainScreen mainScreen;
    public AddButtonFunction(NewExpenseFrame newExpenseFrame, MainScreen mainScreen) {
        this.newExpenseFrame = newExpenseFrame;
        this.mainScreen = mainScreen;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (mainScreen.state == State.MAIN) {
            newExpenseFrame.createFrame();
        }

    }
}
