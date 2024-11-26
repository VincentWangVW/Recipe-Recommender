package app;

import data_access.InMemoryDAO;
import data_access.SpoonacularDAO;
import entity.UserPreferences;
import interface_adapter.ViewManagerModel;
import interface_adapter.datescreen.DateController;
import interface_adapter.datescreen.DatePresenter;
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
import interface_adapter.custom_search.CustomSearchController;
import interface_adapter.custom_search.CustomSearchPresenter;
import interface_adapter.custom_search.CustomSearchViewModel;

import interface_adapter.datescreen.DateController;
import interface_adapter.datescreen.DateViewModel;
import interface_adapter.ingredients_manager.IngredientsViewModel;

import use_case.generated_manager.GeneratedInteractor;
import use_case.generated_manager.GeneratedOutputBoundary;
import use_case.mainwindow.MainInteractor;
import use_case.mainwindow.MainOutputBoundary;
import use_case.recommend_holiday.HolidayInteractor;
import use_case.recommend_holiday.HolidayOutputBoundary;
import use_case.recommend_recipes.RecipesInteractor;
import use_case.recommend_recipes.RecipesOutputBoundary;
import use_case.recommend_season.SeasonInteractor;
import use_case.recommend_season.SeasonOutputBoundary;
import use_case.manage_ingredients.IngredientsInteractor;
import use_case.manage_ingredients.IngredientsIOutputBoundary;
import use_case.recommend_custom.CustomSearchInteractor;
import use_case.recommend_custom.CustomSearchOutputBoundary;

import view.*;
import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

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
        DateViewModel dateViewModel = new DateViewModel();
        RecipesViewModel recipesViewModel = new RecipesViewModel();
        IngredientsViewModel ingredientsViewModel = new IngredientsViewModel();
        GeneratedViewModel generatedViewModel = new GeneratedViewModel();
        CustomSearchViewModel customSearchViewModel = new CustomSearchViewModel();

        MainOutputBoundary mainOutputBoundary = new MainPresenter(viewModel, dateViewModel, recipesViewModel,
                ingredientsViewModel);
        SeasonOutputBoundary seasonOutputBoundary = new DatePresenter(viewModel);
        HolidayOutputBoundary holidayOutputBoundary = new DatePresenter(viewModel);
        RecipesOutputBoundary recipesOutputBoundary = new RecipesPresenter(viewModel);
        IngredientsIOutputBoundary ingredientsOutputBoundary = new IngredientsPresenter(viewModel);
        GeneratedOutputBoundary generatedOutputBoundary = new GeneratedPresenter(viewModel);
        CustomSearchOutputBoundary customSearchOutputBoundary = new CustomSearchPresenter(customSearchViewModel, viewModel);

        SeasonInteractor seasonInteractor = new SeasonInteractor(seasonOutputBoundary, inMemoryDAO);
        HolidayInteractor holidayInteractor = new HolidayInteractor(holidayOutputBoundary, inMemoryDAO);
        DateController dateController = new DateController(seasonInteractor, holidayInteractor);

        MainInteractor mainInteractor = new MainInteractor(mainOutputBoundary);
        MainController mainController = new MainController(mainInteractor);

        IngredientsInteractor ingredientsInteractor = new IngredientsInteractor(ingredientsOutputBoundary);
        IngredientsController ingredientsController = new IngredientsController(ingredientsInteractor);

        RecipesInteractor recipesInteractor = new RecipesInteractor(recipesOutputBoundary);
        RecipesController recipesController = new RecipesController(recipesInteractor);

        GeneratedInteractor generatedInteractor = new GeneratedInteractor(generatedOutputBoundary,
                ingredientsInteractor,
                seasonInteractor, holidayInteractor);
        GeneratedController generatedController = new GeneratedController(generatedInteractor);

        CustomSearchInteractor customSearchInteractor = new CustomSearchInteractor(
                customSearchOutputBoundary,
                new SpoonacularDAO(),
                ingredientsInteractor,
                new UserPreferences(new HashSet<>(), new HashSet<>()) // Pass empty sets for allergies and dietary restrictions
        );
        CustomSearchController customSearchController = new CustomSearchController(customSearchInteractor);

        mainView.setMainController(mainController);
        mainView.setPreferredSize(new Dimension(400, 400));
        views.add(mainView, "MAIN_SCREEN");

        DateView dateView = new DateView(dateViewModel);
        dateView.setDateController(dateController);
        dateView.setPreferredSize(new Dimension(400, 400));
        views.add(dateView, "DATE_SCREEN");

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

        CustomSearchView customSearchView = new CustomSearchView(customSearchViewModel);
        customSearchView.setCustomSearchController(customSearchController);
        customSearchView.setPreferredSize(new Dimension(400, 400));
        views.add(customSearchView, "CUSTOM_SEARCH_SCREEN");
    }
}
