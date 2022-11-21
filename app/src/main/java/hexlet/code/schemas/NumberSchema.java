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
        return positive && value > 0;
    }

    private boolean isRange(Integer value) {
        return range && (value >= minRange && value <= maxRange);

    }

    @Override
    public final NumberSchema required() {
        setRequired(true);
        return this;
    }

    @Override
    public final boolean isValidType(Object value) {
        return value instanceof Integer;
    }
}
