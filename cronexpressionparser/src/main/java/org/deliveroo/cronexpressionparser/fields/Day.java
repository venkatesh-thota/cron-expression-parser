package org.deliveroo.cronexpressionparser.fields;

public class Day extends FieldBase {
    public Day(String expression) {
        super(expression);
        this.min = 1;
        this.max = 31;
    }
}
