package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import interface_adapter.ingredients_manager.IngredientsController;
import interface_adapter.ingredients_manager.IngredientsViewModel;

/**
 * The view for managing ingredients in the application.
 * This class manages the UI components for adding, deleting, and changing the quantity of ingredients.
 * It also displays the list of ingredients currently managed.
 * The user can interact with the view by adding, deleting, and changing the quantity of ingredients.
 * The view communicates with the IngredientsController to handle user actions.
 */
public class IngredientsView extends JPanel implements ActionListener {
    private static final int TITLE_FONT_SIZE = 18;
    private static final int RIGID_AREA_HEIGHT_20 = 20;
    private static final int RIGID_AREA_HEIGHT_10 = 10;
    private static final int SCROLL_PANE_WIDTH = 300;
    private static final int SCROLL_PANE_HEIGHT = 150;
    private static final int INPUT_FIELD_WIDTH = 300;
    private static final int INPUT_FIELD_HEIGHT = 30;
    private static final int DEFAULT_QUANTITY = 1;
    private static final Color HIGHLIGHT_COLOR = new Color(255, 182, 193);
    private static String regex = " - ";
    private IngredientsController ingredientsController;
    private final DefaultListModel<String> ingredientListModel;
    private final JList<String> ingredientList;
    private final JTextField ingredientInputField;
    private final JTextField quantityInputField;
    private final JButton addButton;
    private final JButton deleteButton;
    private final JButton returnButton;
    private final JButton increaseButton;
    private final JButton decreaseButton;

