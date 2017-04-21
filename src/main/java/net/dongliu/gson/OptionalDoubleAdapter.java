package net.dongliu.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.OptionalDouble;

/**
 * @author Liu Dong
 */
class OptionalDoubleAdapter extends TypeAdapter<OptionalDouble> {

    static final OptionalDoubleAdapter instance = new OptionalDoubleAdapter();

    @Override
    public void write(JsonWriter out, OptionalDouble value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        if (!value.isPresent()) {
            out.nullValue();
            return;
        }
        out.value(value.getAsDouble());
    }

    @Override
    public OptionalDouble read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return OptionalDouble.empty();
        }
        double value = in.nextDouble();
        return OptionalDouble.of(value);
    }
}