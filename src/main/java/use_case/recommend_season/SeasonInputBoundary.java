package use_case.recommend_season;

public interface SeasonInputBoundary {
    void return_to_main();

    String getDate();
    String getSeason();
    // TODO seperate this
    String getHoliday();
}