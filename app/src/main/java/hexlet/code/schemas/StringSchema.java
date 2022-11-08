package hexlet.code.schemas;

public class StringSchema extends Schema {
    private String stringToCompare = "";
    private int minimalLength = 0;

    public boolean isValid(Object value) {
        return false;
    }

    public StringSchema contains(String forComparison) {
        stringToCompare = forComparison;
        return this;
    }

    public void minLength(int length) {
        minimalLength = length;
    }

    public boolean isValid(String value) {
        if (isRequired) {
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
