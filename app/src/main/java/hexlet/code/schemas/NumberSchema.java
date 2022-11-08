package hexlet.code.schemas;

public class NumberSchema extends Schema{
    private boolean isPositive = false;
    private boolean isRange = false;
    private int minRange;
    private int maxRange;

    public NumberSchema positive() {
        isPositive = true;
        return this;
    }

    public void range(int minRange, int maxRange) {
        isRange = true;
        this.minRange = minRange;
        this.maxRange = maxRange;
    }

    public boolean isValid(Integer value) {
        if (isRequired && value == null) {
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
