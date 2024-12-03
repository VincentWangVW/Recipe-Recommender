package interface_adapter.datescreen;

import interface_adapter.ViewModel;

/**
 * Represents the view model for the date screen, providing labels and managing the state.
 */
public class DateViewModel extends ViewModel<DateState> {
    public final String TITLE_LABEL = "Date Information";
    public final String RETURN_BUTTON_LABEL = "Return";
    public final String DATE_LABEL = "Today's Date: ";
    public final String SEASON_LABEL = "Current Season: ";
    public final String HOLIDAY_LABEL = "Holiday: ";

    /**
     * Constructs a {@code DateViewModel} and initializes the state with a new {@link DateState}.
     * Sets the screen type to "DATE_SCREEN".
     */
    public DateViewModel() {
        super("DATE_SCREEN");
        setState(new DateState());
    }
}
