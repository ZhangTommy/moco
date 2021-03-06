package com.github.dreamhead.moco.parser.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.github.dreamhead.moco.parser.model.ResponseSetting;
import com.github.dreamhead.moco.parser.model.SeqContainer;
import com.github.dreamhead.moco.parser.model.TextContainer;

import java.io.IOException;
import java.util.List;

public class SeqContainerDeserializer extends JsonDeserializer<SeqContainer> {
    @Override
    public SeqContainer deserialize(final JsonParser jp, final DeserializationContext ctx) throws IOException {
        JsonToken currentToken = jp.getCurrentToken();

        if (currentToken == JsonToken.START_ARRAY) {
            return new SeqContainer(getSeqSettings(jp));
        }

        return (SeqContainer) ctx.handleUnexpectedToken(TextContainer.class, jp);
    }

    private Iterable<ResponseSetting> getSeqSettings(final JsonParser jp) throws IOException {
        TypeReference<List<ResponseSetting>> reference = new TypeReference<List<ResponseSetting>>() {};
        return jp.readValueAs(reference);
    }
}
