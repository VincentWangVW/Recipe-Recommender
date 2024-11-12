package view;
import interface_adapter.ViewModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IngredientsView extends JPanel implements ActionListener {

    private final ViewModel viewModel;
    private final JButton return_button = new JButton("Return");
    public IngredientsView(ViewModel viewModel) {
        this.viewModel = viewModel;
        JLabel title = new JLabel("Ingredients");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel buttons = new JPanel();
        buttons.add(return_button);

        return_button.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);

    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(return_button)) {
            viewModel.setState(ViewModel.viewState.MAIN_SCREEN);
        }
    }

}