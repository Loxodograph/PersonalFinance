import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddButtonFunction implements ActionListener {
    public NewExpenseFrame newExpenseFrame;
    public AddButtonFunction(NewExpenseFrame newExpenseFrame) {
        this.newExpenseFrame = newExpenseFrame;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        newExpenseFrame.createFrame();

    }
}
