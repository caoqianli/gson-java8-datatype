package net.dongliu.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;
import java.util.Objects;

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

    /**
     * Create a Java8TimeAdapter instance to deal with type T
     *
     * @param formatter     the formatter to decode and encode DateTime
     * @param temporalQuery the function to construct and convert type T instance
     */
    public Java8TimeAdapter(DateTimeFormatter formatter, TemporalQuery<T> temporalQuery) {
        this(formatter, formatter, temporalQuery);
    }

    /**
     * Create a Java8TimeAdapter instance to deal with type T
     *
     * @param readFormatter  the formatter to decode DateTime
     * @param writeFormatter the formatter to encode DateTime
     * @param temporalQuery  the function to construct and convert type T instance
     */
    public Java8TimeAdapter(DateTimeFormatter readFormatter, DateTimeFormatter writeFormatter,
                            TemporalQuery<T> temporalQuery) {
        this.readFormatter = Objects.requireNonNull(readFormatter);
        this.writeFormatter = Objects.requireNonNull(writeFormatter);
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