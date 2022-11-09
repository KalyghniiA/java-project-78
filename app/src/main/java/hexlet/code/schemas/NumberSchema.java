package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {
    private boolean positive = false;
    private boolean range = false;
    private int minRange;
    private int maxRange;

    public final NumberSchema positive() {
        positive = true;
        this.getPredicateList().add((value) -> isPositive((Integer) value));
        return this;
    }

    public final NumberSchema range(int minimalRange, int maximumRange) {
        this.getPredicateList().add((value) -> isRange((Integer) value));
        range = true;
        this.minRange = minimalRange;
        this.maxRange = maximumRange;
        return this;
    }

    private boolean isPositive(Integer value) {
        if (value == null) {
            return positive;
        }
        if (value instanceof Integer) {
            return positive && value >= 0;
        }

        return false;
    }

    private boolean isRange(Integer value) {
        if (value == null) {
            return range && (0 >= minRange && 0 <= maxRange);
        }

        if (value instanceof Integer) {
            return range && (value >= minRange && value <= maxRange);
        }
        return false;
    }

    @Override
    public final NumberSchema required() {
        getPredicateList().add((value) -> isRequired(value));
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
}
