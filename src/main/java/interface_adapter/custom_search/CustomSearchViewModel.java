package interface_adapter.custom_search;

import interface_adapter.ViewModel;

public class CustomSearchViewModel extends ViewModel<CustomSearchState> {

    public static final String VIEW_NAME = "CUSTOM_SEARCH_SCREEN";

    public CustomSearchViewModel() {
        super(VIEW_NAME); // Pass the view name to the parent constructor
        setState(new CustomSearchState()); // Initialize the state
    }

    /**
     * Updates the state with a new `CustomSearchState` and notifies listeners.
     * @param newState The new state to be set.
     */
    @Override
    public void setState(CustomSearchState newState) {
        super.setState(newState); // Call the parent class to update the state
        firePropertyChanged();   // Notify listeners about the state change
    }
}

