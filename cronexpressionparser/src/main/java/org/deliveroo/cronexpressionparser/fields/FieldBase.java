package org.deliveroo.cronexpressionparser.fields;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public abstract class FieldBase<T> {
    Integer min;
    Integer max;
    String expression;
    List<T> possibilities;

    public FieldBase(String expression) {
        this.expression = expression;
    }

    public Integer getMinimum() {
        return min;
    }

    public Integer getMaximum() {
        return max;
    }

    public String getExpression() {
        return expression;
    }

    public List<T> parse()
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.possibilities = (List<T>) org.deliveroo.cronexpressionparser.selectors.Base.get(this).generatePossibilities();
        return this.possibilities;
    }
}
