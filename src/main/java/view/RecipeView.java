package view;

import interface_adapter.recommend_recipes.RecipesController;
import interface_adapter.recommend_recipes.RecipesViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RecipeView extends JPanel implements ActionListener, ItemListener {
    private RecipesController recipesController;
    private final RecipesViewModel recipesViewModel;
    private final JButton returnButton;
    private final JComboBox<String> generationType;
    private final JCheckBox userInfo;
    private final JButton generateButton;
    private final JTextField customQueryField;
    private final JTextField noHolidayError;

    public RecipeView(RecipesViewModel recipesViewModel) {
        this.recipesViewModel = recipesViewModel;

        JLabel titleLabel = new JLabel("Generate Recipes", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(titleLabel);

        returnButton = new JButton("Return");
        String[] recipeTypes = {"Ingredients", "Season", "Holiday", "Custom"};
        generationType = new JComboBox<>(recipeTypes);

        userInfo = new JCheckBox("User Info");
        generateButton = new JButton("Generate Recipes");

        customQueryField = new JTextField();
        customQueryField.setMaximumSize(new Dimension(200, 30));
        customQueryField.setAlignmentX(Component.CENTER_ALIGNMENT);
        customQueryField.setVisible(false);
        customQueryField.setBorder(BorderFactory.createTitledBorder("Custom Search Query"));

        noHolidayError = new JTextField();
        noHolidayError.setMaximumSize(new Dimension(200, 30));
        noHolidayError.setAlignmentX(Component.CENTER_ALIGNMENT);
        noHolidayError.setVisible(false);
        noHolidayError.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        noHolidayError.setFont(new Font("Arial", Font.BOLD, 12));
        noHolidayError.setText("WARNING NO HOLIDAY TODAY");
        noHolidayError.setHorizontalAlignment(JTextField.CENTER);
        noHolidayError.setForeground(Color.RED);
        noHolidayError.setEditable(false);

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
        buttons.add(customQueryField);
        buttons.add(noHolidayError);
        buttons.add(generateButton);
        buttons.add(returnButton);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        returnButton.addActionListener(this);
        generateButton.addActionListener(this);
        generationType.addItemListener(this);
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
            recipesController.go_to_generated(selectedType, userInfo.isSelected(), customQueryField.getText());
        }
    }

    @Override
    public void itemStateChanged(ItemEvent evt) {
        if (evt.getSource().equals(generationType) && evt.getStateChange() == ItemEvent.SELECTED) {
            String selectedType = (String) generationType.getSelectedItem();
            customQueryField.setVisible("Custom".equals(selectedType));
            if ("Holiday".equals(selectedType)) {
                noHolidayError.setVisible("No Holiday Today!".equals(recipesController.getHoliday()));
            }
            else {
                noHolidayError.setVisible(false);
            }
            customQueryField.getParent().revalidate();
            customQueryField.getParent().repaint();
        }
    }

}