package app;

import javax.swing.*;
import java.awt.*;

public class AppBuilder {
    private final JFrame mainScreen = new JFrame();
    private final CardLayout cardLayout = new CardLayout();

    public AppBuilder() {
        mainScreen.setLayout(cardLayout);
    }

    public JFrame build() {
        JFrame frame = new JFrame("Main Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        return frame;
    }
}
