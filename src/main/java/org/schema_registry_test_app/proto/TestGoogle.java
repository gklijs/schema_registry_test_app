package org.schema_registry_test_app.proto;

import com.google.type.Color;
import com.google.type.DateTime;
import com.google.type.Money;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializer;
import org.schema_registry_test_app.Utils;

import java.util.concurrent.ExecutionException;

public class TestGoogle {

    private TestGoogle() {
        //prevent instantiation
    }

    private static Google.GoogleTest testValue() {
        return Google.GoogleTest.newBuilder()
                .setColor(Color.newBuilder()
                        .setBlue(30f)
                        .setBlue(40f)
                        .setGreen(50f)
                        .build())
                .setDateTime(DateTime.newBuilder().setDay(14).setMonth(8).setYear(2022).build())
                .setMoney(Money.newBuilder().setCurrencyCode("EUR").setUnits(20L).setNanos(22).build())
                .setBy(Google.GoogleTest.Language.Java)
                .setCounter(1L)
                .build();
    }

    @SuppressWarnings("unchecked")
    public static void produceOne() throws ExecutionException, InterruptedException {
        Utils.produceOne(testValue(), KafkaProtobufSerializer.class);
    }
}
