package hexlet.code.schemas;


import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema {
    private boolean size = false;
    private Map<String, BaseSchema> schemasShape = new HashMap<>();
    private Integer maxSize;

    public final MapSchema sizeof(Integer maximumSize) {
        this.getPredicateList().add((value) -> isSize((Map<String, Object>) value));
        this.size = true;
        this.maxSize = maximumSize;
        return this;
    }

    public final MapSchema shape(Map<String, BaseSchema> schemas) {
        this.getPredicateList().add((value) -> isShape((Map<String, Object>) value));
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
    public final BaseSchema required() {
        getPredicateList().add((value) -> isRequired(value));
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
