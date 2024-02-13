package org.deliveroo.cronexpressionparser.selectors;

import org.deliveroo.cronexpressionparser.fields.FieldBase;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Range extends SelectorBase {

    public Range(FieldBase field) {
        super(field);
    }

    @Override
    public List<Integer> generatePossibilities() {
        List<Integer> rangeLimits = Stream.of(this.field.getExpression().split("-"))
                .map(Integer::valueOf).collect(
                        Collectors.toList());

        if (rangeLimits.size() != 2) {
            throw new RuntimeException(
                    "Range does not have valid expression : " + this.field.getExpression());
        }

        if (rangeLimits.get(1) < rangeLimits.get(0)) {
            throw new RuntimeException(
                    "Range minimum/maximum are in wrong order. maximum should be : " + rangeLimits.get(0)
                            + " and minimum should be : " + rangeLimits.get(1));
        }

        if (rangeLimits.get(0) < this.field.getMinimum()) {
            throw new RuntimeException(
                    "Range minimum is not valid. Given : " + rangeLimits.get(0) + " Min allowed : "
                            + this.field.getMinimum());
        }

        if (rangeLimits.get(0) > this.field.getMaximum()) {
            throw new RuntimeException(
                    "Range minimum is not valid. Given : " + rangeLimits.get(0) + " Max allowed : "
                            + this.field.getMaximum());
        }

        if (rangeLimits.get(1) > this.field.getMaximum()) {
            throw new RuntimeException(
                    "Range maximum is not valid. Given : " + rangeLimits.get(1) + " Max allowed : "
                            + this.field.getMaximum());
        }

        return Arrays.stream(IntStream.range(rangeLimits.get(0), rangeLimits.get(1) + 1).toArray())
                .boxed().collect(
                        Collectors.toList());
    }
}
