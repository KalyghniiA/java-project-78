package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {
    private boolean positive = false;
    private boolean range = false;
    private int minRange;
    private int maxRange;

    public final NumberSchema positive() {
        this.getPredicateList().add((value) -> {
            if (value instanceof Integer || (!getRequired() && value == null)) {
                return isPositive((Integer) value);
            }
            return false;
        });
        positive = true;
        return this;
    }

    public final NumberSchema range(int minimalRange, int maximumRange) {
        this.getPredicateList().add((value) -> {
            if (value instanceof Integer || (!getRequired() && value == null)) {
                return isRange((Integer) value);
            }
            return false;
        });
        range = true;
        this.minRange = minimalRange;
        this.maxRange = maximumRange;
        return this;
    }

    private boolean isPositive(Integer value) {
        if (value == null) {
            return positive;
        }
        return positive && value >= 0;
    }

    private boolean isRange(Integer value) {
        if (value == null) {
            return range && (0 >= minRange && 0 <= maxRange);
        }
        return range && (value >= minRange && value <= maxRange);

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
