package org.schema_registry_test_app.json;

import io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializer;
import org.schema_registry_test_app.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TestJson {

    private TestJson() {
        //prevent instantiation
    }

    private static List<Long> idList(){
        List<Long> integers = new ArrayList<>();
        for(byte b : Utils.uuidBytes()){
            integers.add((long) b);
        }
        return integers;
    }

    private static List<Result> resultList(){
        Result result = new Result();
        result.setUp("STRING");
        result.setDown("string");
        return List.of(result);
    }

    private static JsonTest testValue() {
        JsonTest jsonTest = new JsonTest();
        jsonTest.setId(idList());
        jsonTest.setBy(JsonTest.By.JAVA);
        jsonTest.setCounter(1L);
        jsonTest.setInput("String");
        jsonTest.setResults(resultList());
        return jsonTest;
    }

    @SuppressWarnings("unchecked")
    public static void produceOne() throws ExecutionException, InterruptedException {
        Utils.produceOne(testValue(), KafkaJsonSchemaSerializer.class);
    }
}
