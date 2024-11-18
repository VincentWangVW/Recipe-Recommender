package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewManagerModel {
    private String state;
    private String selectedType;
    private boolean userInfo;
    private final PropertyChangeSupport support;

    public ViewManagerModel() {
        this.support = new PropertyChangeSupport(this);
    }

    public void setState(String state) {
        this.state = state;
        support.firePropertyChange("state", null, this.state);
    }

    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;
        support.firePropertyChange("selectedType", null, this.selectedType);
    }

    public void setUserInfo(boolean userInfo) {
        this.userInfo = userInfo;
        support.firePropertyChange("userInfo", null, this.userInfo);
    }

    public String getSelectedType() {
        return selectedType;
    }

    public boolean isUserInfo() {
        return userInfo;
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }
}