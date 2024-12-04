package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import interface_adapter.recommend_recipes.RecipesController;
import interface_adapter.recommend_recipes.RecipesViewModel;

/**
 * The view for generating recipes in the application.
 * This class manages the
 *
 */
public class RecipeView extends JPanel implements ActionListener, ItemListener {
    private static final int TITLE_FONT_SIZE = 18;
    private static final int FIELD_WIDTH = 200;
    private static final int FIELD_HEIGHT = 30;
    private static final int BORDER_THICKNESS = 3;
    private static final int FONT_SIZE = 12;
    private static final Color BORDER_COLOR = Color.RED;
    private static final Color TEXT_COLOR = Color.RED;
    private static final String WARNING_MESSAGE = "WARNING NO HOLIDAY TODAY";

    private RecipesController recipesController;
    private final RecipesViewModel recipesViewModel;
    private final JButton returnButton;
    private final JComboBox<String> generationType;
    private final JCheckBox userInfo;
    private final JButton generateButton;
    private final JTextField customQueryField;
    private final JTextField noHolidayError;

    /**
     * Constructs a new RecipeView with the specified view model.
     *
     * @param recipesViewModel the view model for the recipes screen
     */
    public RecipeView(RecipesViewModel recipesViewModel) {
        this.recipesViewModel = recipesViewModel;

        final JLabel titleLabel = new JLabel("Generate Recipes", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, TITLE_FONT_SIZE));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(titleLabel);

        returnButton = new JButton("Return");
        final String[] recipeTypes = {"Ingredients", "Season", "Holiday", "Custom"};
        generationType = new JComboBox<>(recipeTypes);

        userInfo = new JCheckBox("User Info");
        generateButton = new JButton("Generate Recipes");

        customQueryField = new JTextField();
        customQueryField.setMaximumSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
        customQueryField.setAlignmentX(Component.CENTER_ALIGNMENT);
        customQueryField.setVisible(false);
        customQueryField.setBorder(BorderFactory.createTitledBorder("Custom Search Query"));

        noHolidayError = new JTextField();
        noHolidayError.setMaximumSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
        noHolidayError.setAlignmentX(Component.CENTER_ALIGNMENT);
        noHolidayError.setVisible(false);
        noHolidayError.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, BORDER_THICKNESS));
        noHolidayError.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        noHolidayError.setText(WARNING_MESSAGE);
        noHolidayError.setHorizontalAlignment(JTextField.CENTER);
        noHolidayError.setForeground(TEXT_COLOR);
        noHolidayError.setEditable(false);

        final JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);

        generationType.setMaximumSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
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

    /**
     * Sets the controller for the recipes view.
     *
     * @param recipesController the controller for managing the recipes view
     */
    public void setRecipesController(RecipesController recipesController) {
        this.recipesController = recipesController;
    }

    /**
     * React to a button click that results in evt.
     * @param evt the event that triggered this action
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(returnButton)) {
            recipesController.returnTomain();
        }
        else if (evt.getSource().equals(generateButton)) {
            final String selectedType = (String) generationType.getSelectedItem();
            recipesController.goToGenerated(selectedType, userInfo.isSelected(), customQueryField.getText());
        }
    }

    /**
     * React to a change in the selected item.
     * @param evt the event that triggered this action
     */
    @Override
    public void itemStateChanged(ItemEvent evt) {
        if (evt.getSource().equals(generationType) && evt.getStateChange() == ItemEvent.SELECTED) {
            final String selectedType = (String) generationType.getSelectedItem();
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
