package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {
    public final NumberSchema positive() {
        this.addPredicateToPredicateList((value) -> isPositive((Integer) value));
        return this;
    }

    public final NumberSchema range(int minRange, int maxRange) {
        this.addPredicateToPredicateList((value) -> isRange((Integer) value, minRange, maxRange));
        return this;
    }

    private boolean isPositive(Integer value) {
        return value > 0;
    }

    private boolean isRange(Integer value, int minRange, int maxRange) {
        return value >= minRange && value <= maxRange;

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
