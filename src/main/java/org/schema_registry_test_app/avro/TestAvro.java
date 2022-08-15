package org.schema_registry_test_app.avro;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.schema_registry_test_app.Utils;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class TestAvro {
    private TestAvro() {
        //prevent instantiation
    }

    private static AvroTest testValue() {
        return AvroTest.newBuilder()
                .setId(new Uuid(Utils.uuidBytes()))
                .setCounter(1L)
                .setInput("String")
                .setResults(List.of(
                        Result.newBuilder()
                                .setUp("STRING")
                                .setDown("string")
                                .build()
                ))
                .setBy(Language.Java)
                .build();
    }

    public static void produceOne() throws ExecutionException, InterruptedException {
        Utils.produceOne(testValue(), KafkaAvroSerializer.class);
    }
}
