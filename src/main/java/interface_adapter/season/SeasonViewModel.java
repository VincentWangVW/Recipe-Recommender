package interface_adapter.season;

import interface_adapter.ViewModel;

public class SeasonViewModel extends ViewModel<SeasonState> {
    public SeasonViewModel() {
        super("SEASON_SCREEN");
        setState(new SeasonState());
    }
}
