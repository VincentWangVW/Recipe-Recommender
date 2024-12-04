package interface_adapter.user_info;

import interface_adapter.ViewModel;

/**
 * Represents the ViewModel for the user info screen.
 *
 * @null The state of this ViewModel may be null if not explicitly set.
 */
public class UserInfoViewModel extends ViewModel<UserInfoState> {
    public UserInfoViewModel() {
        super("USER_INFO_SCREEN");
        setState(new UserInfoState());
    }
}
