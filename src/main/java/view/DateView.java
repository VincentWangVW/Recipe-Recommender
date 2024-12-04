package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import interface_adapter.datescreen.DateController;
import interface_adapter.datescreen.DateViewModel;

public class DateView extends JPanel implements ActionListener {
    private static final int EIGHTEEN = 18;
    private static final int TEN = 10;
    private static final int TWENTY = 20;
    private DateController dateController;
    private final JLabel dateLabel;
    private final JLabel seasonLabel;
    private final JLabel holidayLabel;
    private final JButton returnButton;
    private final DateViewModel dateViewModel;

    /**
     * Constructs a {@code DateView} with the specified {@code DateViewModel}.
     *
     * @param dateViewModel the view model for the date screen
     */
    public DateView(DateViewModel dateViewModel) {
        this.dateViewModel = dateViewModel;
        dateViewModel.addPropertyChangeListener(evt -> updateLabels());

        // Title label with font and alignment
        final JLabel titleLabel = new JLabel(dateViewModel.TITLE_LABEL);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, EIGHTEEN));
        this.add(titleLabel);

        // Create a border for the labels
        final Border labelBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1),
                BorderFactory.createEmptyBorder(5, TEN, 5, TEN)
        );

        // Date label with border
        dateLabel = new JLabel();
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dateLabel.setBorder(labelBorder);
        this.add(Box.createRigidArea(new Dimension(0, TEN)));
        this.add(dateLabel);

        // Season label with border
        seasonLabel = new JLabel();
        seasonLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        seasonLabel.setBorder(labelBorder);
        this.add(Box.createRigidArea(new Dimension(0, TEN)));
        this.add(seasonLabel);

        // Holiday label with border
        holidayLabel = new JLabel();
        holidayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        holidayLabel.setBorder(labelBorder);
        this.add(Box.createRigidArea(new Dimension(0, TEN)));
        this.add(holidayLabel);

        // Return button
        returnButton = new JButton(dateViewModel.RETURN_BUTTON_LABEL);
        returnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        returnButton.addActionListener(this);
        this.add(Box.createRigidArea(new Dimension(0, TWENTY)));
        this.add(returnButton);

        // Set layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    /**
     * Sets the date controller for this view.
     *
     * @param dateController the date controller to set
     */
    public void setDateController(DateController dateController) {
        this.dateController = dateController;
        updateLabels();
    }

    /**
     * Updates the labels in the view with the current date information.
     */
    private void updateLabels() {
        dateLabel.setText(dateViewModel.DATE_LABEL + dateController.getDate());
        seasonLabel.setText(dateViewModel.SEASON_LABEL + dateController.getSeason());
        holidayLabel.setText(dateViewModel.HOLIDAY_LABEL + dateController.getHoliday());
    }

    /**
     * Handles the action events for the return button.
     *
     * @param evt the action event to handle
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(returnButton)) {
            dateController.returnTomain();
        }
    }
}
