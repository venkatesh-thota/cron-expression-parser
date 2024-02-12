package org.deliveroo.cronexpressionparser.fields;

public class Hour extends FieldBase {
    public Hour(String expression) {
        super(expression);
        this.min = 0;
        this.max = 23;
    }
}
