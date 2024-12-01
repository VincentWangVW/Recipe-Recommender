package app;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import data_access.InMemoryDAO;
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
import interface_adapter.datescreen.DateViewModel;
import interface_adapter.ingredients_manager.IngredientsViewModel;
import interface_adapter.user_info.UserInfoController;
import interface_adapter.user_info.UserInfoPresenter;
import interface_adapter.user_info.UserInfoViewModel;
import use_case.generated_manager.GeneratedInteractor;
import use_case.mainwindow.MainInteractor;
import use_case.mainwindow.MainOutputBoundary;
import use_case.recommend_holiday.HolidayInteractor;
import use_case.recommend_recipes.RecipesInteractor;
import use_case.recommend_season.SeasonInteractor;
import use_case.manage_ingredients.IngredientsInteractor;
import use_case.user_info.UserInfoInteractor;
import view.*;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);
    private final InMemoryDAO inMemoryDAO = new InMemoryDAO();
    private final UserPreferences userPreferences = new UserPreferences(0, false, false, new String[0]);
    private final Dimension preferredSize = new Dimension(400, 400);

    private MainView mainView;
    private DateView dateView;
    private RecipeView recipeView;
    private IngredientsView ingredientsView;
    private GeneratedRecipesView generatedRecipesView;
    private UserInfoView userInfoView;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addMainView() {
        mainView = new MainView();
        mainView.setPreferredSize(preferredSize);
        cardPanel.add(mainView, "MAIN_SCREEN");
        return this;
    }

    public AppBuilder addDateView() {
        DateViewModel dateViewModel = new DateViewModel();
        dateView = new DateView(dateViewModel);
        dateView.setPreferredSize(preferredSize);
        cardPanel.add(dateView, "DATE_SCREEN");
        return this;
    }

    public AppBuilder addRecipeView() {
        RecipesViewModel recipesViewModel = new RecipesViewModel();
        recipeView = new RecipeView(recipesViewModel);
        recipeView.setPreferredSize(preferredSize);
        cardPanel.add(recipeView, "RECIPES_SCREEN");
        return this;
    }

    public AppBuilder addIngredientsView() {
        IngredientsViewModel ingredientsViewModel = new IngredientsViewModel();
        ingredientsView = new IngredientsView(ingredientsViewModel);
        ingredientsView.setPreferredSize(preferredSize);
        cardPanel.add(ingredientsView, "INGREDIENTS_SCREEN");
        return this;
    }

    public AppBuilder addGeneratedRecipesView() {
        GeneratedViewModel generatedViewModel = new GeneratedViewModel();
        generatedRecipesView = new GeneratedRecipesView(generatedViewModel);
        generatedRecipesView.setPreferredSize(preferredSize);
        cardPanel.add(generatedRecipesView, "GENERATED_SCREEN");
        return this;
    }

    public AppBuilder addUserInfoView() {
        UserInfoViewModel userInfoViewModel = new UserInfoViewModel();
        userInfoView = new UserInfoView(userInfoViewModel);
        userInfoView.setPreferredSize(preferredSize);
        cardPanel.add(userInfoView, "USER_INFO_SCREEN");
        return this;
    }

    public AppBuilder addMainUseCase() {
        MainOutputBoundary mainOutputBoundary = new MainPresenter(viewManagerModel, new DateViewModel(), new RecipesViewModel(), new IngredientsViewModel(), new UserInfoViewModel());
        MainInteractor mainInteractor = new MainInteractor(mainOutputBoundary);
        MainController mainController = new MainController(mainInteractor);
        mainView.setMainController(mainController);
        return this;
    }

    public AppBuilder addDateUseCase() {
        DatePresenter datePresenter = new DatePresenter(viewManagerModel);
        SeasonInteractor seasonInteractor = new SeasonInteractor(datePresenter, inMemoryDAO);
        HolidayInteractor holidayInteractor = new HolidayInteractor(datePresenter, inMemoryDAO);
        DateController dateController = new DateController(seasonInteractor, holidayInteractor);
        dateView.setDateController(dateController);
        return this;
    }

    public AppBuilder addRecipeUseCase() {
        HolidayInteractor holidayInteractor = new HolidayInteractor(new DatePresenter(viewManagerModel), inMemoryDAO);
        RecipesPresenter recipesPresenter = new RecipesPresenter(viewManagerModel);
        RecipesInteractor recipesInteractor = new RecipesInteractor(recipesPresenter, holidayInteractor);
        RecipesController recipesController = new RecipesController(recipesInteractor);
        recipeView.setRecipesController(recipesController);
        return this;
    }

    public AppBuilder addIngredientsUseCase() {
        IngredientsPresenter ingredientsPresenter = new IngredientsPresenter(viewManagerModel);
        IngredientsInteractor ingredientsInteractor = new IngredientsInteractor(ingredientsPresenter);
        IngredientsController ingredientsController = new IngredientsController(ingredientsInteractor);
        ingredientsView.setIngredientsController(ingredientsController);
        return this;
    }

    public AppBuilder addGeneratedRecipesUseCase() {
        GeneratedPresenter generatedPresenter = new GeneratedPresenter(viewManagerModel);
        IngredientsInteractor ingredientsInteractor = new IngredientsInteractor(new IngredientsPresenter(viewManagerModel));
        SeasonInteractor seasonInteractor = new SeasonInteractor(new DatePresenter(viewManagerModel), inMemoryDAO);
        HolidayInteractor holidayInteractor = new HolidayInteractor(new DatePresenter(viewManagerModel), inMemoryDAO);
        GeneratedInteractor generatedInteractor = new GeneratedInteractor(generatedPresenter, ingredientsInteractor, seasonInteractor, holidayInteractor, userPreferences);
        GeneratedController generatedController = new GeneratedController(generatedInteractor);
        generatedRecipesView.setGeneratedController(generatedController);
        return this;
    }

    public AppBuilder addUserInfoUseCase() {
        UserInfoPresenter userInfoPresenter = new UserInfoPresenter(viewManagerModel);
        UserInfoInteractor userInfoInteractor = new UserInfoInteractor(userInfoPresenter, userPreferences);
        UserInfoController userInfoController = new UserInfoController(userInfoInteractor);
        userInfoView.setUserInfoController(userInfoController);
        return this;
    }

    public JFrame build() {
        JFrame application = new JFrame("Recipe Recommendation");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.add(cardPanel);
        viewManagerModel.setState("MAIN_SCREEN");
        viewManagerModel.firePropertyChanged();
        return application;
    }
}