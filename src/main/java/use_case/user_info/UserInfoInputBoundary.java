package use_case.user_info;

public interface UserInfoInputBoundary {
    void return_to_main();

    void addAllergy(String allergyName);

    void deleteAllergy(String allergyName);

    int changeShopAmount(int shopAmount);

    String[] getAllergies();
}