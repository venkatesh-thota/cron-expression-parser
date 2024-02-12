package org.deliveroo.cronexpressionparser;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CronExpressionParserTest {

    @Test
    public void testSimpleCronExpression()
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        assertEquals(new CronExpressionParser("*/15 0 1,5 * 1-5 /usr/bin/find").toString(), "minute        0 15 30 45\n"
                + "hour          0\n"
                + "day of month  1 5\n"
                + "month         1 2 3 4 5 6 7 8 9 10 11 12\n"
                + "day of week   1 2 3 4 5\n"
                + "command       /usr/bin/find");
    }

    @Test
    public void testCronExpressionWithComplexLists()
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        assertEquals(new CronExpressionParser("*/15 0 1-5,1-15 * 1-5 /usr/bin/find").toString(), "minute        0 15 30 45\n"
                + "hour          0\n"
                + "day of month  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15\n"
                + "month         1 2 3 4 5 6 7 8 9 10 11 12\n"
                + "day of week   1 2 3 4 5\n"
                + "command       /usr/bin/find");
    }

    @Test
    public void testCronExpressionWithWrongStep() {
        RuntimeException exp = assertThrows(RuntimeException.class, () -> {
            new CronExpressionParser("*/105 0 1,5 * 1-5 /usr/bin/find").toString();
        });
        assertEquals(exp.getMessage(), "Step size is more than maximum value");
    }

    @Test
    public void testCronExpressionWithWrongArgument() {
        RuntimeException exp = assertThrows(RuntimeException.class, () -> {
            new CronExpressionParser("").toString();
        });
        assertEquals(exp.getMessage(), "There must be 6 fields in the expression");
    }
}
