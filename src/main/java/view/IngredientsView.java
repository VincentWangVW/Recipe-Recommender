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
    private final JTextField amountInputField;  // Field for amount (integer)
    private final JButton addButton;
    private final JButton deleteButton;
    private final JButton returnButton;

    public IngredientsView(IngredientsViewModel ingredientsViewModel) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Title
        JLabel titleLabel = new JLabel("Ingredients Manager");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(titleLabel);
        this.add(Box.createRigidArea(new Dimension(0, 20)));

        // Ingredient list
        ingredientListModel = new DefaultListModel<>();
        ingredientList = new JList<>(ingredientListModel);
        JScrollPane scrollPane = new JScrollPane(ingredientList);
        scrollPane.setPreferredSize(new Dimension(300, 150));
        this.add(scrollPane);
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        // Ingredient name input field
        ingredientInputField = new JTextField();
        ingredientInputField.setMaximumSize(new Dimension(300, 30));
        ingredientInputField.setBorder(BorderFactory.createTitledBorder("Ingredient Name"));
        this.add(ingredientInputField);
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        // Amount input field (for integer amount)
        amountInputField = new JTextField();
        amountInputField.setMaximumSize(new Dimension(300, 30));
        amountInputField.setBorder(BorderFactory.createTitledBorder("Amount (integer)"));
        this.add(amountInputField);
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        // Buttons
        addButton = createButton("Add Ingredient", "add");
        deleteButton = createButton("Delete Selected", "delete");
        returnButton = createButton("Return to Main", "return");

        this.add(addButton);
        this.add(deleteButton);
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
            case "return" -> ingredientsController.return_to_main();
        }
    }

    private void handleAddIngredient() {
        String ingredient = ingredientInputField.getText().trim();
        String amountText = amountInputField.getText().trim();

        if (ingredient.isEmpty() || amountText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Both ingredient name and amount must be filled.", "Missing Input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int amount = Integer.parseInt(amountText); // Parse amount as integer
            ingredientsController.addIngredient(ingredient, amount); // Controller handles logic
            ingredientListModel.addElement(ingredient + " - " + amount); // Update UI
            ingredientInputField.setText(""); // Clear input fields
            amountInputField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer for amount.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleDeleteIngredient() {
        int selectedIndex = ingredientList.getSelectedIndex();
        if (selectedIndex != -1) {
            String ingredient = ingredientListModel.getElementAt(selectedIndex);
            ingredientsController.deleteIngredient(ingredient); // Controller handles logic
            ingredientListModel.remove(selectedIndex); // Update UI
        } else {
            JOptionPane.showMessageDialog(this, "No ingredient selected.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
}

