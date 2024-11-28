package interface_adapter.user_info;

import use_case.user_info.UserInfoInputBoundary;

public class UserInfoController {
    private final UserInfoInputBoundary userInfoInputBoundary;

    public UserInfoController(UserInfoInputBoundary userInfoInputBoundary) {
        this.userInfoInputBoundary = userInfoInputBoundary;
    }

    public void addAllergy(String allergyName) {
        userInfoInputBoundary.addAllergy(allergyName);
    }

    public void deleteAllergy(String allergyName) {
        userInfoInputBoundary.deleteAllergy(allergyName);
    }

    public void return_to_main() {
        userInfoInputBoundary.return_to_main();
    }

    public String[] getAllergies() {
        return userInfoInputBoundary.getAllergies();
    }
}