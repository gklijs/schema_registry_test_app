# Schema registry test app

Test app to test compatibility for reading and writing to Kafka using the Confluent Schema Registry.
For now it just write a test message used the three by default supported formats, Avro, Protobuf and JSON Schema.
The topics it uses respectively are testavro, testproto and testjson. You can run the app with `mvn exec:java`, from
your ide or as jar.

## Register the schema's

It should be possible to register the schema's using the schema registry plugin. This can be triggered by running
`mvn schema-registry:register` in the root of the project. This doesn't work well through.
- Avro: works as expected, will not create new versions or subjects with the producer.
- Proto: will create a new version of `testproto-value` subject. Seems almost identical to the one stored from the plugin.
- Json: for now changed the subject to `testjson-value-n` in the plugin configuration. Otherwise wants to update the
`testjson-value` subject with one that is incompatible, leading to an error when running the program.