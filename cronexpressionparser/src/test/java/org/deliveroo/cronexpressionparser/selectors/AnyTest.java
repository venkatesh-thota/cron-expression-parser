package org.deliveroo.cronexpressionparser.selectors;

import org.deliveroo.cronexpressionparser.fields.FieldBase;
import org.deliveroo.cronexpressionparser.fields.Day;
import org.deliveroo.cronexpressionparser.fields.Weekday;
import org.junit.Test;
//import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnyTest {

    @Test
    public void testDayAnyParserPossibilities()
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        FieldBase d = new Day("*");
        assertEquals(d.parse(), List
                .of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
                        25, 26, 27, 28, 29, 30, 31));
    }

    @Test
    public void testWeekdayAnyParserPossibilities()
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        FieldBase w = new Weekday("*");
        assertEquals(w.parse(), List
                .of(0, 1, 2, 3, 4, 5, 6));
    }
}
