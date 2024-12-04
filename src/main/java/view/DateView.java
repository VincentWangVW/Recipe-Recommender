package view;

import interface_adapter.datescreen.DateController;
import interface_adapter.datescreen.DateViewModel;

import javax.swing.border.Border;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DateView extends JPanel implements ActionListener {
    private DateController dateController;
    private final JLabel dateLabel;
    private final JLabel seasonLabel;
    private final JLabel holidayLabel;
    private final JButton returnButton;
    private final DateViewModel dateViewModel;

    public DateView(DateViewModel dateViewModel) {
        this.dateViewModel = dateViewModel;
        dateViewModel.addPropertyChangeListener(evt -> updateLabels());

        // Title label with font and alignment
        JLabel titleLabel = new JLabel(dateViewModel.TITLE_LABEL);
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
        returnButton = new JButton(dateViewModel.RETURN_BUTTON_LABEL);
        returnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        returnButton.addActionListener(this);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(returnButton);

        // Set layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void setDateController(DateController dateController) {
        this.dateController = dateController;
        updateLabels();
    }

    private void updateLabels() {
        dateLabel.setText(dateViewModel.DATE_LABEL + dateController.getDate());
        seasonLabel.setText(dateViewModel.SEASON_LABEL + dateController.getSeason());
        holidayLabel.setText(dateViewModel.HOLIDAY_LABEL + dateController.getHoliday());
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(returnButton)) {
            dateController.returnTomain();
        }
    }
}