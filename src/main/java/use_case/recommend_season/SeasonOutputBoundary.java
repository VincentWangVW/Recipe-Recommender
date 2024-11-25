package use_case.recommend_season;

public interface SeasonOutputBoundary {
    void return_to_main();

    String getDate(String date);
    String getSeason(String season);
}