# Schema registry test app

Test app to test compatibility for reading and writing to Kafka using the Confluent Schema Registry.
For now it just write a test message used the three by default supported formats, Avro, Protobuf and JSON Schema.
The topics it uses respectively are testavro, testproto and testjson. You can run the app with `mvn exec:java`, from
your ide or as jar.

It's using a lot of 'sensible defaults', that are not changeable at the moment, like the schema registry url.
Please let me know if you want something to be configurable, for now the simpler the better.

## Register the schema's

You should register the schema's using the schema registry plugin before use, as it's explicitly configured not to auto register schema's.
This can be triggered by running `mvn schema-registry:register` in the root of the project.