package org.example.adapter.presenter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public final class AnnotationViewModel {
    public static final String STATE_PROPERTY = "annotationState";

    private String message = "";
    private boolean success;
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public String getMessage() { return message; }
    public boolean isSuccess() { return success; }

    public AnnotationState getState() {
        return new AnnotationState(message, success);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(STATE_PROPERTY, listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(STATE_PROPERTY, listener);
    }

    public void showSuccess(String value) {
        updateState(value, true);
    }

    public void showError(String value) {
        updateState(value, false);
    }

    private void updateState(String value, boolean newSuccess) {
        AnnotationState previousState = getState();
        message = value;
        success = newSuccess;
        propertyChangeSupport.firePropertyChange(
                STATE_PROPERTY, previousState, getState());
    }
}
