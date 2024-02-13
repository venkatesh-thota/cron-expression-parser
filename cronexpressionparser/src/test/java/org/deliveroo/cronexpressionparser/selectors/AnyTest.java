package org.deliveroo.cronexpressionparser.selectors;

import org.deliveroo.cronexpressionparser.fields.*;
import org.junit.Test;
//import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnyTest {

    @Test
    public void testMinuteAnyParserPossibilities() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        FieldBase d = new Minute("*");
        assertEquals(d.parse(), List
                .of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
                        25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,41, 42, 43, 44, 45, 46, 47, 48 , 49, 50,51 ,52,53 ,54 ,55,56,57, 58,59 ));
    }

    @Test
    public void testHourAnyParserPossibilities() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        FieldBase d = new Hour("*");
        assertEquals(d.parse(), List
                .of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23 ));
    }


    @Test
    public void testDayAnyParserPossibilities()
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        FieldBase d = new Day("*");
        assertEquals(d.parse(), List
                .of( 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
                        25, 26, 27, 28, 29, 30, 31));
    }

    @Test
    public void testWeekdayAnyParserPossibilities()
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        FieldBase w = new Weekday("*");
        assertEquals(w.parse(), List
                .of(0, 1, 2, 3, 4, 5, 6));
    }

    @Test
    public void testMonthAnyParserPossibilities()
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        FieldBase w = new Month("*");
        assertEquals(w.parse(), List
                .of( 1, 2, 3, 4, 5, 6,7,8,9,10,11,12));
    }
}
