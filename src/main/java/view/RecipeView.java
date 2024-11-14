package view;

import interface_adapter.recommend_recipes.RecipesController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecipeView extends JPanel implements ActionListener {
    private RecipesController recipesController;
    private final JButton returnButton = new JButton("Return");

    public RecipeView() {
        JLabel title = new JLabel("Generate Recipes");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel buttons = new JPanel();
        buttons.add(returnButton);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        returnButton.addActionListener(this);
        this.add(title);
        this.add(buttons);
    }

    public void setRecipesController(RecipesController recipesController) {
        this.recipesController = recipesController;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(returnButton)) {
            recipesController.return_to_main();
        }
    }
}