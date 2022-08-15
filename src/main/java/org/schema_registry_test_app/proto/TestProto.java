package org.schema_registry_test_app.proto;

import com.google.protobuf.ByteString;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializer;
import org.schema_registry_test_app.Utils;

import java.util.concurrent.ExecutionException;

public class TestProto {

    private TestProto() {
        //prevent instantiation
    }

    private static Test.ProtoTest testValue() {
        return Test.ProtoTest.newBuilder()
                .setId(ByteString.copyFrom(Utils.uuidBytes()))
                .setCounter(1L)
                .setInput("String")
                .addResults(ResultOuterClass.Result.newBuilder()
                        .setUp("STRING")
                        .setDown("string")
                        .build())
                .setBy(Test.ProtoTest.Language.Java)
                .build();
    }

    @SuppressWarnings("unchecked")
    public static void produceOne() throws ExecutionException, InterruptedException {
        Utils.produceOne(testValue(), KafkaProtobufSerializer.class);
    }
}
