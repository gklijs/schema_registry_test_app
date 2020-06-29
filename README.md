# Schema registry test app

Test app to test compatibility for reading and writing to Kafka using the Confluent Schema Registry.
For now it just write a test message used the three by default supported formats, Avro, Protobuf and JSON Schema.
The topics it uses respectively are testavro, testproto and testjson.