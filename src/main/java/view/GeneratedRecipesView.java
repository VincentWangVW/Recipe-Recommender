package view;

import entity.Recipe;
import interface_adapter.generated_recipes.GeneratedController;
import interface_adapter.generated_recipes.GeneratedViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class GeneratedRecipesView extends JPanel implements ActionListener {
    private GeneratedController generatedController;
    private JTable recipesTable;
    private DefaultTableModel tableModel;
    private final JButton returnButton;
    private GeneratedViewModel generatedViewModel;
    private final JButton generateButton;

    public GeneratedRecipesView(GeneratedViewModel generatedViewModel) {
        this.generatedViewModel = generatedViewModel;
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Recipes", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"Name", "URL", "Missing Ingredients"};
        tableModel = new DefaultTableModel(columnNames, 0);
        recipesTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(recipesTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        generateButton = new JButton("Generate Recipes");
        generateButton.addActionListener(this);
        buttonPanel.add(generateButton);

        returnButton = new JButton("Return");
        returnButton.addActionListener(this);
        buttonPanel.add(returnButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setGeneratedController(GeneratedController generatedController) {
        this.generatedController = generatedController;
    }

    public void addRecipe(String name, String url, Integer missingIngredients) {
        tableModel.addRow(new Object[]{name, url, missingIngredients});
    }

    public void addRecipes(ArrayList<Recipe> recipes) {
        for (Recipe recipe : recipes) {
            addRecipe(recipe.getName(), recipe.getUrl(), recipe.getMissingItems());
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(returnButton)) {
            generatedController.return_to_main();
        } else if (evt.getSource().equals(generateButton)) {
            tableModel.setRowCount(0);
            try {
                ArrayList<Recipe> recipes = generatedController.generateRecipes();
                sleep(1000); // fix for now bc we get 1 api call a second (hard to notice)

                if (generatedController.getGenerationType().equals("Holiday") &&
                        generatedController.getHoliday().equals("No Holiday Today!")) {
                    JOptionPane.showMessageDialog(
                            this,
                            "No Holiday Today!",
                            "No Holiday",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }

                else if (recipes == null || recipes.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            this,
                            "No Recipes Found, Try Again.",
                            "No Recipes Found",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }

                else {
                    addRecipes(recipes);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        this,
                        "An error occurred while generating recipes: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}