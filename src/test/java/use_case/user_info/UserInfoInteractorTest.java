package use_case.user_info;

import entity.IUserPreferences;
import entity.UserPreferences;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class UserInfoInteractorTest {

    private boolean mainReturned = false;

    @Test
    void addAllergyTest() {
        // Mock the UserInfo output boundary
        UserInfoOutputBoundary successPresenter = new UserInfoOutputBoundary() {

            @Override
            public void return_to_main() {
                // No action needed for this test
            }
        };

        // Mock the UserPreferences
        UserPreferences userPreferences = new UserPreferences(0, false, false,
                new String[0]);

        // Create the interactor
        UserInfoInteractor interactor = new UserInfoInteractor(successPresenter, userPreferences);

        // Add allergies
        interactor.addAllergy("Apple");
        interactor.addAllergy("Fish");
        interactor.addAllergy("Peanuts");
        interactor.addAllergy("Watermelon");

        // Verify the allergies
        String[] allergies = interactor.getAllergies();
        Arrays.sort(allergies); // Sort to match assertion order
        assertArrayEquals(new String[]{"Apple", "Fish", "Peanuts", "Watermelon"}, allergies);
    }

    @Test
    void deleteAllergyTest() {
        // Mock the UserInfo output boundary
        UserInfoOutputBoundary successPresenter = new UserInfoOutputBoundary() {

            @Override
            public void return_to_main() {
                // No action needed for this test
            }
        };

        // Mock the UserPreferences
        UserPreferences userPreferences = new UserPreferences(0, false, false,
                new String[0]);

        // Create the interactor
        UserInfoInteractor interactor = new UserInfoInteractor(successPresenter, userPreferences);

        // Add allergies
        interactor.addAllergy("Peanuts");
        interactor.addAllergy("Watermelon");

        // Delete an allergy
        interactor.deleteAllergy("Peanuts");

        // Verify the remaining allergies
        String[] allergies = interactor.getAllergies();
        assertArrayEquals(new String[]{"Watermelon"}, allergies);
    }

    @Test
    void getAllergiesEmptyTest() {
        // Mock the UserInfo output boundary
        UserInfoOutputBoundary successPresenter = new UserInfoOutputBoundary() {

            @Override
            public void return_to_main() {
                // No action needed for this test
            }
        };

        // Mock the UserPreferences
        UserPreferences userPreferences = new UserPreferences(0, false, false,
                new String[0]);

        // Create the interactor
        UserInfoInteractor interactor = new UserInfoInteractor(successPresenter, userPreferences);

        // Verify the allergies list is empty in the initialization
        String[] allergies = interactor.getAllergies();
        assertArrayEquals(new String[0], allergies);
    }

    @Test
    void changeShopAmountTest() {
        // Mock the UserInfo output boundary
        UserInfoOutputBoundary successPresenter = new UserInfoOutputBoundary() {

            @Override
            public void return_to_main() {
                // No action needed for this test
            }
        };

        // Mock the UserPreferences
        UserPreferences userPreferences = new UserPreferences(0, false, false,
                new String[0]);

        // Create the interactor
        UserInfoInteractor interactor = new UserInfoInteractor(successPresenter, userPreferences);

        // Change the shop amount
        int result = interactor.changeShopAmount(15);
        assertEquals(15, result);

        result = interactor.changeShopAmount(30);
        assertEquals(30, result);
    }

    @Test
    void returnToMainTest() {
        // Mock the UserInfo output boundary
        UserInfoOutputBoundary successPresenter = new UserInfoOutputBoundary() {

            @Override
            public void return_to_main() {
                mainReturned = true;
            }
        };

        // Mock the UserPreferences
        UserPreferences userPreferences = new UserPreferences(0, false, false,
                new String[0]);

        // Create the interactor
        UserInfoInteractor interactor = new UserInfoInteractor(successPresenter, userPreferences);

        // Call return_to_main
        interactor.return_to_main();

        // Verify return_to_main was executed
        assertTrue(mainReturned);
    }
}
