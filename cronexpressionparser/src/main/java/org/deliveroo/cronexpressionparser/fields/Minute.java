package org.deliveroo.cronexpressionparser.fields;

public class Minute extends FieldBase {
    public Minute(String expression) {
        super(expression);
        this.min = 0;
        this.max = 59;
    }
}
