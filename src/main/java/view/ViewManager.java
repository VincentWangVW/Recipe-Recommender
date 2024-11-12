package view;
import interface_adapter.ViewModel;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewManager implements PropertyChangeListener {
    //TODO: add all the states
    public static final String MAIN_SCREEN = "MAIN_SCREEN";
    public static final String INGREDIENTS_SCREEN = "INGREDIENTS_SCREEN";
    public static final String USER_INFO = "USER_INFO";
    public static final String SEASON_SCREEN = "SEASON_SCREEN";
    public static final String GENERATE_RECIPES = "GENERATE_RECIPES";
    public static final String RECIPES_SCREEN = "RECIPES_SCREEN";
    private final CardLayout cardLayout;
    private final JPanel views;

    public ViewManager(JPanel views, CardLayout cardLayout, ViewModel userViewModel) {
        this.views = views;
        this.cardLayout = cardLayout;
        userViewModel.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            ViewModel.viewState newValue = (ViewModel.viewState) evt.getNewValue();
            switch (newValue) {
                case MAIN_SCREEN -> cardLayout.show(views, MAIN_SCREEN);
                case INGREDIENTS_SCREEN -> cardLayout.show(views, INGREDIENTS_SCREEN);
                case USER_INFO-> cardLayout.show(views, USER_INFO);
                case SEASON_SCREEN -> cardLayout.show(views, SEASON_SCREEN);
                case GENERATE_RECIPES -> cardLayout.show(views, GENERATE_RECIPES);
                case RECIPES_SCREEN -> cardLayout.show(views, RECIPES_SCREEN);
                default -> throw new IllegalStateException("Unexpected value: " + evt.getNewValue());
            }
        }
    }
}
