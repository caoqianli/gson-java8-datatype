package net.dongliu.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.Period;

/**
 * @author Liu Dong
 */
class PeriodAdapter extends TypeAdapter<Period> {

    static final PeriodAdapter instance = new PeriodAdapter();

    @Override
    public void write(JsonWriter out, Period period) throws IOException {
        if (period == null) {
            out.nullValue();
            return;
        }
        out.value(period.toString());
    }

    @Override
    public Period read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        String s = in.nextString();
        return Period.parse(s);
    }
}