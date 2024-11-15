package interface_adapter.season;

import interface_adapter.ViewModel;

public class SeasonViewModel extends ViewModel<SeasonState> {
    public static final String TITLE_LABEL = "Season";
    public static final String RETURN_BUTTON_LABEL = "Return";
    public static final String DATE_LABEL = "Today's Date: ";
    public static final String SEASON_LABEL = "Current Season: ";
    public static final String HOLIDAY_LABEL = "Holiday: ";

    public SeasonViewModel() {
        super("SEASON_SCREEN");
        setState(new SeasonState());
    }
}
