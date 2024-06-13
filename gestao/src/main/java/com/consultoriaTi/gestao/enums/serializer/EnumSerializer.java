package com.consultoriaTi.gestao.enums.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.consultoriaTi.gestao.enums.EnumDescription;

import java.io.IOException;

public class EnumSerializer extends StdSerializer<EnumDescription> {

    public EnumSerializer() {
        this(null);
    }

    public EnumSerializer(Class<EnumDescription> t) {
        super(t);
    }

    @Override
    public void serialize(EnumDescription anEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("name");
        jsonGenerator.writeString(anEnum.name());
        jsonGenerator.writeFieldName("description");
        jsonGenerator.writeString(anEnum.getDescription());
        jsonGenerator.writeEndObject();
    }
}
