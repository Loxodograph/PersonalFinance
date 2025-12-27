import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartButton implements ActionListener {

    final private UserInterface UI;
    public StartButton(UserInterface UI) {
        this.UI = UI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UI.drawMainScreen();
    }
}
