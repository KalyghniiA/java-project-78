package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NumberSchemaTest {

    @Test
    public void testSchema1() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertTrue(schema.isValid(null));
    }

    @Test
    public void testSchema2() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        schema.required();

        assertAll(
                () -> assertFalse(schema.isValid(null)),
                () -> assertTrue(schema.isValid(10)),
                () -> assertFalse(schema.isValid("1"))
        );
    }

    @Test
    public void testSchema3() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        schema.required();

        assertAll(
                () -> assertTrue(schema.positive().isValid(10)),
                () -> assertFalse(schema.isValid(-10))
        );
    }

    @Test
    public void testSchema4() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        schema.required();
        schema.range(5, 10);

        assertAll(
                () -> assertTrue(schema.isValid(5)),
                () -> assertTrue(schema.isValid(10)),
                () -> assertFalse(schema.isValid(4)),
                () -> assertFalse(schema.isValid(11))
        );
    }
}
