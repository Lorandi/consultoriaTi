package com.consultoriaTi.client.enums.serializer;


import com.consultoriaTi.client.enums.EnumDescription;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

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
