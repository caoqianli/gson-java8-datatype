package net.dongliu.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.MonthDay;
import java.time.YearMonth;

/**
 * @author Liu Dong
 */
class MonthDayAdapter extends TypeAdapter<MonthDay> {

    static final MonthDayAdapter instance = new MonthDayAdapter();

    @Override
    public void write(JsonWriter out, MonthDay monthDay) throws IOException {
        if (monthDay == null) {
            out.nullValue();
            return;
        }
        out.value(monthDay.toString());
    }

    @Override
    public MonthDay read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        String s = in.nextString();
        return MonthDay.parse(s);
    }
}