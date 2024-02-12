package org.deliveroo.cronexpressionparser.selectors;

import org.deliveroo.cronexpressionparser.fields.FieldBase;
import org.deliveroo.cronexpressionparser.fields.Minute;
import org.deliveroo.cronexpressionparser.fields.Weekday;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValuesTest {

    @Test
    public void testListPossibilities()
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        FieldBase m = new Minute("1,5");
        assertEquals(m.parse(), List.of(1, 5));
    }

    @Test
    public void testListWithInvalidElement() {
        FieldBase m = new Minute("a,15");
        RuntimeException exp = assertThrows(RuntimeException.class, () -> {
            m.parse();
        });
        assertEquals(exp.getMessage(), "Invalid field expression : a");

    }

    @Test
    public void testListWithMoreThanMaximumValue() {
        FieldBase w = new Weekday("1,8");
        RuntimeException exp = assertThrows(RuntimeException.class, () -> {
            w.parse();
        });
        assertEquals(exp.getMessage(), "The value for field is more than the maximum allowed");
    }

    @Test
    public void testListWithMissingValue() {
        FieldBase w = new Weekday("1,");
        RuntimeException exp = assertThrows(RuntimeException.class, () -> {
            w.parse();
        });
        assertEquals(exp.getMessage(), "Values does not have valid expression : 1,");
    }

    @Test
    public void testListWithValidComplexValues()
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        FieldBase w = new Weekday("1-2,5-6");
        assertEquals(w.parse(), List.of(1, 2, 5, 6));
    }

    @Test
    public void testListWithValidComplexValues2()
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        FieldBase w = new Weekday("1-4,3-6");
        assertEquals(w.parse(), List.of(1, 2, 3, 4, 5, 6));
    }
}
