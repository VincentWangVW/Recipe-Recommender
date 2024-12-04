package interface_adapter.user_info;

import java.util.HashSet;
import java.util.Set;

import interface_adapter.ViewManagerModel;
import use_case.user_info.UserInfoOutputBoundary;

/**
 * The UserInfoPresenter is responsible for presenting user allergy information
 * and managing interactions between the user interface and the view model.
 *
 * @null The allergies field may be null if not initialized.
 */
public class UserInfoPresenter implements UserInfoOutputBoundary {
    private Set<String> allergies;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs a UserInfoPresenter instance with the specified view manager model.
     *
     * @param viewManagerModel The ViewManagerModel instance to manage view state.
     */
    public UserInfoPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        this.allergies = new HashSet<>();
    }

    @Override
    public void returnTomain() {
        viewManagerModel.setState("MAIN_SCREEN");
        viewManagerModel.firePropertyChanged();
    }
}

