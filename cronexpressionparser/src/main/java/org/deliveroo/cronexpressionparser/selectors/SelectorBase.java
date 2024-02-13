package org.deliveroo.cronexpressionparser.selectors;


import org.deliveroo.cronexpressionparser.fields.FieldBase;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.List;

public abstract class SelectorBase {
    protected static final String ANY = "*";
    protected static final String LIST = ",";
    protected static final String STEP = "/";
    protected static final String RANGE = "-";
    protected static final String EXACT = "e";
    private static final Map<String, Class> expressionSelectorMap = Map.of(
            ANY, Any.class,
            LIST, Values.class,
            STEP, Step.class,
            RANGE, Range.class,
            EXACT, Exact.class
    );
    protected FieldBase field;

    public SelectorBase(FieldBase field) {
        this.field = field;
    }

    public static SelectorBase get(FieldBase field)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String selector = null;
        if (field.getExpression().equals(ANY)) selector = ANY;
        if (field.getExpression().matches(".*,.*")) selector = LIST;
        if (field.getExpression().matches("[0-9]+-[0-9]+")) selector = RANGE;
        if (field.getExpression().matches(".*\\/.*")) selector = STEP;
        if (field.getExpression().matches("^[0-9]+$")) selector = EXACT;

        if (selector == null) throw new RuntimeException("Invalid field expression : " + field.getExpression());

        return (SelectorBase) expressionSelectorMap
                .get(selector)
                .getDeclaredConstructor(FieldBase.class).newInstance(field);
    }

    public abstract List<Integer> generatePossibilities();
}
