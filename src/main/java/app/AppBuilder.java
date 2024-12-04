package app;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.InMemoryDateInfoDao;
import data_access.SpoonacularDao;
import entity.UserPreferences;
import interface_adapter.ViewManagerModel;
import interface_adapter.datescreen.DateController;
import interface_adapter.datescreen.DatePresenter;
import interface_adapter.datescreen.DateViewModel;
import interface_adapter.generated_recipes.GeneratedController;
import interface_adapter.generated_recipes.GeneratedPresenter;
import interface_adapter.generated_recipes.GeneratedViewModel;
import interface_adapter.ingredients_manager.IngredientsController;
import interface_adapter.ingredients_manager.IngredientsPresenter;
import interface_adapter.ingredients_manager.IngredientsViewModel;
import interface_adapter.main.MainController;
import interface_adapter.main.MainPresenter;
import interface_adapter.recommend_recipes.RecipesController;
import interface_adapter.recommend_recipes.RecipesPresenter;
import interface_adapter.recommend_recipes.RecipesViewModel;
import interface_adapter.user_info.UserInfoController;
import interface_adapter.user_info.UserInfoPresenter;
import interface_adapter.user_info.UserInfoViewModel;
import use_case.generated_manager.GeneratedInteractor;
import use_case.mainwindow.MainInteractor;
import use_case.mainwindow.MainOutputBoundary;
import use_case.manage_ingredients.IngredientsInteractor;
import use_case.recommend_custom.CustomSearchInteractor;
import use_case.recommend_holiday.HolidayInteractor;
import use_case.recommend_recipes.RecipesInteractor;
import use_case.recommend_season.SeasonInteractor;
import use_case.user_info.UserInfoInteractor;
import view.DateView;
import view.GeneratedRecipesView;
import view.IngredientsView;
import view.MainView;
import view.RecipeView;
import view.UserInfoView;
import view.ViewManager;

