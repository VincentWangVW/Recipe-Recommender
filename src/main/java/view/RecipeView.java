package view;

import interface_adapter.recommend_recipes.RecipesController;
import interface_adapter.recommend_recipes.RecipesViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecipeView extends JPanel implements ActionListener {
    private RecipesController recipesController;
    private final RecipesViewModel recipesViewModel;
    private final JButton returnButton;
    private final JCheckBox ingredients;
    private final JCheckBox userInfo;
    private final JCheckBox season;
    private final JCheckBox holiday;
    private final JCheckBox drinkItem;
    private final JButton generateButton;

    public RecipeView(RecipesViewModel recipesViewModel) {
        this.recipesViewModel = recipesViewModel;

        JLabel titleLabel = new JLabel("Generate Recipes", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(titleLabel);

        returnButton = new JButton("Return");
        ingredients = new JCheckBox("Ingredients");
        userInfo = new JCheckBox("User Info");
        season = new JCheckBox("Season");
        holiday = new JCheckBox("Holiday");
        drinkItem = new JCheckBox("Drink Item");
        generateButton = new JButton("Generate Recipes");

        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
        ingredients.setAlignmentX(Component.CENTER_ALIGNMENT);
        userInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        season.setAlignmentX(Component.CENTER_ALIGNMENT);
        holiday.setAlignmentX(Component.CENTER_ALIGNMENT);
        drinkItem.setAlignmentX(Component.CENTER_ALIGNMENT);
        returnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        generateButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttons.add(ingredients);
        buttons.add(userInfo);
        buttons.add(season);
        buttons.add(holiday);
        buttons.add(drinkItem);
        buttons.add(generateButton);
        buttons.add(returnButton);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        returnButton.addActionListener(this);
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
        else if (evt.getSource().equals(generateButton)) {
            recipesController.generateRecipes(
                    ingredients.isSelected(),
                    userInfo.isSelected(),
                    season.isSelected(),
                    holiday.isSelected(),
                    drinkItem.isSelected()
            );
        }
    }
}