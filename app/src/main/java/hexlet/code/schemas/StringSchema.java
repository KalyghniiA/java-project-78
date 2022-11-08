package hexlet.code.schemas;

public class StringSchema extends Schema {
    private String stringToCompare = "";
    private int minimalLength = 0;

    public final StringSchema contains(String forComparison) {
        stringToCompare = forComparison;
        return this;
    }

    public final void minLength(int length) {
        minimalLength = length;
    }

    public final boolean isValid(String value) {
        if (getIsRequired()) {
            if (value == null || value.equals("")) {
                return false;
            }
            if (isContains(value) && isMinimalLength(value)) {
                return true;
            }
            return false;
        }
        return true;
    }

    private boolean isContains(String value) {
        return value.contains(stringToCompare);
    }

    private boolean isMinimalLength(String value) {
        return value.length() >= minimalLength;
    }
}
