package hexlet.code.schemas;

public class StringSchema extends BaseSchema {
    private String stringToCompare = "";
    private int minimalLength = 0;

    public final StringSchema contains(String forComparison) {
        this.addPredicateToPredicateList((value) -> isContains((String) value));
        stringToCompare = forComparison;
        return this;
    }

    public final StringSchema minLength(int length) {
        this.addPredicateToPredicateList((value) ->  isMinimalLength((String) value));
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
        this.addPredicateToPredicateList((value) -> isRequired(value));
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

    @Override
    protected final boolean isValidType(Object value) {
        return value instanceof String && !value.toString().isEmpty();
    }
}
