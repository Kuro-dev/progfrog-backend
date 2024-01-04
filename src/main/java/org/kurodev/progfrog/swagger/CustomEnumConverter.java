package org.kurodev.progfrog.swagger;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverterContext;
import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.oas.models.media.Schema;
import org.kurodev.progfrog.game.map.TileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Iterator;

@Component
public class CustomEnumConverter implements ModelConverter {

    private final ModelResolver defaultConverter;

    @Autowired
    public CustomEnumConverter(ObjectMapper objectMapper) {
        this.defaultConverter = new ModelResolver(objectMapper);
    }

    @Override
    public Schema resolve(AnnotatedType type, ModelConverterContext context, Iterator<ModelConverter> chain) {
        Schema model = defaultConverter.resolve(type, context, chain);
        if (type.getType().getTypeName().contains(TileType.class.getName())) {
            model.addExtension("x-enum-varnames", Arrays.toString(TileType.values()));
        }
        return model;
    }
}