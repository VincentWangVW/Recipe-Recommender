package app;

//import javax.swing.JFrame;

///**
// * The Main class of our application.
// */
//public class Main {
//    /**
//     * The main method for starting the program.
//     */
//    public static void main(String[] args) {
//        final AppBuilder appBuilder = new AppBuilder();
//        final JFrame application = appBuilder.build();
//
//        application.pack();
//        application.setVisible(true);
//    }
//}

import interface_adapter.ViewModel;
import view.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.
        JFrame application = new JFrame("Recipe Recommendation");
        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // The data for the views, such as username and password. This
        // will be changed by a presenter object that is reporting the
        // results from the use case. This is an observable, and will
        // be observed by the layout manager.
        ViewModel viewModel = new ViewModel();

        /*
         The observer watching for changes in the userViewModel. It will
         react to changes in application state by changing which view
         is showing. This is an anonymous object because we don't need to
         refer to it later.
        */
        new ViewManager(views, cardLayout, viewModel);

        // The object that knows how to start a use case.
//        UserSignupController userSignupController = createUserSignupUseCase();

        // Build the GUI, plugging in the screens.
        createViewsAndAddToPanel(viewModel, views );

        // Show the first view.
        cardLayout.show(views, "MAIN_SCREEN");
        application.pack();
        application.setVisible(true);
    }

    private static void createViewsAndAddToPanel(ViewModel viewModel, JPanel views) {
        MainView mainView = new MainView(viewModel);
        views.add(mainView, ViewManager.MAIN_SCREEN);
        GenerateRecipeView generateRecipeView = new GenerateRecipeView(viewModel);
        views.add(generateRecipeView, ViewManager.GENERATE_RECIPES);
        IngredientsView ingredientsView = new IngredientsView(viewModel);
        views.add(ingredientsView, ViewManager.INGREDIENTS_SCREEN);
        SeasonView seasonView = new SeasonView(viewModel);
        views.add(seasonView, ViewManager.SEASON_SCREEN);
        UserInfoView userInfoView = new UserInfoView(viewModel);
        views.add(userInfoView, ViewManager.USER_INFO);


    }

//    private static UserSignupController createUserSignupUseCase() {
//        UserSignupDataAccessInterface user;
//        try {
//            user = new FileUserDataAccessObject("./users.csv");
//        } catch (IOException e) {
//            throw new RuntimeException("Could not create file.");
//        }
//        UserSignupOutputBoundary userSignupOutputBoundary = new UserSignupPresenter();
//        UserFactory userFactory = new CommonUserFactory();
//        UserSignupInputBoundary userSignupInteractor = new UserSignupInteractor(
//                user, userSignupOutputBoundary, userFactory);
//        return new UserSignupController(userSignupInteractor);
//    }

}