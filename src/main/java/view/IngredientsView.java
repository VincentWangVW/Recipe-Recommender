package view;

import interface_adapter.ingredients_manager.IngredientsController;
import interface_adapter.ingredients_manager.IngredientsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IngredientsView extends JPanel implements ActionListener {
    private IngredientsController ingredientsController;
    private final DefaultListModel<String> ingredientListModel;
    private final JList<String> ingredientList;
    private final JTextField ingredientInputField;
    private final JTextField quantityInputField;  // Field for quantity (integer)
    private final JButton addButton;
    private final JButton deleteButton;
    private final JButton returnButton;
    private final JButton increaseButton;
    private final JButton decreaseButton;

    public IngredientsView(IngredientsViewModel ingredientsViewModel) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Title
        JLabel titleLabel = new JLabel(ingredientsViewModel.TITLE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(titleLabel);
        this.add(Box.createRigidArea(new Dimension(0, 20)));

        // Ingredient list
        ingredientListModel = new DefaultListModel<>();
        ingredientList = new JList<>(ingredientListModel);
        ingredientList.setCellRenderer(new IngredientCellRenderer()); // Set custom renderer
        JScrollPane scrollPane = new JScrollPane(ingredientList);
        scrollPane.setPreferredSize(new Dimension(300, 150));
        this.add(scrollPane);
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        // Ingredient name input field
        ingredientInputField = new JTextField();
        ingredientInputField.setMaximumSize(new Dimension(300, 30));
        ingredientInputField.setBorder(BorderFactory.createTitledBorder(ingredientsViewModel.INGREDIENT_NAME));
        this.add(ingredientInputField);
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        // Quantity input field (for integer quantity)
        quantityInputField = new JTextField();
        quantityInputField.setMaximumSize(new Dimension(300, 30));
        quantityInputField.setBorder(BorderFactory.createTitledBorder(ingredientsViewModel.QUANTITY));
        this.add(quantityInputField);
        this.add(Box.createRigidArea(new Dimension(0, 10)));

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
        this.add(Box.createRigidArea(new Dimension(0, 20)));
    }

    private JButton createButton(String text, String actionCommand) {
        JButton button = new JButton(text);
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
            case "increment" -> changeIngredientQuantity(1);
            case "decrement" -> changeIngredientQuantity(-1);
            case "return" -> ingredientsController.return_to_main();
        }
    }

    private void handleAddIngredient() {
        String ingredient = ingredientInputField.getText().trim();
        String quantityText = quantityInputField.getText().trim();

        if (ingredient.isEmpty() || quantityText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingredient name and quantity must be filled.", "Missing Input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityText);
            ingredientsController.addIngredient(ingredient, quantity);
            updateIngredientList();
            ingredientInputField.setText(""); // Clear input fields
            quantityInputField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer for quantity.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleDeleteIngredient() {
        int selectedIndex = ingredientList.getSelectedIndex();
        if (selectedIndex != -1) {
            String ingredient = ingredientListModel.getElementAt(selectedIndex).split(" - ")[0];
            ingredientsController.deleteIngredient(ingredient); // Controller handles logic
            updateIngredientList();
        } else {
            JOptionPane.showMessageDialog(this, "No ingredient selected.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void changeIngredientQuantity(int delta) {
        int selectedIndex = ingredientList.getSelectedIndex();
        if (selectedIndex != -1) {
            String ingredient = ingredientListModel.getElementAt(selectedIndex).split(" - ")[0];
            int newQuantity = ingredientsController.changeIngredientAmount(ingredient, delta);

            // If the quantity is 0 or below after the change
            if (newQuantity < 1) {
                int response = JOptionPane.showConfirmDialog(this, "The quantity of this ingredient is 1. Do you want to delete it?", "Delete Ingredient", JOptionPane.YES_NO_OPTION);

                // If the user confirms, delete the ingredient
                if (response == JOptionPane.YES_OPTION) {
                    ingredientsController.deleteIngredient(ingredient); // Delete ingredient
                } else {
                    // If user chooses not to delete, reset the quantity to 1 or another default value
                    ingredientsController.addIngredient(ingredient, 1); // Reset to 1 or other value
                }
            }
            updateIngredientList();
            // Reselect the previously selected index
            if (selectedIndex < ingredientListModel.getSize()) {
                ingredientList.setSelectedIndex(selectedIndex);
            } else if (ingredientListModel.getSize() > 0) {
                ingredientList.setSelectedIndex(ingredientListModel.getSize() - 1);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No ingredient selected.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateIngredientList() {
        ingredientListModel.clear();
        for (String ingredient : ingredientsController.getIngredients()) {
            ingredientListModel.addElement(ingredient);
        }
    }

    // highlighting ingredient with quantity 0
    private static class IngredientCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            // Check if the ingredient has a quantity of 0
            String text = (String) value;
            String[] parts = text.split(" - ");
            int quantity = Integer.parseInt(parts[1].split(" ")[0].trim());

            if (quantity == 0 && !isSelected) {
                label.setBackground(new Color(255, 182, 193));  // Highlight with red background
                label.setForeground(Color.WHITE);
            } else {
                label.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
                label.setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
            }

            return label;
        }
    }
}