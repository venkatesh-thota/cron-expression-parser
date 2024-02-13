package org.deliveroo.cronexpressionparser.selectors;

import org.deliveroo.cronexpressionparser.fields.FieldBase;

import java.util.List;

public class Exact extends SelectorBase {

    public Exact(FieldBase field) {
        super(field);
    }

    @Override
    public List<Integer> generatePossibilities() {
        Integer value = Integer.valueOf(this.field.getExpression());
        if (value > this.field.getMaximum()) {
            throw new RuntimeException("The value for field is more than the maximum allowed");
        }

        if (value < this.field.getMinimum()) {
            throw new RuntimeException("The value for field is less than the minimum allowed");
        }

        return List.of(value);
    }
}
