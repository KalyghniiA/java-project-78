package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertAll;

public class MapSchemaTest {
    private static final Integer MAX_SIZE = 2;
    private static final Integer TESTING_NUMBER_10 = 10;

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

    @Test
    public void testShapeMap() {
        Validator v = new Validator();

        MapSchema schema = v.map();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", TESTING_NUMBER_10);


        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);


        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);


        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -TESTING_NUMBER_10);


        assertAll(
                /*() -> assertTrue(schema.isValid(human1)),
                () -> assertTrue(schema.isValid(human2)),*/
                () -> assertFalse(schema.isValid(human3)),
                () -> assertFalse(schema.isValid(human4))
        );
    }

}
