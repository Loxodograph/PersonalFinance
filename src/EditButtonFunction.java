import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditButtonFunction implements ActionListener {
    public CreateEditFrame createEditFrame;
    public EditButtonFunction(CreateEditFrame createEditFrame) {
        this.createEditFrame = createEditFrame;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        createEditFrame.createEditFrame();

    }
}
