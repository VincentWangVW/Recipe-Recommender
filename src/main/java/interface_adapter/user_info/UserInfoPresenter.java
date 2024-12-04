package interface_adapter.user_info;

import java.util.HashSet;

import interface_adapter.ViewManagerModel;
import use_case.user_info.UserInfoOutputBoundary;

/**
 * The UserInfoPresenter class is responsible for handling the presentation logic
 * related to user information, such as allergies and shopping preferences.
 * It interacts with the ViewManagerModel to update the user interface and manage
 * the state transitions.
 */
public class UserInfoPresenter implements UserInfoOutputBoundary {
    private HashSet<String> allergies;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs a UserInfoPresenter with the provided ViewManagerModel.
     *
     * @param viewManagerModel The ViewManagerModel to manage the view's state.
     */
    public UserInfoPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Handles the action to return to the main screen by updating the state
     * of the ViewManagerModel.
     */
    @Override
    public void returnTomain() {
        viewManagerModel.setState("MAIN_SCREEN");
        viewManagerModel.firePropertyChanged();
    }
}
