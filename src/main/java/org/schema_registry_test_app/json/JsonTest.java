package org.schema_registry_test_app.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaInject;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaString;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@JsonSchemaInject(strings = { @JsonSchemaString(path = "javaType", value = "org.schema_registry_test_app.json" +
        ".JsonTest") })
public class JsonTest {
    @Nonnull
    @JsonProperty(required=true)
    public byte[] id;
    @Nonnull
    @JsonProperty(required=true)
    public Language by;
    @JsonProperty(required=true)
    public long counter;
    @Nullable
    @JsonProperty(required=true)
    public String input;
    @Nonnull
    @JsonProperty(required=true)
    public List<Result> results;

    public JsonTest(
            @NotNull byte[] id,
            @NotNull Language by,
            long counter,
            @Nullable String input,
            @NotNull List<Result> results) {
        this.id = id;
        this.by = by;
        this.counter = counter;
        this.input = input;
        this.results = results;
    }
}
