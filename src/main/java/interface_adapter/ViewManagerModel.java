package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The ViewManagerModel class manages the state and properties of the application's view.
 * It supports property change listeners to notify observers of state changes.
 */
public class ViewManagerModel {
    private String state;
    private String selectedType;
    private boolean userInfo;
    private String custom;
    private final PropertyChangeSupport support;

    /**
     * Constructs a new ViewManagerModel with an initialized PropertyChangeSupport.
     */
    public ViewManagerModel() {
        this.support = new PropertyChangeSupport(this);
    }

    /**
     * Sets the current state of the view and notifies listeners.
     *
     * @param state The new state of the view.
     */
    public void setState(String state) {
        this.state = state;
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Sets the selected type for view generation and notifies listeners.
     *
     * @param selectedType The selected type for generation.
     */
    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;
        support.firePropertyChange("selectedType", null, this.selectedType);
    }

    /**
     * Sets whether user info is available and notifies listeners.
     *
     * @param userInfo True if user info is available, false otherwise.
     */
    public void setUserInfo(boolean userInfo) {
        this.userInfo = userInfo;
        support.firePropertyChange("userInfo", null, this.userInfo);
    }

    /**
     * Sets the custom property for view customization and notifies listeners.
     *
     * @param custom The custom property value.
     */
    public void setCustom(String custom) {
        this.custom = custom;
        support.firePropertyChange("custom", null, custom);
    }

    public String getSelectedType() {
        return selectedType;
    }

    public boolean isUserInfo() {
        return userInfo;
    }

    public String getCustom() {
        return custom;
    }

    /**
     * Adds a PropertyChangeListener to the listener list.
     *
     * @param pcl The listener to add.
     */
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    /**
     * Removes a PropertyChangeListener from the listener list.
     *
     * @param pcl The listener to remove.
     */
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    /**
     * Fires a property change event for the state property to notify listeners.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }
}
