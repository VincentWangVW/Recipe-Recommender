package view;
import interface_adapter.ViewModel;
import interface_adapter.season.SeasonController;

import data_access.InMemoryDAO;
import interface_adapter.season.SeasonViewModel;
import interface_adapter.season.SeasonDataAccessInterface;

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
    private final SeasonDataAccessInterface seasonDataAccessInterface;

    public SeasonView(SeasonViewModel seasonViewModel) {
        this.seasonViewModel = seasonViewModel;
        this.seasonDataAccessInterface = new InMemoryDAO();

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
        dateLabel = new JLabel("Today's Date: " + seasonDataAccessInterface.get_date());
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dateLabel.setBorder(labelBorder);  // Apply border
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(dateLabel);

        // Season label with border
        seasonLabel = new JLabel("Current Season: " + seasonDataAccessInterface.get_season());
        seasonLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        seasonLabel.setBorder(labelBorder);  // Apply border
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(seasonLabel);

        // Holiday label with border
        holidayLabel = new JLabel("Holiday: " + seasonDataAccessInterface.get_holiday());
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
    }

    /**
     * React to a button click that results in evt.
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(returnButton)) {
            seasonController.return_to_main();
        }
    }
}