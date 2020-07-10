# Schema registry test app

Test app to test compatibility for reading and writing to Kafka using the Confluent Schema Registry.
For now it just write a test message used the three by default supported formats, Avro, Protobuf and JSON Schema.
The topics it uses respectively are testavro, testproto and testjson. You can run the app with `mvn exec:java`, from
your ide or as jar.

It's using a lot of 'sensible defaults', like the schema registry url and topic names.
For now only the Schema Registry Url (`SCHEMA_REGISTRY_URL`) and the Bootstrap Servers(`BOOTSTRAP_SERVERS`) can be overwritten using environment variables.

## Register the schema's

You should register the schema's using the schema registry plugin before use, as it's explicitly configured not to auto register schema's.
This can be triggered by running `mvn schema-registry:register` in the root of the project.
Optionally you can add `-Dschema-registry-url=something` after mvn to overwrite the default url.