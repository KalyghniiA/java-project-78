package hexlet.code;

import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertAll;

public class MapSchemaTest {
    private static final Integer MAX_SIZE = 2;

    @Test
    public void testSchema1() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        assertTrue(schema.isValid(null));
    }

    @Test
    public void testSchema2() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        schema.required();

        assertAll(
                () -> assertFalse(schema.isValid(null)),
                () -> assertTrue(schema.isValid(new HashMap()))
        );
    }

    @Test
    public void testSchema3() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        schema.required();

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        assertTrue(schema.isValid(data));
    }

    @Test
    public void testSchema4() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        schema.required();
        schema.sizeof(MAX_SIZE);

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        assertAll(
                () -> assertFalse(schema.isValid(data)),
                () -> assertTrue(() -> {
                    data.put("key2", "value2");

                    return schema.isValid(data);
                })
        );
    }
}
