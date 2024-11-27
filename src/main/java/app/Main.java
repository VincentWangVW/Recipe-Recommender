package app;

import data_access.InMemoryDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.generated_recipes.GeneratedController;
import interface_adapter.generated_recipes.GeneratedPresenter;
import interface_adapter.generated_recipes.GeneratedViewModel;
import interface_adapter.ingredients_manager.*;
import interface_adapter.main.MainController;
import interface_adapter.main.MainPresenter;
import interface_adapter.recommend_recipes.RecipesController;
import interface_adapter.recommend_recipes.RecipesPresenter;
import interface_adapter.recommend_recipes.RecipesViewModel;
import interface_adapter.season.SeasonController;
import interface_adapter.season.SeasonPresenter;
import interface_adapter.season.SeasonViewModel;
import interface_adapter.ingredients_manager.IngredientsViewModel;

import interface_adapter.user_info.UserInfoController;
import interface_adapter.user_info.UserInfoPresenter;
import interface_adapter.user_info.UserInfoViewModel;
import use_case.generated_manager.GeneratedInteractor;
import use_case.generated_manager.GeneratedOutputBoundary;
import use_case.mainwindow.MainInteractor;
import use_case.mainwindow.MainOutputBoundary;
import use_case.recommend_recipes.RecipesInteractor;
import use_case.recommend_recipes.RecipesOutputBoundary;
import use_case.recommend_season.SeasonInteractor;
import use_case.recommend_season.SeasonOutputBoundary;
import use_case.manage_ingredients.IngredientsInteractor;
import use_case.manage_ingredients.IngredientsIOutputBoundary;

import use_case.user_info.UserInfoInteractor;
import use_case.user_info.UserInfoOutputBoundary;
import view.*;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame application = new JFrame("Recipe Recommendation");
        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewModel);

        createViewsAndAddToPanel(viewModel, views);

        cardLayout.show(views, "MAIN_SCREEN");
        application.pack();
        application.setVisible(true);
    }

    private static void createViewsAndAddToPanel(ViewManagerModel viewModel, JPanel views) {
        MainView mainView = new MainView();
        InMemoryDAO inMemoryDAO = new InMemoryDAO();
        SeasonViewModel seasonViewModel = new SeasonViewModel();
        RecipesViewModel recipesViewModel = new RecipesViewModel();
        IngredientsViewModel ingredientsViewModel = new IngredientsViewModel();
        GeneratedViewModel generatedViewModel = new GeneratedViewModel();
        UserInfoViewModel userInfoViewModel = new UserInfoViewModel();

        MainOutputBoundary mainOutputBoundary = new MainPresenter(viewModel, seasonViewModel, recipesViewModel,
                ingredientsViewModel, userInfoViewModel);
        SeasonOutputBoundary seasonOutputBoundary = new SeasonPresenter(viewModel);
        RecipesOutputBoundary recipesOutputBoundary = new RecipesPresenter(viewModel);
        IngredientsIOutputBoundary ingredientsOutputBoundary = new IngredientsPresenter(viewModel);
        GeneratedOutputBoundary generatedOutputBoundary = new GeneratedPresenter(viewModel);
        UserInfoOutputBoundary userInfoOutputBoundary = new UserInfoPresenter(viewModel);

        SeasonInteractor seasonInteractor = new SeasonInteractor(seasonOutputBoundary, inMemoryDAO);
        SeasonController seasonController = new SeasonController(seasonInteractor);

        MainInteractor mainInteractor = new MainInteractor(mainOutputBoundary);
        MainController mainController = new MainController(mainInteractor);

        IngredientsInteractor ingredientsInteractor = new IngredientsInteractor(ingredientsOutputBoundary);
        IngredientsController ingredientsController = new IngredientsController(ingredientsInteractor);

        RecipesInteractor recipesInteractor = new RecipesInteractor(recipesOutputBoundary);
        RecipesController recipesController = new RecipesController(recipesInteractor);

        GeneratedInteractor generatedInteractor = new GeneratedInteractor(generatedOutputBoundary,
                ingredientsInteractor,
                seasonInteractor);
        GeneratedController generatedController = new GeneratedController(generatedInteractor);

        UserInfoInteractor userInfoInteractor = new UserInfoInteractor(userInfoOutputBoundary);
        UserInfoController userInfoController = new UserInfoController(userInfoInteractor);

        mainView.setMainController(mainController);
        mainView.setPreferredSize(new Dimension(400, 400));
        views.add(mainView, "MAIN_SCREEN");

        SeasonView seasonView = new SeasonView(seasonViewModel);
        seasonView.setSeasonController(seasonController);
        seasonView.setPreferredSize(new Dimension(400, 400));
        views.add(seasonView, "SEASON_SCREEN");

        RecipeView recipeView = new RecipeView(recipesViewModel);
        recipeView.setRecipesController(recipesController);
        recipeView.setPreferredSize(new Dimension(400, 400));
        views.add(recipeView, "RECIPES_SCREEN");

        IngredientsView ingredientsView = new IngredientsView(ingredientsViewModel);
        ingredientsView.setIngredientsController(ingredientsController);
        ingredientsView.setPreferredSize(new Dimension(400, 400));
        views.add(ingredientsView, "INGREDIENTS_SCREEN");

        GeneratedRecipesView generatedRecipesView = new GeneratedRecipesView(generatedViewModel);
        generatedRecipesView.setGeneratedController(generatedController);
        generatedRecipesView.setPreferredSize(new Dimension(400, 400));
        views.add(generatedRecipesView, "GENERATED_SCREEN");

        UserInfoView userInfoView = new UserInfoView(userInfoViewModel);
        userInfoView.setUserInfoController(userInfoController);
        userInfoView.setPreferredSize(new Dimension(400, 400));
        views.add(userInfoView, "USER_INFO_SCREEN");
    }
}