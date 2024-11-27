package use_case.user_info;

import java.util.List;
import java.util.HashSet;

public interface UserInfoOutputBoundary {
    void presentAllergies(HashSet<String> allergies);
    void return_to_main();
}
