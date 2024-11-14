package use_case.recommend_season;

public class SeasonInteractor implements SeasonInputBoundary {
    public final SeasonOutputBoundary seasonpresenter;

    public SeasonInteractor(SeasonOutputBoundary seasonpresenter) {
        this.seasonpresenter = seasonpresenter;
    }

    @Override
    public void return_to_main() {
        seasonpresenter.return_to_main();
    }
}
