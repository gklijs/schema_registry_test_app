package org.schema_registry_test_app.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaInject;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaString;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

@JsonSchemaInject(strings = {@JsonSchemaString(path="javaType", value="org.schema_registry_test_app.json.Result")})
public class Result {
    @Nonnull
    @JsonProperty(required=true)
    public String up;
    @Nonnull
    @JsonProperty(required=true)
    public String down;

    public Result(@NotNull String up, @NotNull String down){
        this.up = up;
        this.down = down;
    }
}
