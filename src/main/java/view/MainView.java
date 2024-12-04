package view;
import interface_adapter.ViewModel;
import interface_adapter.main.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JPanel implements ActionListener {
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    private MainController mainController;
    private final JButton ingredients = new JButton("Ingredients");
    private final JButton user_info = new JButton("User Info");
    private final JButton date = new JButton("Date Information");
    private final JButton generate_recipe = new JButton("Generate Recipe");
    public MainView() {

        JLabel title = new JLabel("<html><span style='color: #FF0000;'>Recipe</span> " +
                "<span style='color: #0000FF;'>Recommendation</span></html>");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);
        JPanel buttons = new JPanel(new GridLayout(4, 1, 10, 10));
        buttons.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        customizeButton(ingredients);
        customizeButton(user_info);
        customizeButton(date);
        customizeButton(generate_recipe);
        buttons.add(ingredients);
        buttons.add(user_info);
        buttons.add(date);
        buttons.add(generate_recipe);
        ingredients.addActionListener(this);
        user_info.addActionListener(this);
        date.addActionListener(this);
        generate_recipe.addActionListener(this);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(ingredients)) {
            mainController.switchToIngredientsView();
        }
        else if (evt.getSource().equals(user_info)) {
            mainController.switchToUserInfoView();
        }
        else if (evt.getSource().equals(date)) {
            mainController.switchToDateView();
        }
        else if (evt.getSource().equals(generate_recipe)) {
            mainController.switchToRecipeView();
        }
    }
    private void customizeButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 30));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(30, 144, 255), 2), // 边框颜色
                BorderFactory.createEmptyBorder(10, 10, 10, 10))); // 内边距
    }
}