package org.schema_registry_test_app.json;

import io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializer;
import org.schema_registry_test_app.Utils;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class TestJson {

    private TestJson() {
        //prevent instantiation
    }

    private static JsonTest testValue() {
        return new JsonTest(Utils.uuidBytes(), Language.Java, 1L, "String", List.of(new Result("STRING", "string")));
    }

    @SuppressWarnings("unchecked")
    public static void produceOne() throws ExecutionException, InterruptedException {
        Utils.produceOne(testValue(), KafkaJsonSchemaSerializer.class);
    }
}
