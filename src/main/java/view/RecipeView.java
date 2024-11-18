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
    private final JComboBox<String> generationType;
    private final JCheckBox userInfo;
    private final JButton generateButton;

    public RecipeView(RecipesViewModel recipesViewModel) {
        this.recipesViewModel = recipesViewModel;

        JLabel titleLabel = new JLabel("Generate Recipes", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(titleLabel);

        returnButton = new JButton("Return");
        String[] recipeTypes = {"Ingredients", "Season", "Holiday", "Drink Item", "Custom"};
        generationType = new JComboBox<>(recipeTypes);

        userInfo = new JCheckBox("User Info");
        generateButton = new JButton("Generate Recipes");

        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);

        generationType.setMaximumSize(new Dimension(200, 30));
        generationType.setAlignmentX(Component.CENTER_ALIGNMENT);
        userInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        returnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        generateButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttons.add(userInfo);
        buttons.add(generationType);
        buttons.add(generateButton);
        buttons.add(returnButton);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        returnButton.addActionListener(this);
        generateButton.addActionListener(this);
        this.add(buttons);
    }

    public void setRecipesController(RecipesController recipesController) {
        this.recipesController = recipesController;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(returnButton)) {
            recipesController.return_to_main();
        } else if (evt.getSource().equals(generateButton)) {
            String selectedType = (String) generationType.getSelectedItem();
            recipesController.go_to_generated(selectedType, userInfo.isSelected());
        }
    }
}