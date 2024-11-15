// src/main/java/app/Main.java
package app;

import data_access.InMemoryDAO;
import interface_adapter.ViewManagerModel;
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

import use_case.mainwindow.MainInteractor;
import use_case.mainwindow.MainOutputBoundary;
import use_case.recommend_recipes.RecipesInteractor;
import use_case.recommend_recipes.RecipesOutputBoundary;
import use_case.recommend_season.SeasonInteractor;
import use_case.recommend_season.SeasonOutputBoundary;
import use_case.manage_ingredients.IngredientsInteractor;
import use_case.manage_ingredients.IngredientsIOutputBoundary;

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

        MainOutputBoundary mainOutputBoundary = new MainPresenter(viewModel, seasonViewModel, recipesViewModel, ingredientsViewModel);
        SeasonOutputBoundary seasonOutputBoundary = new SeasonPresenter(viewModel);
        RecipesOutputBoundary recipesOutputBoundary = new RecipesPresenter(viewModel);
        IngredientsIOutputBoundary ingredientsOutputBoundary = new IngredientsPresenter(viewModel);

        SeasonInteractor seasonInteractor = new SeasonInteractor(seasonOutputBoundary, inMemoryDAO);
        SeasonController seasonController = new SeasonController(seasonInteractor);

        MainInteractor mainInteractor = new MainInteractor(mainOutputBoundary);
        MainController mainController = new MainController(mainInteractor);

        RecipesInteractor recipesInteractor = new RecipesInteractor(recipesOutputBoundary);
        RecipesController recipesController = new RecipesController(recipesInteractor);

        IngredientsInteractor ingredientsInteractor = new IngredientsInteractor(ingredientsOutputBoundary);
        IngredientsController ingredientsController = new IngredientsController(ingredientsInteractor);

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
    }
}