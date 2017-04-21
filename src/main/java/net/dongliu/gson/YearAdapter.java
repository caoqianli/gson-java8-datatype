package net.dongliu.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.Year;

/**
 * @author Liu Dong
 */
class YearAdapter extends TypeAdapter<Year> {

    static final YearAdapter instance = new YearAdapter();

    @Override
    public void write(JsonWriter out, Year year) throws IOException {
        if (year == null) {
            out.nullValue();
            return;
        }
        out.value(year.toString());
    }

    @Override
    public Year read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        String s = in.nextString();
        return Year.parse(s);
    }
}