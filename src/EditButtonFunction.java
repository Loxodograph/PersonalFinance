import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditButtonFunction implements ActionListener {
    public CreateEditFrame createEditFrame;
    public MainScreen mainScreen;
    public EditButtonFunction(CreateEditFrame createEditFrame, MainScreen mainScreen) {
        this.createEditFrame = createEditFrame;
        this.mainScreen = mainScreen;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        createEditFrame.createEditFrame();

    }
}
