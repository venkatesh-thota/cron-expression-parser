package org.deliveroo.cronexpressionparser.selectors;

import org.deliveroo.cronexpressionparser.fields.FieldBase;

import java.util.stream.Collectors;
import java.util.List;
import java.util.stream.Stream;

public class Values extends SelectorBase {

    public Values(FieldBase field) {
        super(field);
    }

    @Override
    public  List<Integer> generatePossibilities() {
        String[] lists = this.field.getExpression().split(",");

        if (lists.length != 2) {
            throw new RuntimeException(
                    "Values does not have valid expression : " + this.field.getExpression());
        }

        return Stream.of(lists)
                .flatMap(l -> {
                    try {
                        return SelectorBase.get(new FieldBase(l) {
                            @Override
                            public Integer getMinimum() {
                                return field.getMinimum();
                            }

                            @Override
                            public Integer getMaximum() {
                                return field.getMaximum();
                            }

                            @Override
                            public String getExpression() {
                                return l;
                            }
                        }).generatePossibilities().stream();
                    } catch (Exception e) {
                        throw new RuntimeException(e.getMessage());
                    }
                }).distinct().sorted().collect(Collectors.toList());
    }
}
