package com.thinksky.kafka.twitter.stream.connect;

import com.github.jcustenborder.kafka.connect.utils.BaseDocumentationTest;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.kafka.connect.data.Schema;
import org.junit.jupiter.api.Disabled;

@Disabled
public class DocumentationTest extends BaseDocumentationTest {
    static Schema schema(Field field) {
        try {
            return (Schema) field.get(null);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    protected List<Schema> schemas() {
        List<Schema> schemas = Arrays.stream(StatusConverter.class.getFields())
            .filter(field -> Modifier.isFinal(field.getModifiers()))
            .filter(field -> Modifier.isStatic(field.getModifiers()))
            .filter(field -> Schema.class.equals(field.getType()))
            .map(DocumentationTest::schema)
            .collect(Collectors.toList());
        return schemas;
    }
}
