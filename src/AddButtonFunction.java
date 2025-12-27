import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddButtonFunction implements ActionListener {
    public CreateFrame createFrame;
    public AddButtonFunction(CreateFrame createFrame) {
        this.createFrame = createFrame;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        createFrame.createFrame();

    }
}
