package interface_adapter.user_info;

import use_case.user_info.UserInfoInputBoundary;
import use_case.user_info.UserInfoInteractor;

public class UserInfoController {
    private final UserInfoInteractor userInfoInteractor;

    public UserInfoController(UserInfoInteractor userInfoInteractor) {
        this.userInfoInteractor = userInfoInteractor;
    }

    public void addAllergy(String allergyName) {
        userInfoInteractor.addAllergy(allergyName);
    }

    public void deleteAllergy(String allergyName) {
        userInfoInteractor.deleteAllergy(allergyName);
    }

    public int changeShopAmount(int shopAmount) {
        return userInfoInteractor.changeShopAmount(shopAmount);
    }

    public void return_to_main() {
        userInfoInteractor.return_to_main();
    }
}
