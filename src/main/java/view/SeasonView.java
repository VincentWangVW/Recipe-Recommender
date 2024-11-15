package view;

import interface_adapter.season.SeasonController;
import interface_adapter.season.SeasonViewModel;

import javax.swing.border.Border;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeasonView extends JPanel implements ActionListener {
    private SeasonController seasonController;
    private final JLabel dateLabel;
    private final JLabel seasonLabel;
    private final JLabel holidayLabel;
    private final JButton returnButton;
    private final SeasonViewModel seasonViewModel;

    public SeasonView(SeasonViewModel seasonViewModel) {
        this.seasonViewModel = seasonViewModel;

        // Title label with font and alignment
        JLabel titleLabel = new JLabel("Season");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(titleLabel);

        // Create a border for the labels
        Border labelBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1),  // Outer border
                BorderFactory.createEmptyBorder(5, 10, 5, 10)   // Inner padding
        );

        // Date label with border
        dateLabel = new JLabel();
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dateLabel.setBorder(labelBorder);  // Apply border
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(dateLabel);

        // Season label with border
        seasonLabel = new JLabel();
        seasonLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        seasonLabel.setBorder(labelBorder);  // Apply border
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(seasonLabel);

        // Holiday label with border
        holidayLabel = new JLabel();
        holidayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        holidayLabel.setBorder(labelBorder);  // Apply border
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(holidayLabel);

        // Return button
        returnButton = new JButton("Return");
        returnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        returnButton.addActionListener(this);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(returnButton);

        // Set layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void setSeasonController(SeasonController seasonController) {
        this.seasonController = seasonController;
        updateLabels();
    }

    private void updateLabels() {
        dateLabel.setText("Today's Date: " + seasonController.getDate());
        seasonLabel.setText("Current Season: " + seasonController.getSeason());
        holidayLabel.setText("Holiday: " + seasonController.getHoliday());
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(returnButton)) {
            seasonController.return_to_main();
        }
    }
}