    public IngredientsView(IngredientsViewModel ingredientsViewModel) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Title
        final JLabel titleLabel = new JLabel(ingredientsViewModel.TITLE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, TITLE_FONT_SIZE));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(titleLabel);
        this.add(Box.createRigidArea(new Dimension(0, RIGID_AREA_HEIGHT_20)));

        // Ingredient list
        ingredientListModel = new DefaultListModel<>();
        ingredientList = new JList<>(ingredientListModel);
        ingredientList.setCellRenderer(new IngredientCellRenderer());
        final JScrollPane scrollPane = new JScrollPane(ingredientList);
        scrollPane.setPreferredSize(new Dimension(SCROLL_PANE_WIDTH, SCROLL_PANE_HEIGHT));
        this.add(scrollPane);
        this.add(Box.createRigidArea(new Dimension(0, RIGID_AREA_HEIGHT_10)));

        // Ingredient name input field
        ingredientInputField = new JTextField();
        ingredientInputField.setMaximumSize(new Dimension(INPUT_FIELD_WIDTH, INPUT_FIELD_HEIGHT));
        ingredientInputField.setBorder(BorderFactory.createTitledBorder(ingredientsViewModel.INGREDIENT_NAME));
        this.add(ingredientInputField);
        this.add(Box.createRigidArea(new Dimension(0, RIGID_AREA_HEIGHT_10)));

        // Quantity input field (for integer quantity)
        quantityInputField = new JTextField();
        quantityInputField.setMaximumSize(new Dimension(INPUT_FIELD_WIDTH, INPUT_FIELD_HEIGHT));
        quantityInputField.setBorder(BorderFactory.createTitledBorder(ingredientsViewModel.QUANTITY));
        this.add(quantityInputField);
        this.add(Box.createRigidArea(new Dimension(0, RIGID_AREA_HEIGHT_10)));

        // Buttons
        addButton = createButton(ingredientsViewModel.ADD_BUTTON, "add");
        deleteButton = createButton(ingredientsViewModel.DELETE_BUTTON, "delete");
        returnButton = createButton(ingredientsViewModel.RETURN_BUTTON, "return");
        increaseButton = createButton(ingredientsViewModel.INCREASE_BUTTON, "increment");
        decreaseButton = createButton(ingredientsViewModel.DECREASE_BUTTON, "decrement");

        this.add(addButton);
        this.add(deleteButton);
        this.add(increaseButton);
        this.add(decreaseButton);
        this.add(returnButton);
        this.add(Box.createRigidArea(new Dimension(0, RIGID_AREA_HEIGHT_20)));
    }

    /**
     *  Creates a button with the specified text and action command.
     * @param text
     * @param actionCommand
     * @return
     */
    private JButton createButton(String text, String actionCommand) {
        final JButton button = new JButton(text);
        button.setActionCommand(actionCommand);
        button.addActionListener(this);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    /**
     * Sets the controller for the ingredients view.
     * @param controller the controller for managing the ingredients view
     */
    public void setIngredientsController(IngredientsController controller) {
        this.ingredientsController = controller;
    }

    /**
     * Handles the action events for the buttons in the view.
     * @param e the action event to handle
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "add" -> handleAddIngredient();
            case "delete" -> handleDeleteIngredient();
            case "increment" -> changeIngredientQuantity(DEFAULT_QUANTITY);
            case "decrement" -> changeIngredientQuantity(-DEFAULT_QUANTITY);
            default -> ingredientsController.returnTomain();
        }
    }

    /**
     * Handles the addition of an ingredient to the list.
     */
    private void handleAddIngredient() {
        final String ingredient = ingredientInputField.getText().trim();
        final String quantityText = quantityInputField.getText().trim();

        if (ingredient.isEmpty() || quantityText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingredient name and quantity must be filled.",
                    "Missing Input", JOptionPane.WARNING_MESSAGE);
        }
        else {
            try {
                final int quantity = Integer.parseInt(quantityText);
                ingredientsController.addIngredient(ingredient, quantity);
                updateIngredientList();
                ingredientInputField.setText("");
                quantityInputField.setText("");
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a valid integer for quantity.",
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Handles the deletion of an ingredient from the list.
     */
    private void handleDeleteIngredient() {
        final int selectedIndex = ingredientList.getSelectedIndex();
        if (selectedIndex != -1) {
            final String ingredient = ingredientListModel.getElementAt(selectedIndex).split(regex)[0];
            ingredientsController.deleteIngredient(ingredient);
            updateIngredientList();
        }
        else {
            JOptionPane.showMessageDialog(this, "No ingredient selected.", "Error",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Changes the quantity of the selected ingredient by the specified delta.
     * If the quantity is 0 or below after the change, the user is prompted to delete the ingredient.
     * @param delta the amount to adjust the quantity by (positive to increase, negative to decrease)
     */
    private void changeIngredientQuantity(int delta) {
        final int selectedIndex = ingredientList.getSelectedIndex();
        if (selectedIndex != -1) {
            final String ingredient = ingredientListModel.getElementAt(selectedIndex).split(regex)[0];
            final int newQuantity = ingredientsController.changeIngredientAmount(ingredient, delta);

            // If the quantity is 0 or below after the change
            if (newQuantity < DEFAULT_QUANTITY) {
                final int response = JOptionPane.showConfirmDialog(this,
                        "The quantity of this ingredient is 1. Do you want to delete it?",
                        "Delete Ingredient", JOptionPane.YES_NO_OPTION);

                // If the user confirms, delete the ingredient
                if (response == JOptionPane.YES_OPTION) {
                    ingredientsController.deleteIngredient(ingredient);
                }
                else {
                    // If user chooses not to delete, reset the quantity to 1 or another default value
                    ingredientsController.addIngredient(ingredient, DEFAULT_QUANTITY);
                }
            }
            updateIngredientList();
            // Reselect the previously selected index
            if (selectedIndex < ingredientListModel.getSize()) {
                ingredientList.setSelectedIndex(selectedIndex);
            }
            else if (ingredientListModel.getSize() > 0) {
                ingredientList.setSelectedIndex(ingredientListModel.getSize() - 1);
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "No ingredient selected.", "Error",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Updates the list of ingredients displayed in the view.
     */
    private void updateIngredientList() {
        ingredientListModel.clear();
        for (String ingredient : ingredientsController.getIngredients()) {
            ingredientListModel.addElement(ingredient);
        }
    }

    /**
     * Custom cell renderer for the ingredient list.
     * Highlights ingredients with a quantity of 0.
     */
    final class IngredientCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                                                      boolean cellHasFocus) {
            final JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
                    cellHasFocus);

            // Check if the ingredient has a quantity of 0
            final String text = (String) value;
            final String[] parts = text.split(regex);
            final int quantity = Integer.parseInt(parts[1].split(" ")[0].trim());

            if (quantity == 0 && !isSelected) {
                label.setBackground(HIGHLIGHT_COLOR);
                label.setForeground(Color.WHITE);
            }
            else {
                label.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
                label.setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
            }

            return label;
        }
    }
}
