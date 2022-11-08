package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertAll;


public class NumberSchemaTest {
    private static final int TESTING_NUMBER_10 = 10;
    private static final int TESTING_NUMBER_5 = 5;
    private static final int TESTING_NUMBER_4 = 4;
    private static final int TESTING_NUMBER_11 = 11;
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
                () -> assertTrue(schema.isValid(TESTING_NUMBER_10)),
                () -> assertFalse(schema.isValid("1"))
        );
    }

    @Test
    public void testSchema3() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        schema.required();

        assertAll(
                () -> assertTrue(schema.positive().isValid(TESTING_NUMBER_10)),
                () -> assertFalse(schema.isValid(-TESTING_NUMBER_10))
        );
    }

    @Test
    public void testSchema4() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        schema.required();
        schema.range(TESTING_NUMBER_5, TESTING_NUMBER_10);

        assertAll(
                () -> assertTrue(schema.isValid(TESTING_NUMBER_5)),
                () -> assertTrue(schema.isValid(TESTING_NUMBER_10)),
                () -> assertFalse(schema.isValid(TESTING_NUMBER_4)),
                () -> assertFalse(schema.isValid(TESTING_NUMBER_11))
        );
    }
}
