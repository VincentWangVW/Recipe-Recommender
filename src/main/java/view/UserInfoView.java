package view;

import interface_adapter.ingredients_manager.IngredientsController;
import interface_adapter.user_info.UserInfoController;
import interface_adapter.user_info.UserInfoViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UserInfoView extends JPanel implements ActionListener {
    private UserInfoController userInfoController;
    private final JCheckBox dairyFreeCheckBox, glutenFreeCheckBox;
    private final JTextField shopAmountTextField;
    private final DefaultListModel<String> allergiesListModel;
    private final JList<String> allergiesList;
    private final JButton addAllergyButton, deleteAllergyButton, saveButton, returnButton;
    private final UserInfoViewModel userInfoViewModel;

    public UserInfoView(UserInfoViewModel userInfoViewModel) {
        this.userInfoViewModel = userInfoViewModel;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Title
        JLabel titleLabel = new JLabel("User Information", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(titleLabel);
        this.add(Box.createRigidArea(new Dimension(0, 20)));

        // Main content panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 1));
        this.add(mainPanel);
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        // Checkboxes
        dairyFreeCheckBox = new JCheckBox("Dairy-Free");
        glutenFreeCheckBox = new JCheckBox("Gluten-Free");
        this.add(dairyFreeCheckBox);
        this.add(glutenFreeCheckBox);

        // Shop amount
        shopAmountTextField = new JTextField();
        shopAmountTextField.setMaximumSize(new Dimension(300, 30));
        shopAmountTextField.setBorder(BorderFactory.createTitledBorder("Shop Amount (integer)"));
        this.add(shopAmountTextField);
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        // Save button
        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        this.add(saveButton);

        // Allergies list
        allergiesListModel = new DefaultListModel<>();
        allergiesList = new JList<>(allergiesListModel);
        JScrollPane scrollPane = new JScrollPane(allergiesList);
        scrollPane.setPreferredSize(new Dimension(300, 150));
        this.add(scrollPane);
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        // Buttons for allergies
        JPanel buttonPanel = new JPanel();
        addAllergyButton = new JButton("Add Allergy");
        addAllergyButton.addActionListener(this);
        buttonPanel.add(addAllergyButton);
        deleteAllergyButton = new JButton("Delete Selected");
        deleteAllergyButton.addActionListener(this);
        buttonPanel.add(deleteAllergyButton);
        returnButton = new JButton("Return");
        returnButton.addActionListener(this);
        buttonPanel.add(returnButton);
        this.add(buttonPanel);

    }

    public void setUserInfoController(UserInfoController userInfoController) {
        this.userInfoController = userInfoController;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(addAllergyButton)) {
            // Show dialog to add allergy
            String newAllergy = JOptionPane.showInputDialog(this, "Enter new allergy:");
            if (newAllergy != null && !newAllergy.trim().isEmpty()) {
                allergiesListModel.addElement(newAllergy);
            }
        } else if (e.getSource().equals(deleteAllergyButton)) {
            // Remove selected allergy
            int selectedIndex = allergiesList.getSelectedIndex();
            if (selectedIndex != -1) {
                allergiesListModel.remove(selectedIndex);
            } else {
                JOptionPane.showMessageDialog(this, "No allergy selected!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource().equals(saveButton)) {
            // Save user preferences
            String dairyFree = dairyFreeCheckBox.isSelected() ? "Yes" : "No";
            String glutenFree = glutenFreeCheckBox.isSelected() ? "Yes" : "No";
            String shopAmount = shopAmountTextField.getText().trim();
            ArrayList<String> allergiesList = new ArrayList<>();
            for (int i = 0; i < allergiesListModel.size(); i++) {
                allergiesList.add(allergiesListModel.getElementAt(i));
            }
            String allergies = allergiesList.toString();
            allergies = allergies.substring(1, allergies.length() - 1);

            JOptionPane.showMessageDialog(this, "Preferences Saved:\n" +
                    "Dairy-Free: " + dairyFree + "\n" +
                    "Gluten-Free: " + glutenFree + "\n" +
                    "Shop Amount: " + shopAmount + "\n" +
                    "Allergies: " + allergies);
        } else if (e.getSource().equals(returnButton)) {
            // Return action
            userInfoController.return_to_main();
        }
    }
}