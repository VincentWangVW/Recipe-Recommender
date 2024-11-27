package use_case.user_info;

import entity.IUserPreferences;
import entity.Ingredient;
import entity.UserPreferences;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;

public class UserInfoInteractor implements UserInfoInputBoundary{
    private final HashSet<String> allergies = new HashSet<>();
    private final UserInfoOutputBoundary outputBoundary;
    public UserInfoInteractor(UserInfoOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void return_to_main(){
        outputBoundary.return_to_main();
    }


    public void addAllergy(String allergyName) {
        allergies.add(allergyName);
    }

    public void deleteAllergy(String allergyName) {
        allergies.remove(allergyName);
    }

    public int changeShopAmount(int shopAmount) {
        return shopAmount;
    }
}
