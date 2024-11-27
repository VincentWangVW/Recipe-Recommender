package interface_adapter.user_info;

import interface_adapter.ViewModel;

public class UserInfoViewModel extends ViewModel<UserInfoState> {
    public UserInfoViewModel() {
        super("USER_INFO_SCREEN");
        setState(new UserInfoState());
    }
}
