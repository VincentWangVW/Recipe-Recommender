package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The ViewManagerModel class manages the state of the view and allows for
 * communication between the view and its components. It tracks various attributes
 * such as the current screen state, the selected type, user information, and custom data.
 * This class supports property change listeners, allowing other components to react to state changes.
 */
public class ViewManagerModel {
    private String state;
    private String selectedType;
    private boolean userInfo;
    private String custom;
    private final PropertyChangeSupport support;

    /**
     * Constructor that initializes the ViewManagerModel, setting up property change support.
     */
    public ViewManagerModel() {
        this.support = new PropertyChangeSupport(this);
    }

    /**
     * Sets the state of the view and notifies listeners of the change.
     *
     * @param state the new state of the view
     */
    public void setState(String state) {
        this.state = state;
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Sets the selected type and notifies listeners of the change.
     *
     * @param selectedType the selected type of data
     */
    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;
        support.firePropertyChange("selectedType", null, this.selectedType);
    }

    /**
     * Sets whether user information is included and notifies listeners of the change.
     *
     * @param userInfo true if user information is included, false otherwise
     */
    public void setUserInfo(boolean userInfo) {
        this.userInfo = userInfo;
        support.firePropertyChange("userInfo", null, this.userInfo);
    }

    /**
     * Sets custom data and notifies listeners of the change.
     *
     * @param custom the custom data to set
     */
    public void setCustom(String custom) {
        this.custom = custom;
        support.firePropertyChange("custom", null, custom);
    }

    /**
     * Gets the selected type.
     *
     * @return the selected type
     */
    public String getSelectedType() {
        return selectedType;
    }

    /**
     * Checks if user information is included.
     *
     * @return true if user information is included, false otherwise
     */
    public boolean isUserInfo() {
        return userInfo;
    }

    /**
     * Gets the custom data.
     *
     * @return the custom data
     */
    public String getCustom() {
        return custom;
    }

    /**
     * Adds a property change listener that will be notified of state changes.
     *
     * @param pcl the property change listener to add
     */
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    /**
     * Removes a property change listener.
     *
     * @param pcl the property change listener to remove
     */
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    /**
     * Fires a property change event, notifying listeners of a state change.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }
}
