package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import interface_adapter.user_info.UserInfoController;
import interface_adapter.user_info.UserInfoViewModel;

public class UserInfoView extends JPanel implements ActionListener {
    private static final int BORDER_GAP = 10;
    private static final int TITLE_FONT_SIZE = 18;
    private static final int TEXT_FIELD_WIDTH = 300;
    private static final int TEXT_FIELD_HEIGHT = 30;
    private static final int SCROLL_PANE_WIDTH = 300;
    private static final int SCROLL_PANE_HEIGHT = 150;

    private UserInfoController userInfoController;
    private final JCheckBox dairyFreeCheckBox;
    private final JCheckBox glutenFreeCheckBox;
    private final JTextField shopAmountTextField;
    private final DefaultListModel<String> allergiesListModel;
    private final JList<String> allergiesList;
    private final JButton addAllergyButton;
    private final JButton deleteAllergyButton;
    private final JButton returnButton;
    private final UserInfoViewModel userInfoViewModel;

    public UserInfoView(UserInfoViewModel userInfoViewModel) {
        this.userInfoViewModel = userInfoViewModel;
        this.setLayout(new BorderLayout(BORDER_GAP, BORDER_GAP));
        this.setBorder(BorderFactory.createEmptyBorder(BORDER_GAP, BORDER_GAP, BORDER_GAP, BORDER_GAP));

        // Title
        final JLabel titleLabel = new JLabel("User Information", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, TITLE_FONT_SIZE));
        this.add(titleLabel, BorderLayout.NORTH);

        // Main content panel
        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.add(mainPanel, BorderLayout.CENTER);

        // Checkboxes
        final JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        dairyFreeCheckBox = new JCheckBox("Dairy-Free");
        glutenFreeCheckBox = new JCheckBox("Gluten-Free");
        checkBoxPanel.add(dairyFreeCheckBox);
        checkBoxPanel.add(glutenFreeCheckBox);
        mainPanel.add(checkBoxPanel);

        // Shop amount
        shopAmountTextField = new JTextField();
        shopAmountTextField.setMaximumSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));
        shopAmountTextField.setBorder(BorderFactory.createTitledBorder("Shop Amount (integer)"));
        mainPanel.add(shopAmountTextField);

        // Allergies list
        allergiesListModel = new DefaultListModel<>();
        allergiesList = new JList<>(allergiesListModel);
        final JScrollPane scrollPane = new JScrollPane(allergiesList);
        scrollPane.setPreferredSize(new Dimension(SCROLL_PANE_WIDTH, SCROLL_PANE_HEIGHT));
        scrollPane.setBorder(BorderFactory.createTitledBorder("Allergies"));
        mainPanel.add(scrollPane);

        // Buttons for allergies
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        addAllergyButton = new JButton("Add Allergy");
        addAllergyButton.addActionListener(this);
        buttonPanel.add(addAllergyButton);
        deleteAllergyButton = new JButton("Delete Selected");
        deleteAllergyButton.addActionListener(this);
        buttonPanel.add(deleteAllergyButton);
        mainPanel.add(buttonPanel);

        // Save and return buttons
        final JPanel actionButtonPanel = new JPanel();
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
            final String newAllergy = JOptionPane.showInputDialog(this, "Enter new allergy:");
            if (newAllergy != null && !newAllergy.trim().isEmpty()) {
                userInfoController.addAllergy(newAllergy);
            }
            updateView();
        }
        else if (e.getSource().equals(deleteAllergyButton)) {
            final int selectedIndex = allergiesList.getSelectedIndex();
            if (selectedIndex != -1) {
                userInfoController.deleteAllergy(allergiesListModel.get(selectedIndex));
            }
            else {
                JOptionPane.showMessageDialog(this, "No allergy selected!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            updateView();
        }
        else if (e.getSource().equals(returnButton)) {
            final String shopAmountText = shopAmountTextField.getText();
            if (!shopAmountText.trim().isEmpty()) {
                try {
                    Integer.parseInt(shopAmountText);
                    userInfoController.changeShopAmount(Integer.parseInt(shopAmountText));
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Shop amount must be an integer!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            userInfoController.returnTomain();
        }
    }
}
