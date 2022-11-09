package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertAll;

public class StringSchemaTest {

    @Test
    public void testSchema1() {
        Validator v = new Validator();

        StringSchema schema = v.string();
        assertAll(
                () -> assertTrue(schema.isValid("")),
                () -> assertTrue(schema.isValid(null))
        );
    }

    @Test
    public void testSchema2() {
        Validator v = new Validator();

        StringSchema schema = v.string();
        schema.required();

        assertAll(
                () -> assertTrue(schema.isValid("what does the fox say")),
                () -> assertTrue(schema.isValid("hexlet")),
                () -> assertFalse(schema.isValid(null)),
                () -> assertFalse(schema.isValid(""))
        );

    }

    @Test
    public void testSchema3() {
        Validator v = new Validator();

        StringSchema schema = v.string();
        schema.required();

        assertAll(
                () -> assertTrue(schema.contains("wh").isValid("what does the fox say")),
                () -> assertTrue(schema.contains("what").isValid("what does the fox say")),
                () -> assertFalse(schema.contains("whatthe").isValid("what does the fox say")),
                () -> assertFalse(schema.isValid("what does the fox say"))
        );
    }
}
