package view;

import static java.lang.Thread.sleep;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import entity.Recipe;
import interface_adapter.generated_recipes.GeneratedController;
import interface_adapter.generated_recipes.GeneratedViewModel;

/**
 * The view for displaying generated recipes in the application.
 * This class manages the UI components for displaying generated recipes
 * and handles user actions such as generating recipes.
 */
public class GeneratedRecipesView extends JPanel implements ActionListener {
    private static final int TWENTY_FOUR = 24;
    private static final int THOUSAND = 1000;
    private GeneratedController generatedController;
    private JTable recipesTable;
    private DefaultTableModel tableModel;
    private final JButton returnButton;
    private GeneratedViewModel generatedViewModel;
    private final JButton generateButton;

    /**
     * Constructs a new GeneratedRecipesView with the specified view model.
     *
     * @param generatedViewModel the view model for the generated recipes screen
     */
    public GeneratedRecipesView(GeneratedViewModel generatedViewModel) {
        this.generatedViewModel = generatedViewModel;
        setLayout(new BorderLayout());

        final JLabel titleLabel = new JLabel(generatedViewModel.TITLE_LABLE, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, TWENTY_FOUR));
        add(titleLabel, BorderLayout.NORTH);

        final String[] columnNames = {generatedViewModel.COLUMN_NAME_1, generatedViewModel.COLUMN_NAME_2,
                generatedViewModel.COLUMN_NAME_3};
        tableModel = new DefaultTableModel(columnNames, 0);
        recipesTable = new JTable(tableModel);
        final JScrollPane scrollPane = new JScrollPane(recipesTable);
        add(scrollPane, BorderLayout.CENTER);

        final JPanel buttonPanel = new JPanel();
        generateButton = new JButton(generatedViewModel.GENERATE_BUTTON);
        generateButton.addActionListener(this);
        buttonPanel.add(generateButton);

        returnButton = new JButton(generatedViewModel.RETURN_BUTTON);
        returnButton.addActionListener(this);
        buttonPanel.add(returnButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Sets the controller for the generated recipes view.
     *
     * @param generatedController the controller for managing the generated recipes view
     */
    public void setGeneratedController(GeneratedController generatedController) {
        this.generatedController = generatedController;
    }

    /**
     * Adds a recipe to the table of generated recipes.
     *
     * @param name the name of the recipe
     * @param url the URL of the recipe
     * @param missingIngredients the number of missing ingredients for the recipe
     */
    public void addRecipe(String name, String url, Integer missingIngredients) {
        tableModel.addRow(new Object[]{name, url, missingIngredients});
    }

    /**
     * Adds a list of recipes to the table of generated recipes.
     *
     * @param recipes the list of recipes to be added to the table
     */
    public void addRecipes(ArrayList<Recipe> recipes) {
        for (Recipe recipe : recipes) {
            addRecipe(recipe.getName(), recipe.getUrl(), recipe.getMissingItems());
        }
    }

    /**
     * Handles the action events for the generated recipes view.
     *
     * @param evt the action event to be handled
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(returnButton)) {
            tableModel.setRowCount(0);
            generatedController.returnTomain();
        }
        else if (evt.getSource().equals(generateButton)) {
            tableModel.setRowCount(0);
            try {
                final ArrayList<Recipe> recipes = generatedController.generateRecipes();
                sleep(THOUSAND);
                // fix for now bc we get 1 api call a second (hard to notice)

                if (generatedController.getGenerationType().equals("Holiday")
                        && generatedController.getHoliday().equals("No Holiday Today!")) {
                    JOptionPane.showMessageDialog(
                            this,
                            generatedViewModel.NO_HOLIDAY_TODAY,
                            generatedViewModel.HOLIDAY_TITLE,
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }

                else if (recipes == null || recipes.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            this,
                            generatedViewModel.NO_RECIPES,
                            generatedViewModel.NO_RECIPES_TITLE,
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }

                else {
                    addRecipes(recipes);
                }
            }
            catch (Exception exception) {
                JOptionPane.showMessageDialog(
                        this,
                        generatedViewModel.ERROR_MESSAGE + exception.getMessage(),
                        generatedViewModel.ERROR_TITLE,
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}
