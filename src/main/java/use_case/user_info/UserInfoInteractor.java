package use_case.user_info;

import entity.UserPreferences;

import java.util.HashSet;

public class UserInfoInteractor implements UserInfoInputBoundary {
    private final HashSet<String> allergies = new HashSet<>();
    private final UserInfoOutputBoundary outputBoundary;
    private final UserPreferences userPreferences;

    public UserInfoInteractor(UserInfoOutputBoundary outputBoundary, UserPreferences userPreferences) {
        this.outputBoundary = outputBoundary;
        this.userPreferences = userPreferences;
    }

    @Override
    public void return_to_main() {
        outputBoundary.return_to_main();
    }

    public void addAllergy(String allergyName) {
        allergies.add(allergyName);
        userPreferences.addAllergy(allergyName);
    }

    public void deleteAllergy(String allergyName) {
        allergies.remove(allergyName);
        userPreferences.removeAllergy(allergyName);
    }

    public int changeShopAmount(int shopAmount) {
        userPreferences.setMissingIngredientsLimit(shopAmount);
        return shopAmount;
    }

    @Override
    public String[] getAllergies() {
        return allergies.toArray(new String[0]);
    }
}