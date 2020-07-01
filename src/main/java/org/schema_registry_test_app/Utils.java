package org.schema_registry_test_app;

import com.google.protobuf.GeneratedMessageV3;
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class Utils {

    private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);

    public static final String TEST_KEY = "testkey";
    public static final String TEST_TOPIC_AVRO = "testavro";
    public static final String TEST_TOPIC_PROTO = "testproto";
    public static final String TEST_TOPIC_JSON = "testjson";

    private Utils() {
        //prevent instantiation
    }

    public static byte[] uuidBytes() {
        UUID javaUuid = UUID.randomUUID();
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.putLong(javaUuid.getMostSignificantBits());
        buffer.putLong(javaUuid.getLeastSignificantBits());
        return buffer.array();
    }

    public static <T, O, S extends Serializer<O>> void produceOne(T item,
            Class<S> serializerClass) throws ExecutionException, InterruptedException {
        if (item instanceof SpecificRecord) {
            produceOne(item, serializerClass, TEST_TOPIC_AVRO);
        } else if (item instanceof GeneratedMessageV3) {
            produceOne(item, serializerClass, TEST_TOPIC_PROTO);
        } else{
            produceOne(item, serializerClass, TEST_TOPIC_JSON);
        }
    }

    public static <T, O, S extends Serializer<O>> void produceOne(T item, Class<S> serializerClass,
            String topic) throws ExecutionException, InterruptedException {
        try (Producer<String, T> producer = new KafkaProducer<>(producerProperties(serializerClass))) {
            var record = new ProducerRecord<>(topic, TEST_KEY, item);
            RecordMetadata recordMetadata = producer.send(record).get();
            LOGGER.info("Produced test message with offset {} and value size {} to topic {}", recordMetadata.offset(),
                        recordMetadata.serializedValueSize(), topic);
        }
    }

    private static <O, S extends Serializer<O>> Properties producerProperties(Class<S> serializerClass) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, serializerClass.getName());
        props.put(AbstractKafkaSchemaSerDeConfig.AUTO_REGISTER_SCHEMAS, false);
        props.put(AbstractKafkaSchemaSerDeConfig.USE_LATEST_VERSION, true);
        props.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://127.0.0.1:8081");
        return props;
    }
}
