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

import data_access.InMemoryDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import interface_adapter.main.MainController;
import interface_adapter.main.MainPresenter;
import interface_adapter.season.SeasonController;
import interface_adapter.season.SeasonPresenter;
import interface_adapter.season.SeasonViewModel;
import use_case.mainwindow.MainInteractor;
import use_case.mainwindow.MainOutputBoundary;
import use_case.season.SeasonInteractor;
import use_case.season.SeasonOutputBoundary;
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
        ViewManagerModel viewModel = new ViewManagerModel();

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

    private static void createViewsAndAddToPanel(ViewManagerModel viewModel, JPanel views) {
        MainView mainView = new MainView();
        SeasonViewModel seasonViewModel=new SeasonViewModel();
        MainOutputBoundary mainOutputBoundary=new MainPresenter(viewModel,seasonViewModel);
        SeasonOutputBoundary seasonOutputBoundary=new SeasonPresenter(viewModel);
        SeasonInteractor seasonInteractor=new SeasonInteractor(seasonOutputBoundary);
        SeasonController seasonController=new SeasonController(seasonInteractor);
        MainInteractor mainInteractor=new MainInteractor(mainOutputBoundary);
        MainController mainController=new MainController(mainInteractor);
        mainView.setMainController(mainController);
        mainView.setPreferredSize(new Dimension(400,400));
        views.add(mainView, "MAIN_SCREEN");

        SeasonView seasonView = new SeasonView(seasonViewModel);
        seasonView.setSeasonController(seasonController);
        views.add(seasonView,"SEASON_SCREEN");



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