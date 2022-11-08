package hexlet.code.schemas;

public abstract class Schema {
    boolean isRequired = false;

    public void required() {
        isRequired = true;
    }

    public boolean isValid(Object value) {
        return false;
    }

}
