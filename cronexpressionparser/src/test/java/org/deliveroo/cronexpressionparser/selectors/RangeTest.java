package org.deliveroo.cronexpressionparser.selectors;

import org.deliveroo.cronexpressionparser.fields.FieldBase;
import org.deliveroo.cronexpressionparser.fields.Day;
import org.deliveroo.cronexpressionparser.fields.Month;
import org.deliveroo.cronexpressionparser.fields.Weekday;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RangeTest {

    @Test
    public void testRangeParserPossibilities()
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        FieldBase d = new Day("1-5");
        assertEquals(d.parse(), List
                .of(1, 2, 3, 4, 5));
    }

    @Test
    public void testRangeWithMissingValue() {
        FieldBase w = new Weekday("1-");
        RuntimeException exp = assertThrows(RuntimeException.class, () -> {
            w.parse();
        });
        assertEquals(exp.getMessage(), "Invalid field expression : 1-");
    }

    @Test
    public void testRangeWithWrongMaximum() {
        FieldBase w = new Weekday("1-8");
        RuntimeException exp = assertThrows(RuntimeException.class, () -> {
            w.parse();
        });
        assertEquals(exp.getMessage(), "Range maximum is not valid. Given : 8 Max allowed : 6");
    }
    @Test
    public void testRangeWithWrongMinimum() {
        FieldBase m = new Month("0-6");
        RuntimeException exp = assertThrows(RuntimeException.class, () -> {
            m.parse();
        });
        assertEquals(exp.getMessage(), "Range minimum is not valid. Given : 0 Min allowed : 1");
    }

    @Test
    public void testRangeWithWrongMinimum2() {
        FieldBase m = new Month("13-16");
        RuntimeException exp = assertThrows(RuntimeException.class, () -> {
            m.parse();
        });
        assertEquals(exp.getMessage(), "Range minimum is not valid. Given : 13 Max allowed : 12");
    }

    @Test
    public void testRangeWithWrongMinimumAndMaximum() {
        FieldBase m = new Month("6-0");
        RuntimeException exp = assertThrows(RuntimeException.class, () -> {
            m.parse();
        });
        assertEquals(exp.getMessage(), "Range minimum/maximum are in wrong order. maximum should be : 6 and minimum should be : 0");
    }

    @Test
    public void testRangeWithWeekValues() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        FieldBase w = new Weekday("MON-FRI");
        assertEquals(w.parse(), List
                .of(0, 1, 2, 3, 4));
    }

    @Test
    public void testRangeextenParserPossibilities()
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        FieldBase d = new Day("29-3");
        assertEquals(d.parse(), List
                .of(29, 30, 0, 1, 2, 3));
    }
}
