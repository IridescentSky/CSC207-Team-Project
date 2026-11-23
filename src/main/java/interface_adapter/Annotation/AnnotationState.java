package interface_adapter.Annotation;

import java.util.Objects;

public final class AnnotationState {
    private final String message;
    private final boolean success;

    public AnnotationState(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() { return message; }
    public boolean isSuccess() { return success; }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof AnnotationState)) return false;
        AnnotationState that = (AnnotationState) other;
        return success == that.success && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() { return Objects.hash(message, success); }
}