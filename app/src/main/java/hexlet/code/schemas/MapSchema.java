package hexlet.code.schemas;


import java.util.Map;

public class MapSchema extends BaseSchema {
    private boolean size = false;
    private Integer maxSize;

    public final MapSchema sizeof(Integer maximumSize) {
        this.getPredicateList().add((value) -> isSize((Map<String, String>) value));
        this.size = true;
        this.maxSize = maximumSize;
        return this;
    }

    private boolean isSize(Map<String, String> value) {
        return value.size() == maxSize;
    }

    @Override
    protected final boolean isRequired(Object value) {
        if (getRequired()) {
            return value != null && value instanceof Map<?, ?>;
        }

        return true;
    }
}
