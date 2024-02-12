package org.deliveroo.cronexpressionparser.selectors;

import org.deliveroo.cronexpressionparser.fields.FieldBase;

import java.util.stream.Collectors;

public class Values extends org.deliveroo.cronexpressionparser.selectors.Base {

    public Values(FieldBase field) {
        super(field);
    }

    @Override
    public java.util.List<Integer> generatePossibilities() {
        String[] lists = this.field.getExpression().split(",");

        if (lists.length != 2) {
            throw new RuntimeException(
                    "Values does not have valid expression : " + this.field.getExpression());
        }

        return java.util.List.of(lists).stream()
                .flatMap(l -> {
                    try {
                        return org.deliveroo.cronexpressionparser.selectors.Base.get(new FieldBase(l) {
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
