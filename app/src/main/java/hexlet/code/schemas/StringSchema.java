package hexlet.code.schemas;

public class StringSchema extends BaseSchema {
    private String stringToCompare = "";

    public final StringSchema contains(String forComparison) {
        this.addPredicateToPredicateList((value) -> isContains((String) value));
        stringToCompare = forComparison;
        return this;
    }

    public final StringSchema minLength(int length) {
        this.addPredicateToPredicateList((value) ->  isMinimalLength((String) value, length));
        return this;
    }


    private boolean isContains(String value) {
        return value.contains(stringToCompare);
    }

    private boolean isMinimalLength(String value, int minimalLength) {
        return value.length() >= minimalLength;
    }

    @Override
    public final StringSchema required() {
        setRequired(true);
        return this;
    }

    @Override
    protected final boolean isValidType(Object value) {
        return value instanceof String && !value.toString().isEmpty();
    }
}
