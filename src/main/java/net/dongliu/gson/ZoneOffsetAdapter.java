package net.dongliu.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.Year;
import java.time.ZoneOffset;

/**
 * @author Liu Dong
 */
class ZoneOffsetAdapter extends TypeAdapter<ZoneOffset> {

    static final ZoneOffsetAdapter instance = new ZoneOffsetAdapter();

    @Override
    public void write(JsonWriter out, ZoneOffset zoneOffset) throws IOException {
        // value type should not be null
        if (zoneOffset == null) {
            out.nullValue();
            return;
        }
        out.value(zoneOffset.getId());
    }

    @Override
    public ZoneOffset read(JsonReader in) throws IOException {
        // value type should not be null
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        String s = in.nextString();
        return ZoneOffset.of(s);
    }
}