package hexlet.code.schemas;


import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema {
    private boolean size = false;
    private Map<String, BaseSchema> schemasShape = new HashMap<>();
    private Integer maxSize;

    public final MapSchema sizeof(Integer maximumSize) {
        this.addPredicateToPredicateList((value) -> isSize((Map<String, Object>) value));
        this.size = true;
        this.maxSize = maximumSize;
        return this;
    }

    public final MapSchema shape(Map<String, BaseSchema> schemas) {
        this.addPredicateToPredicateList((value) -> isShape((Map<String, Object>) value));
        schemasShape.putAll(schemas);
        return this;
    }

    private boolean isShape(Map<String, Object> value) {
        for (Map.Entry elem: value.entrySet()) {
            if (!schemasShape.get(elem.getKey()).isValid(elem.getValue())) {
                return false;
            }
        }

        return true;
    }

    private boolean isSize(Map<String, Object> value) {
        return value.size() == maxSize;
    }

    @Override
    public final MapSchema required() {
        setRequired(true);
        return this;
    }

    @Override
    public final boolean isValidType(Object value) {
        return value instanceof Map<?, ?>;
    }
}
