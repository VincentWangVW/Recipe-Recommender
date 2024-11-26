package interface_adapter.custom_search;

import use_case.recommend_custom.CustomSearchOutputBoundary;
import interface_adapter.ViewManagerModel;

import java.util.HashMap;
import java.util.List;

public class CustomSearchPresenter implements CustomSearchOutputBoundary {
    private final CustomSearchViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    public CustomSearchPresenter(CustomSearchViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Updates the view model with the custom search results.
     * @param recipes A map containing recipe IDs and their details.
     */
    @Override
    public void presentCustomSearchResults(HashMap<Integer, List<String>> recipes) {
        CustomSearchState state = new CustomSearchState();
        state.setRecipes(recipes);
        state.setErrorMessage(null); // Clear any previous errors
        viewModel.setState(state);
        viewModel.firePropertyChanged(); // Notify the view
    }

    /**
     * Updates the view model with an error message.
     * @param errorMessage The error message to be displayed.
     */
    @Override
    public void presentCustomSearchError(String errorMessage) {
        CustomSearchState state = new CustomSearchState();
        state.setRecipes(null); // Clear any previous results
        state.setErrorMessage(errorMessage);
        viewModel.setState(state);
        viewModel.firePropertyChanged(); // Notify the view
    }

    /**
     * Handles returning to the main screen by updating the ViewManagerModel.
     */
    @Override
    public void returnToMain() {
        viewManagerModel.setState("MAIN_SCREEN");
        viewManagerModel.firePropertyChanged();
    }
}


