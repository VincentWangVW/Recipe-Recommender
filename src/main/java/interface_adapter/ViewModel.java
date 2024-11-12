package interface_adapter;

import javax.swing.text.View;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewModel {

    public enum viewState {
        MAIN_SCREEN,
        INGREDIENTS_SCREEN,
        USER_INFO,
        SEASON_SCREEN,
        GENERATE_RECIPES,
        RECIPES_SCREEN,
        //TODO: add all the states

    }

    private viewState state = viewState.MAIN_SCREEN;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public void setState(viewState state) {
        viewState oldState = this.state;
        this.state = state;
        support.firePropertyChange("state", oldState, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
