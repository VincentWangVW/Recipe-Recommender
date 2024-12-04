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

    private JButton createButton(String text, String actionCommand) {
        final JButton button = new JButton(text);
        button.setActionCommand(actionCommand);
        button.addActionListener(this);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    public void setIngredientsController(IngredientsController controller) {
        this.ingredientsController = controller;
    }

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

    private void updateIngredientList() {
        ingredientListModel.clear();
        for (String ingredient : ingredientsController.getIngredients()) {
            ingredientListModel.addElement(ingredient);
        }
    }

    // highlighting ingredient with quantity 0
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
