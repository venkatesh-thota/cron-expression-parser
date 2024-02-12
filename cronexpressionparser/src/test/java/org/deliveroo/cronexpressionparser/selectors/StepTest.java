package org.deliveroo.cronexpressionparser.selectors;

import org.deliveroo.cronexpressionparser.fields.FieldBase;
import org.deliveroo.cronexpressionparser.fields.Minute;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StepTest {

    @Test
    public void testStepParserPossibilities()
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        FieldBase m = new Minute("*/15");
        assertEquals(m.parse(), List.of(0, 15, 30, 45));
    }

    @Test
    public void testStepWithMissingValues() {
        FieldBase m = new Minute("*/");
        RuntimeException exp = assertThrows(RuntimeException.class, () -> {
            m.parse();
        });
        assertEquals(exp.getMessage(), "Step does not have valid expression : */");
    }

    @Test
    public void testStepWithInvalidStepStart() {
        FieldBase m = new Minute("a/15");
        RuntimeException exp = assertThrows(RuntimeException.class, () -> {
            m.parse();
        });
        assertEquals(exp.getMessage(), "Step does not have valid expression : a/15");
    }

    @Test
    public void testStepWithWrongStepStart() {
        FieldBase m = new Minute("60/15");
        RuntimeException exp = assertThrows(RuntimeException.class, () -> {
            m.parse();
        });
        assertEquals(exp.getMessage(), "Step start is more than maximum value");
    }

    @Test
    public void testStepWithWrongStepValue() {
        FieldBase m = new Minute("*/60");
        RuntimeException exp = assertThrows(RuntimeException.class, () -> {
            m.parse();
        });
        assertEquals(exp.getMessage(), "Step size is more than maximum value");
    }
}
