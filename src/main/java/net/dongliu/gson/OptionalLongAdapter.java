package net.dongliu.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.OptionalLong;

/**
 * @author Liu Dong
 */
class OptionalLongAdapter extends TypeAdapter<OptionalLong> {

    static final OptionalLongAdapter instance = new OptionalLongAdapter();

    @Override
    public void write(JsonWriter out, OptionalLong value) throws IOException {
        // value type should not be null
        if (value == null) {
            out.nullValue();
            return;
        }
        if (!value.isPresent()) {
            out.nullValue();
            return;
        }
        out.value(value.getAsLong());
    }

    @Override
    public OptionalLong read(JsonReader in) throws IOException {
        // value type should not be null
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return OptionalLong.empty();
        }
        long value = in.nextLong();
        return OptionalLong.of(value);
    }
}