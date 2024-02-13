package org.deliveroo.cronexpressionparser;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CronExpressionParserTest {

    @Test
    public void testSampleCronExpression()
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
        assertEquals(new CronExpressionParser("*/15 0 1-5,1-15 * 1-5 /usr/bin/find").toString(),
                "minute        0 15 30 45\n"
                + "hour          0\n"
                + "day of month  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15\n"
                + "month         1 2 3 4 5 6 7 8 9 10 11 12\n"
                + "day of week   1 2 3 4 5\n"
                + "command       /usr/bin/find");
    }

    @Test
    public void testCronExpressionWithComplexStepValue()
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        assertEquals(new CronExpressionParser("15 5/1 5 12 1-5 /usr/bin/find").toString(),
                "minute        15\n"
                        + "hour          5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23\n"
                        + "day of month  5\n"
                        + "month         12\n"
                        + "day of week   1 2 3 4 5\n"
                        + "command       /usr/bin/find");
    }

    @Test
    public void testCronExpressionWithComplexCronExp()
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        assertEquals(new CronExpressionParser("5 5/5 15-19 */3 1 /usr/bin/find").toString(),
                "minute        5\n"
                        + "hour          5 10 15 20\n"
                        + "day of month  15 16 17 18 19\n"
                        + "month         1 4 7 10\n"
                        + "day of week   1\n"
                        + "command       /usr/bin/find");
    }

    @Test
    public void testCronExpressionWithComplexCronExpression()
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        assertEquals(new CronExpressionParser("*/5 5/5 15-19 3,6 1 /usr/bin/find").toString(),
                "minute        0 5 10 15 20 25 30 35 40 45 50 55\n"
                        + "hour          5 10 15 20\n"
                        + "day of month  15 16 17 18 19\n"
                        + "month         3 6\n"
                        + "day of week   1\n"
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