/**
 * Builder class to build the application.
 */
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);
    private final InMemoryDateInfoDao inMemoryDateInfoDao = new InMemoryDateInfoDao();
    private final SpoonacularDao spoonacularDao = new SpoonacularDao();
    private final UserPreferences userPreferences = new UserPreferences(0, false, false, new String[0]);
    private final Dimension preferredSize = new Dimension(400, 400);

    private MainView mainView;
    private DateView dateView;
    private RecipeView recipeView;
    private IngredientsView ingredientsView;
    private GeneratedRecipesView generatedRecipesView;
    private UserInfoView userInfoView;
    private IngredientsInteractor ingredientsInteractor;

    /**
     * Constructs an {@code AppBuilder} instance.
     */
    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Add the main view to the card panel.
     * @return AppBuilder
     */
    public AppBuilder addMainView() {
        mainView = new MainView();
        mainView.setPreferredSize(preferredSize);
        cardPanel.add(mainView, "MAIN_SCREEN");
        return this;
    }

    /**
     * Add the date view to the card panel.
     * @return AppBuilder
     */
    public AppBuilder addDateView() {
        final DateViewModel dateViewModel = new DateViewModel();
        dateView = new DateView(dateViewModel);
        dateView.setPreferredSize(preferredSize);
        cardPanel.add(dateView, "DATE_SCREEN");
        return this;
    }

    /**
     * Add the recipe view to the card panel.
     * @return AppBuilder
     */
    public AppBuilder addRecipeView() {
        final RecipesViewModel recipesViewModel = new RecipesViewModel();
        recipeView = new RecipeView(recipesViewModel);
        recipeView.setPreferredSize(preferredSize);
        cardPanel.add(recipeView, "RECIPES_SCREEN");
        return this;
    }

    /**
     * Add the ingredients view to the card panel.
     * @return AppBuilder
     */
    public AppBuilder addIngredientsView() {
        final IngredientsViewModel ingredientsViewModel = new IngredientsViewModel();
        ingredientsView = new IngredientsView(ingredientsViewModel);
        ingredientsView.setPreferredSize(preferredSize);
        cardPanel.add(ingredientsView, "INGREDIENTS_SCREEN");
        return this;
    }

    /**
     * Add the generated recipes view to the card panel.
     * @return AppBuilder
     */
    public AppBuilder addGeneratedRecipesView() {
        final GeneratedViewModel generatedViewModel = new GeneratedViewModel();
        generatedRecipesView = new GeneratedRecipesView(generatedViewModel);
        generatedRecipesView.setPreferredSize(preferredSize);
        cardPanel.add(generatedRecipesView, "GENERATED_SCREEN");
        return this;
    }

    /**
     * Add the user info view to the card panel.
     * @return AppBuilder
     */
    public AppBuilder addUserInfoView() {
        final UserInfoViewModel userInfoViewModel = new UserInfoViewModel();
        userInfoView = new UserInfoView(userInfoViewModel);
        userInfoView.setPreferredSize(preferredSize);
        cardPanel.add(userInfoView, "USER_INFO_SCREEN");
        return this;
    }

    /**
     * Add the main use case to the application.
     * @return AppBuilder
     */
    public AppBuilder addMainUseCase() {
        final MainOutputBoundary mainOutputBoundary = new MainPresenter(viewManagerModel, new DateViewModel(),
                new RecipesViewModel(), new IngredientsViewModel(), new UserInfoViewModel());
        final MainInteractor mainInteractor = new MainInteractor(mainOutputBoundary);
        final MainController mainController = new MainController(mainInteractor);
        mainView.setMainController(mainController);
        return this;
    }

    /**
     * Add the date use case to the application.
     * @return AppBuilder
     */
    public AppBuilder addDateUseCase() {
        final DatePresenter datePresenter = new DatePresenter(viewManagerModel);
        final SeasonInteractor seasonInteractor = new SeasonInteractor(datePresenter, inMemoryDateInfoDao,
                spoonacularDao);
        final HolidayInteractor holidayInteractor = new HolidayInteractor(datePresenter, inMemoryDateInfoDao,
                spoonacularDao);
        final DateController dateController = new DateController(seasonInteractor, holidayInteractor);
        dateView.setDateController(dateController);
        return this;
    }

    /**
     * Add the recipe use case to the application.
     * @return AppBuilder
     */
    public AppBuilder addRecipeUseCase() {
        final HolidayInteractor holidayInteractor = new HolidayInteractor(new DatePresenter(viewManagerModel),
                inMemoryDateInfoDao, spoonacularDao);
        final RecipesPresenter recipesPresenter = new RecipesPresenter(viewManagerModel);
        final RecipesInteractor recipesInteractor = new RecipesInteractor(recipesPresenter, holidayInteractor);
        final RecipesController recipesController = new RecipesController(recipesInteractor);
        recipeView.setRecipesController(recipesController);
        return this;
    }

    /**
     * Add the ingredients use case to the application.
     * @return AppBuilder
     */
    public AppBuilder addIngredientsUseCase() {
        final IngredientsPresenter ingredientsPresenter = new IngredientsPresenter(viewManagerModel);
        ingredientsInteractor = new IngredientsInteractor(ingredientsPresenter);
        final IngredientsController ingredientsController = new IngredientsController(ingredientsInteractor);
        ingredientsView.setIngredientsController(ingredientsController);
        return this;
    }

    /**
     * Add the generated recipes use case to the application.
     * @return AppBuilder
     */
    public AppBuilder addGeneratedRecipesUseCase() {
        final GeneratedPresenter generatedPresenter = new GeneratedPresenter(viewManagerModel);
        final SeasonInteractor seasonInteractor = new SeasonInteractor(new DatePresenter(viewManagerModel),
                inMemoryDateInfoDao, spoonacularDao);
        final HolidayInteractor holidayInteractor = new HolidayInteractor(new DatePresenter(viewManagerModel),
                inMemoryDateInfoDao, spoonacularDao);
        final CustomSearchInteractor customSearchInteractor = new CustomSearchInteractor();
        final GeneratedInteractor generatedInteractor = new GeneratedInteractor(generatedPresenter,
                ingredientsInteractor,
                seasonInteractor, holidayInteractor, customSearchInteractor, userPreferences);
        final GeneratedController generatedController = new GeneratedController(generatedInteractor);
        generatedRecipesView.setGeneratedController(generatedController);
        return this;
    }

    /**
     * Add the user info use case to the application.
     * @return AppBuilder
     */
    public AppBuilder addUserInfoUseCase() {
        final UserInfoPresenter userInfoPresenter = new UserInfoPresenter(viewManagerModel);
        final UserInfoInteractor userInfoInteractor = new UserInfoInteractor(userInfoPresenter, userPreferences);
        final UserInfoController userInfoController = new UserInfoController(userInfoInteractor);
        userInfoView.setUserInfoController(userInfoController);
        return this;
    }

    /**
     * Build the application.
     * @return JFrame
     */
    public JFrame build() {
        final JFrame application = new JFrame("Recipe Recommendation");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.add(cardPanel);
        viewManagerModel.setState("MAIN_SCREEN");
        viewManagerModel.firePropertyChanged();
        return application;
    }
}
