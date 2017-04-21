package net.dongliu.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.OptionalInt;

/**
 * @author Liu Dong
 */
class OptionalIntAdapter extends TypeAdapter<OptionalInt> {

    static final OptionalIntAdapter instance = new OptionalIntAdapter();

    @Override
    public void write(JsonWriter out, OptionalInt value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        if (!value.isPresent()) {
            out.nullValue();
            return;
        }
        out.value(value.getAsInt());
    }

    @Override
    public OptionalInt read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return OptionalInt.empty();
        }
        int value = in.nextInt();
        return OptionalInt.of(value);
    }
}