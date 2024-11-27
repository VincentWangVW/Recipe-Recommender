package interface_adapter.datescreen;

import interface_adapter.ViewModel;

public class DateViewModel extends ViewModel<DateState> {
    public static final String TITLE_LABEL = "Date Information";
    public static final String RETURN_BUTTON_LABEL = "Return";
    public static final String DATE_LABEL = "Today's Date: ";
    public static final String SEASON_LABEL = "Current Season: ";
    public static final String HOLIDAY_LABEL = "Holiday: ";

    public DateViewModel() {
        super("DATE_SCREEN");
        setState(new DateState());
    }
}