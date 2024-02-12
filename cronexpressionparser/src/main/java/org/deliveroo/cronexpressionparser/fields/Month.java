package org.deliveroo.cronexpressionparser.fields;

public class Month extends FieldBase {
    public Month(String expression) {
        super(expression);
        this.min = 1;
        this.max = 12;
    }
}
