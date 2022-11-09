package hexlet.code.schemas;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;


public abstract class BaseSchema {
    private boolean required = false;
    private List<Predicate> predicateList = new LinkedList<>();



    protected abstract BaseSchema required();

    public final boolean isValid(Object value) {
        return predicateList.stream().allMatch(method -> method.test(value));
    }

    protected final boolean getRequired() {
        return required;
    }

    public final void setRequired(boolean value) {
        required = value;
    }

    protected final List<Predicate> getPredicateList() {
        return predicateList;
    }

    protected abstract boolean isRequired(Object value);


}
