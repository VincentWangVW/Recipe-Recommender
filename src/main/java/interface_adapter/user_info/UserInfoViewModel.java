package interface_adapter.user_info;

import interface_adapter.ViewModel;

/**
 * The UserInfoViewModel class is responsible for managing the view-related state
 * of the user information screen. It holds the user's shopping amount and allergy data,
 * and provides this information to the view to be displayed.
 * This class extends the ViewModel class, providing functionality to handle
 * and update the state related to user information.
 */
public class UserInfoViewModel extends ViewModel<UserInfoState> {

    /**
     * Constructor that initializes the UserInfoViewModel with the view name
     * and sets the initial state of the view.
     */
    public UserInfoViewModel() {
        super("USER_INFO_SCREEN");
        setState(new UserInfoState());
    }
}
