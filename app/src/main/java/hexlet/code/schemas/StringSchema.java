package hexlet.code.schemas;

public class StringSchema extends BaseSchema {
    private String stringToCompare = "";
    private int minimalLength = 0;

    public final StringSchema contains(String forComparison) {
        this.getPredicateList().add((value) -> isContains((String) value));
        stringToCompare = forComparison;
        return this;
    }

    public final StringSchema minLength(int length) {
        this.getPredicateList().add((value) -> isMinimalLength((String) value));
        minimalLength = length;
        return this;
    }


    private boolean isContains(String value) {
        if (value == null) {
            return false;
        }
        return value.contains(stringToCompare);
    }

    private boolean isMinimalLength(String value) {
        if (value == null) {
            return false;
        }
        return value.length() >= minimalLength;
    }

    @Override
    public final StringSchema required() {
        getPredicateList().add((value) -> isRequired(value));
        setRequired(true);
        return this;
    }

    @Override
    protected final boolean isRequired(Object value) {
        if (getRequired()) {
            return value != null && !value.toString().isEmpty();
        }
        return true;
    }
}
