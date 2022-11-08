package hexlet.code.schemas;

public abstract class Schema {
    private boolean isRequired = false;

    public final void required() {
        isRequired = true;
    }

    public static boolean isValid(Object value) {
        return false;
    }

    public final boolean getIsRequired() {
        return isRequired;
    }

}
