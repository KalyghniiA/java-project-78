package hexlet.code.schemas;


import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema {
    private boolean size = false;
    private Map<String, BaseSchema> schemasShape = new HashMap<>();
    private Integer maxSize;

    public final MapSchema sizeof(Integer maximumSize) {
        this.addPredicateToPredicateList((value) -> {
            if (value instanceof Map<?, ?>) {
                return isSize((Map<String, Object>) value);
            }
            return false;
        });
        this.size = true;
        this.maxSize = maximumSize;
        return this;
    }

    public final MapSchema shape(Map<String, BaseSchema> schemas) {
        this.addPredicateToPredicateList((value) -> {
            if (value instanceof Map<?, ?>) {
                return isShape((Map<String, Object>) value);
            }
            return false;
        });
        schemasShape.putAll(schemas);
        return this;
    }

    private boolean isShape(Map<String, Object> value) {
        for (Map.Entry elem: value.entrySet()) {
            if (!isShapeToSchema(schemasShape.get(elem.getKey()), elem.getValue())) {
                return false;
            }
        }

        return true;
    }

    private boolean isShapeToSchema(BaseSchema schema, Object value) {
        return schema.isValid(value);
    }

    private boolean isSize(Map<String, Object> value) {
        return value.size() == maxSize;
    }

    @Override
    public final MapSchema required() {
        this.addPredicateToPredicateList((value) -> isRequired(value));
        setRequired(true);
        return this;
    }

    @Override
    protected final boolean isRequired(Object value) {
        if (getRequired()) {
            return value != null && value instanceof Map<?, ?>;
        }

        return true;
    }
}
