package org.deliveroo.cronexpressionparser.fields;

import org.deliveroo.cronexpressionparser.selectors.SelectorBase;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public abstract class FieldBase {
    Integer min;
    Integer max;
    String expression;
    List possibilities;

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

    public List<Integer> parse()
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.possibilities =  SelectorBase.get(this).generatePossibilities();
        return this.possibilities;
    }
}
