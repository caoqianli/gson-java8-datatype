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
 * Gson type adapter for java8 DateTimes.
 *
 * @author Liu Dong
 */
public class Java8TimeAdapter<T extends TemporalAccessor> extends TypeAdapter<T> {
    /**
     * DateTimeFormatter for parsing json string value to DateTime
     */
    private final DateTimeFormatter readFormatter;
    /**
     * DateTimeFormatter for writing DateTime value to json string
     */
    private final DateTimeFormatter writeFormatter;
    /**
     * Using with readFormatter, to parse json string to DateTime value
     */
    private final TemporalQuery<T> temporalQuery;

    static final Java8TimeAdapter<Instant> instantAdapter =
            new Java8TimeAdapter<>(ISO_OFFSET_DATE_TIME, ISO_INSTANT, Instant::from);
    static final Java8TimeAdapter<LocalDate> localDateAdapter =
            new Java8TimeAdapter<>(ISO_LOCAL_DATE, LocalDate::from);
    static final Java8TimeAdapter<LocalDateTime> localDateTimeAdapter =
            new Java8TimeAdapter<>(ISO_LOCAL_DATE_TIME, LocalDateTime::from);
    static final Java8TimeAdapter<LocalTime> localTimeAdapter =
            new Java8TimeAdapter<>(ISO_LOCAL_TIME, LocalTime::from);
    static final Java8TimeAdapter<OffsetDateTime> offsetDateTimeAdapter =
            new Java8TimeAdapter<>(ISO_OFFSET_DATE_TIME, OffsetDateTime::from);
    static final Java8TimeAdapter<OffsetTime> offsetTimeAdapter =
            new Java8TimeAdapter<>(ISO_OFFSET_TIME, OffsetTime::from);
    static final Java8TimeAdapter<ZonedDateTime> zonedDateTimeAdapter =
            new Java8TimeAdapter<>(ISO_ZONED_DATE_TIME, ZonedDateTime::from);

    public Java8TimeAdapter(DateTimeFormatter dtf, TemporalQuery<T> temporalQuery) {
        this(dtf, dtf, temporalQuery);
    }

    public Java8TimeAdapter(final DateTimeFormatter readFormatter, final DateTimeFormatter writeFormatter,
                            final TemporalQuery<T> temporalQuery) {
        this.readFormatter = readFormatter;
        this.writeFormatter = writeFormatter;
        this.temporalQuery = temporalQuery;
    }

    @Override
    public void write(JsonWriter out, T value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        String str = writeFormatter.format(value);
        out.value(str);
    }

    @Override
    public T read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        String str = in.nextString();
        return readFormatter.parse(str, temporalQuery);
    }
}