package use_case.user_info;

/**
 * The UserInfoOutputBoundary interface defines the contract for the output boundary
 * used by the UserInfoInteractor to navigate between views and return to the main screen.
 */
public interface UserInfoOutputBoundary {

    /**
     * Instructs the system to return to the main screen.
     */
    void return_to_main();
}
