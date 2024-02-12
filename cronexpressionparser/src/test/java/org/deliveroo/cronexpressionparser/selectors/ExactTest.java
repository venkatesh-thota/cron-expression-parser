package org.deliveroo.cronexpressionparser.selectors;

import org.deliveroo.cronexpressionparser.fields.FieldBase;
import org.deliveroo.cronexpressionparser.fields.Day;
import org.deliveroo.cronexpressionparser.fields.Month;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExactTest {

    @Test
    public void testDayExactParserPossibilities()
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        FieldBase d = new Day("1");
        assertEquals(d.parse(), List
                .of(1));
    }

    @Test
    public void testDayExactParserWithException() {
        FieldBase d = new Day("59");
        RuntimeException exp = assertThrows(RuntimeException.class, () -> {
            d.parse();
        });
        assertEquals(exp.getMessage(), "The value for field is more than the maximum allowed");
    }

    @Test
    public void testDayExactParserWithExceptionOfMinimum() {
        FieldBase m = new Month("0");
        RuntimeException exp = assertThrows(RuntimeException.class, () -> {
            m.parse();
        });
        assertEquals(exp.getMessage(), "The value for field is less than the minimum allowed");
    }
}
