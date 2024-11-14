package view;
import interface_adapter.ViewModel;
import interface_adapter.season.SeasonController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeasonView extends JPanel implements ActionListener {
    private SeasonController seasonController;
    public void setSeasonController(SeasonController seasonController) {
        this.seasonController = seasonController;
    }


    private final JButton return_button = new JButton("Return");
    public SeasonView() {

        JLabel title = new JLabel("Season");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel buttons = new JPanel();
        buttons.add(return_button);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        return_button.addActionListener(this);
        this.add(title);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(return_button)) {
            seasonController.return_to_main();
        }
    }

}
