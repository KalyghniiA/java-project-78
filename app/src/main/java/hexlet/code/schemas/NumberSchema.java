package hexlet.code.schemas;

public class NumberSchema extends Schema {
    private boolean isPositive = false;
    private boolean isRange = false;
    private int minRange;
    private int maxRange;

    public final NumberSchema positive() {
        isPositive = true;
        return this;
    }

    public final void range(int minimalRange, int maximumRange) {
        isRange = true;
        this.minRange = minimalRange;
        this.maxRange = maximumRange;
    }

    public final boolean isValid(Integer value) {
        if (getIsRequired() && value == null) {
            return false;
        }

        if (isPositive && value < 0) {
            return false;
        }

        if (isRange && (value < minRange || value > maxRange)) {
            return false;
        }

        return true;
    }


}
