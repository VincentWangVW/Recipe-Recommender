package view;
import interface_adapter.ViewModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JPanel implements ActionListener {

    private final ViewModel viewModel;
    private final JButton ingredients = new JButton("Ingredients");
    private final JButton user_info = new JButton("User Info");
    private final JButton season = new JButton("Season");
    private final JButton generate_recipe = new JButton("Generate Recipe");
    public MainView(ViewModel viewModel) {
        this.viewModel = viewModel;
        JLabel title = new JLabel("Recipe Recommendation");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel buttons = new JPanel();
        buttons.add(ingredients);
        buttons.add(user_info);
        buttons.add(season);
        buttons.add(generate_recipe);

        ingredients.addActionListener(this);
        user_info.addActionListener(this);
        season.addActionListener(this);
        generate_recipe.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);

    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(ingredients)) {
            viewModel.setState(ViewModel.viewState.INGREDIENTS_SCREEN);
        } else if (evt.getSource().equals(user_info)) {
            viewModel.setState(ViewModel.viewState.USER_INFO);
        } else if (evt.getSource().equals(season)) {
            viewModel.setState(ViewModel.viewState.SEASON_SCREEN);
        } else if (evt.getSource().equals(generate_recipe)) {
            viewModel.setState(ViewModel.viewState.GENERATE_RECIPES);
        }
    }

}