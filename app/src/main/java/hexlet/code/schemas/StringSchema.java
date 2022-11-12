package hexlet.code.schemas;

public class StringSchema extends BaseSchema {
    private String stringToCompare = "";
    private int minimalLength = 0;

    public final StringSchema contains(String forComparison) {
        this.additionPredicateToPredicateList((value) -> {
            if (value instanceof String) {
                return isContains((String) value);
            }
            return false;
        });
        stringToCompare = forComparison;
        return this;
    }

    public final StringSchema minLength(int length) {
        this.additionPredicateToPredicateList((value) -> {
            if (value instanceof String) {
                return isMinimalLength((String) value);
            }
            return false;
        });
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
        this.additionPredicateToPredicateList((value) -> isRequired(value));
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
