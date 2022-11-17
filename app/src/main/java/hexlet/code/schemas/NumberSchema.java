package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {
    private boolean positive = false;
    private boolean range = false;
    private int minRange;
    private int maxRange;

    public final NumberSchema positive() {
        this.addPredicateToPredicateList((value) -> isPositive((Integer) value));
        positive = true;
        return this;
    }

    public final NumberSchema range(int minimalRange, int maximumRange) {
        this.addPredicateToPredicateList((value) -> isRange((Integer) value));
        range = true;
        this.minRange = minimalRange;
        this.maxRange = maximumRange;
        return this;
    }

    private boolean isPositive(Integer value) {
        if (value == null) {
            return true;
        }
        return positive && value > 0;
    }

    private boolean isRange(Integer value) {
        if (value == null) {
            return false;
        }
        return range && (value >= minRange && value <= maxRange);

    }

    @Override
    public final NumberSchema required() {
        this.addPredicateToPredicateList((value) -> isRequired(value));
        setRequired(true);
        return this;
    }

    @Override
    protected final boolean isRequired(Object value) {
        if (getRequired()) {
            return value != null && value instanceof Integer;
        }
        return true;
    }

    @Override
    protected final boolean isValidType(Object value) {
        return value instanceof Integer;
    }
}
