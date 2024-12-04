package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.main.MainController;

/**
 * The main view for the application.
 * This class manages the UI components for the main screen and handles user actions.
 */
public class MainView extends JPanel implements ActionListener {
    private static final int TITLE_FONT_SIZE = 24;
    private static final int BUTTON_FONT_SIZE = 30;
    private static final int GRID_ROWS = 4;
    private static final int GRID_COLS = 1;
    private static final int GRID_HGAP = 10;
    private static final int GRID_VGAP = 10;
    private static final int BORDER_THICKNESS = 2;
    private static final int BORDER_PADDING = 10;
    private static final int PANEL_PADDING = 20;
    private static final Color BUTTON_BACKGROUND_COLOR = new Color(70, 130, 180);
    private static final Color BUTTON_FOREGROUND_COLOR = Color.WHITE;
    private static final Color BUTTON_BORDER_COLOR = new Color(30, 144, 255);
    private static final String TITLE_HTML = "<html><span style='color: #FF0000;'>Recipe</span> "
            + "<span style='color: #0000FF;'>Recommendation</span></html>";

    private MainController mainController;
    private final JButton ingredients = new JButton("Ingredients");
    private final JButton userInfo = new JButton("User Info");
    private final JButton date = new JButton("Date Information");
    private final JButton generateRecipe = new JButton("Generate Recipe");

    /**
     * Constructs a new MainView with the specified main controller.
     */
    public MainView() {
        final JLabel title = new JLabel(TITLE_HTML);
        title.setFont(new Font("Arial", Font.BOLD, TITLE_FONT_SIZE));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);
        final JPanel buttons = new JPanel(new GridLayout(GRID_ROWS, GRID_COLS, GRID_HGAP, GRID_VGAP));
        buttons.setBorder(BorderFactory.createEmptyBorder(PANEL_PADDING, PANEL_PADDING, PANEL_PADDING, PANEL_PADDING));
        customizeButton(ingredients);
        customizeButton(userInfo);
        customizeButton(date);
        customizeButton(generateRecipe);
        buttons.add(ingredients);
        buttons.add(userInfo);
        buttons.add(date);
        buttons.add(generateRecipe);
        ingredients.addActionListener(this);
        userInfo.addActionListener(this);
        date.addActionListener(this);
        generateRecipe.addActionListener(this);
        this.add(buttons);
    }

    /**
     * Sets the main controller for this view.
     *
     * @param mainController the main controller
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * React to a button click that results in evt.
     * @param evt the event that triggered this action
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(ingredients)) {
            mainController.switchToIngredientsView();
        }
        else if (evt.getSource().equals(userInfo)) {
            mainController.switchToUserInfoView();
        }
        else if (evt.getSource().equals(date)) {
            mainController.switchToDateView();
        }
        else if (evt.getSource().equals(generateRecipe)) {
            mainController.switchToRecipeView();
        }
    }

    /**
     * Customizes the appearance of the specified button.
     *
     * @param button the button to customize
     */
    private void customizeButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, BUTTON_FONT_SIZE));
        button.setBackground(BUTTON_BACKGROUND_COLOR);
        button.setForeground(BUTTON_FOREGROUND_COLOR);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BUTTON_BORDER_COLOR, BORDER_THICKNESS),
                BorderFactory.createEmptyBorder(BORDER_PADDING, BORDER_PADDING, BORDER_PADDING, BORDER_PADDING)));
    }
}
