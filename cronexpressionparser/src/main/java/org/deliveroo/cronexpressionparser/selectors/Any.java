package org.deliveroo.cronexpressionparser.selectors;

import org.deliveroo.cronexpressionparser.fields.FieldBase;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Any extends SelectorBase {

    public Any(FieldBase field) {
        super(field);
    }

    @Override
    public List<Integer> generatePossibilities() {
        return Arrays.stream(IntStream.range(this.field.getMinimum(), this.field.getMaximum() + 1).toArray()).boxed().collect(
                Collectors.toList());
    }
}
