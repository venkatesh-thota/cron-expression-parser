package org.deliveroo.cronexpressionparser.selectors;

import org.deliveroo.cronexpressionparser.fields.FieldBase;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Step extends org.deliveroo.cronexpressionparser.selectors.Base {

    public Step(FieldBase field) {
        super(field);
    }

    @Override
    public List<Integer> generatePossibilities() {
        String[] steps = this.field.getExpression().split("/");

        if (steps.length != 2) {
            throw new RuntimeException(
                    "Step does not have valid expression : " + this.field.getExpression());
        }

        if (steps[0].equals("*")) {
            steps[0] = this.field.getMinimum().toString();
        }

        Integer[] stepfields;
        try {
            stepfields = new Integer[]{Integer.valueOf(steps[0]), Integer.valueOf(steps[1])};
        } catch (Exception e) {
            throw new RuntimeException("Step does not have valid expression : " + this.field.getExpression());
        }

        if (stepfields[1] > this.field.getMaximum()) {
            throw new RuntimeException("Step size is more than maximum value");
        }

        if (stepfields[0] > this.field.getMaximum()) {
            throw new RuntimeException("Step start is more than maximum value");
        }

        return IntStream.iterate(stepfields[0], n -> n + stepfields[1])
                .limit((this.field.getMaximum() - stepfields[0]) / stepfields[1] + 1).boxed().collect(Collectors.toList());
    }
}
