package com.daeman2k.powerbound.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.io.InputStream;

public final class ConfigLoader {

        private static final ObjectMapper YAML_MAPPER = new ObjectMapper(new YAMLFactory())
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        private static final ObjectMapper JSON_MAPPER = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

    public static StrengthConfig loadFromYaml(InputStream in) throws IOException {
        if (in == null) throw new IOException("YAML resource stream is null");
        return YAML_MAPPER.readValue(in, StrengthConfig.class);
    }

    public static StrengthConfig loadFromJson(InputStream in) throws IOException {
        if (in == null) throw new IOException("JSON resource stream is null");
        return JSON_MAPPER.readValue(in, StrengthConfig.class);
    }
}
