package interface_adapter.user_info;

import interface_adapter.ViewManagerModel;
import use_case.user_info.UserInfoOutputBoundary;

import java.util.List;
import java.util.HashSet;

public class UserInfoPresenter implements UserInfoOutputBoundary {
    private HashSet<String> allergies;
    private final ViewManagerModel viewManagerModel;

    public UserInfoPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void presentAllergies(HashSet<String> allergies) {
        this.allergies = allergies;
    }

    public HashSet<String> getAllergies() {
        return allergies;
    }

    @Override
    public void return_to_main() {
        viewManagerModel.setState("MAIN_SCREEN");
        viewManagerModel.firePropertyChanged();
    }
}
