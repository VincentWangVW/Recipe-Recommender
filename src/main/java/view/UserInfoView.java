package view;

import interface_adapter.user_info.UserInfoController;
import interface_adapter.user_info.UserInfoViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInfoView extends JPanel implements ActionListener {
    private UserInfoController userInfoController;
    private final JCheckBox dairyFreeCheckBox, glutenFreeCheckBox;
    private final JTextField shopAmountTextField;
    private final DefaultListModel<String> allergiesListModel;
    private final JList<String> allergiesList;
    private final JButton addAllergyButton, deleteAllergyButton, returnButton;
    private final UserInfoViewModel userInfoViewModel;

    public UserInfoView(UserInfoViewModel userInfoViewModel) {
        this.userInfoViewModel = userInfoViewModel;
        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Title
        JLabel titleLabel = new JLabel("User Information", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(titleLabel, BorderLayout.NORTH);

        // Main content panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.add(mainPanel, BorderLayout.CENTER);

        // Checkboxes
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        dairyFreeCheckBox = new JCheckBox("Dairy-Free");
        glutenFreeCheckBox = new JCheckBox("Gluten-Free");
        checkBoxPanel.add(dairyFreeCheckBox);
        checkBoxPanel.add(glutenFreeCheckBox);
        mainPanel.add(checkBoxPanel);

        // Shop amount
        shopAmountTextField = new JTextField();
        shopAmountTextField.setMaximumSize(new Dimension(300, 30));
        shopAmountTextField.setBorder(BorderFactory.createTitledBorder("Shop Amount (integer)"));
        mainPanel.add(shopAmountTextField);

        // Allergies list
        allergiesListModel = new DefaultListModel<>();
        allergiesList = new JList<>(allergiesListModel);
        JScrollPane scrollPane = new JScrollPane(allergiesList);
        scrollPane.setPreferredSize(new Dimension(300, 150));
        scrollPane.setBorder(BorderFactory.createTitledBorder("Allergies"));
        mainPanel.add(scrollPane);

        // Buttons for allergies
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        addAllergyButton = new JButton("Add Allergy");
        addAllergyButton.addActionListener(this);
        buttonPanel.add(addAllergyButton);
        deleteAllergyButton = new JButton("Delete Selected");
        deleteAllergyButton.addActionListener(this);
        buttonPanel.add(deleteAllergyButton);
        mainPanel.add(buttonPanel);

        // Save and return buttons
        JPanel actionButtonPanel = new JPanel();
        actionButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        returnButton = new JButton("Return");
        returnButton.addActionListener(this);
        actionButtonPanel.add(returnButton);
        this.add(actionButtonPanel, BorderLayout.SOUTH);
    }

    public void setUserInfoController(UserInfoController userInfoController) {
        this.userInfoController = userInfoController;
    }

    private void updateView() {
        allergiesListModel.clear();
        for (String allergy : userInfoController.getAllergies()) {
            allergiesListModel.addElement(allergy);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(addAllergyButton)) {
            String newAllergy = JOptionPane.showInputDialog(this, "Enter new allergy:");
            if (newAllergy != null && !newAllergy.trim().isEmpty()) {
                userInfoController.addAllergy(newAllergy);
            }
            updateView();
        } else if (e.getSource().equals(deleteAllergyButton)) {
            int selectedIndex = allergiesList.getSelectedIndex();
            if (selectedIndex != -1) {
                userInfoController.deleteAllergy(allergiesListModel.get(selectedIndex));
            } else {
                JOptionPane.showMessageDialog(this, "No allergy selected!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            updateView();
        } else if (e.getSource().equals(returnButton)) {
            String shopAmountText = shopAmountTextField.getText();
            if (!shopAmountText.trim().isEmpty()) {
                try {
                    Integer.parseInt(shopAmountText);
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Shop amount must be an integer!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                userInfoController.changeShopAmount(Integer.parseInt(shopAmountText));
            }
            userInfoController.return_to_main();
        }
    }
}