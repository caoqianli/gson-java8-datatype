package net.dongliu.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;

import static java.time.format.DateTimeFormatter.*;

/**
 * @author Liu Dong
 */
class Java8TimeAdapter extends TypeAdapter<TemporalAccessor> {
    private final DateTimeFormatter dtf;
    private final TemporalQuery<TemporalAccessor> temporalQuery;

    static final Java8TimeAdapter instantAdapter = new Java8TimeAdapter(ISO_INSTANT, Instant::from);
    static final Java8TimeAdapter localDateAdapter = new Java8TimeAdapter(ISO_LOCAL_DATE, LocalDate::from);
    static final Java8TimeAdapter localDateTimeAdapter = new Java8TimeAdapter(ISO_LOCAL_DATE_TIME, LocalDateTime::from);
    static final Java8TimeAdapter localTimeAdapter = new Java8TimeAdapter(ISO_LOCAL_TIME, LocalTime::from);
    static final Java8TimeAdapter offsetDateTimeAdapter = new Java8TimeAdapter(ISO_OFFSET_DATE_TIME, OffsetDateTime::from);
    static final Java8TimeAdapter offsetTimeAdapter = new Java8TimeAdapter(ISO_OFFSET_TIME, OffsetTime::from);
    static final Java8TimeAdapter zonedDateTimeAdapter =new Java8TimeAdapter(ISO_ZONED_DATE_TIME, ZonedDateTime::from);

    public Java8TimeAdapter(DateTimeFormatter dtf, TemporalQuery<TemporalAccessor> temporalQuery) {
        this.dtf = dtf;
        this.temporalQuery = temporalQuery;
    }

    @Override
    public void write(JsonWriter out, TemporalAccessor value) throws IOException {
        // value type should not be null
        if (value == null) {
            out.nullValue();
            return;
        }
        String str = dtf.format(value);
        out.value(str);
    }

    @Override
    public TemporalAccessor read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        String str = in.nextString();
        return dtf.parse(str, temporalQuery);
    }
}