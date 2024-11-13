package interface_adapter.season;

import use_case.season.SeasonInputBoundary;

public class SeasonController {
    private final SeasonInputBoundary seasonInteractor;

    public SeasonController(SeasonInputBoundary seasonInteractor) {
        this.seasonInteractor = seasonInteractor;
    }
    public void return_to_main(){seasonInteractor.return_to_main();}
}
