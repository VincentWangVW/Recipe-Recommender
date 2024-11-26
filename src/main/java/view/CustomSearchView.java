package view;

import interface_adapter.custom_search.CustomSearchController;
import interface_adapter.custom_search.CustomSearchViewModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class CustomSearchView extends JPanel implements ActionListener {
    private CustomSearchController customSearchController;
    private final JLabel titleLabel;
    private final JLabel errorLabel;
    private final JCheckBox followUserInfoCheckBox;
    private final JButton searchButton;
    private final JButton returnButton;
    private final JTextArea resultsArea;
    private final CustomSearchViewModel viewModel;

    public CustomSearchView(CustomSearchViewModel viewModel) {
        this.viewModel = viewModel;
        viewModel.addPropertyChangeListener(evt -> updateView());

        // Set layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Title
        titleLabel = new JLabel("Custom Recipe Search");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(titleLabel);

        // Add space
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        // Checkbox for user info
        followUserInfoCheckBox = new JCheckBox("Follow User Preferences");
        followUserInfoCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(followUserInfoCheckBox);

        // Add space
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        // Search button
        searchButton = new JButton("Search");
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchButton.setActionCommand("search");
        searchButton.addActionListener(this);
        this.add(searchButton);

        // Add space
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        // Results area
        resultsArea = new JTextArea(10, 30);
        resultsArea.setEditable(false);
        resultsArea.setBorder(BorderFactory.createTitledBorder("Search Results"));
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        this.add(scrollPane);

        // Add space
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        // Error label
        errorLabel = new JLabel();
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        errorLabel.setForeground(Color.RED);
        this.add(errorLabel);

        // Add space
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        // Return button
        returnButton = new JButton("Return to Main");
        returnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        returnButton.setActionCommand("return");
        returnButton.addActionListener(this);
        this.add(returnButton);
    }

    public void setCustomSearchController(CustomSearchController controller) {
        this.customSearchController = controller;
    }

    private void updateView() {
        // Get the current state from the view model
        var state = viewModel.getState();

        if (state.getRecipes() != null) {
            // Display the search results
            StringBuilder resultsBuilder = new StringBuilder();
            for (Map.Entry<Integer, List<String>> entry : state.getRecipes().entrySet()) {
                resultsBuilder.append("Recipe: ").append(entry.getValue().get(0)).append("\n");
                resultsBuilder.append("URL: ").append(entry.getValue().get(1)).append("\n");
                resultsBuilder.append(entry.getValue().get(2)).append("\n\n");
            }
            resultsArea.setText(resultsBuilder.toString());
            errorLabel.setText("");
        } else if (state.getErrorMessage() != null) {
            // Display the error message
            resultsArea.setText("");
            errorLabel.setText(state.getErrorMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("search")) {
            boolean followUserInfo = followUserInfoCheckBox.isSelected();
            customSearchController.performCustomSearch(followUserInfo);
        } else if (e.getActionCommand().equals("return")) {
            customSearchController.returnToMain();
        }
    }
}
