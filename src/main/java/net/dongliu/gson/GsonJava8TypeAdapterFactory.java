package net.dongliu.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.time.format.DateTimeFormatter.*;

/**
 * Deal with java8 optional and datetime type for gson.
 *
 * @author Liu Dong
 */
public class GsonJava8TypeAdapterFactory implements TypeAdapterFactory {
    private static final Java8TimeAdapter<Instant> defaultInstantAdapter =
            new Java8TimeAdapter<>(ISO_OFFSET_DATE_TIME, ISO_INSTANT, Instant::from);
    private static final Java8TimeAdapter<LocalDate> defaultLocalDateAdapter =
            new Java8TimeAdapter<>(ISO_LOCAL_DATE, LocalDate::from);
    private static final Java8TimeAdapter<LocalDateTime> defaultLocalDateTimeAdapter =
            new Java8TimeAdapter<>(ISO_LOCAL_DATE_TIME, LocalDateTime::from);
    private static final Java8TimeAdapter<LocalTime> defaultLocalTimeAdapter =
            new Java8TimeAdapter<>(ISO_LOCAL_TIME, LocalTime::from);
    private static final Java8TimeAdapter<OffsetDateTime> defaultOffsetDateTimeAdapter =
            new Java8TimeAdapter<>(ISO_OFFSET_DATE_TIME, OffsetDateTime::from);
    private static final Java8TimeAdapter<OffsetTime> defaultOffsetTimeAdapter =
            new Java8TimeAdapter<>(ISO_OFFSET_TIME, OffsetTime::from);
    private static final Java8TimeAdapter<ZonedDateTime> defaultZonedDateTimeAdapter =
            new Java8TimeAdapter<>(ISO_ZONED_DATE_TIME, ZonedDateTime::from);

    private Java8TimeAdapter<Instant> instantAdapter = defaultInstantAdapter;
    private Java8TimeAdapter<LocalDate> localDateAdapter = defaultLocalDateAdapter;
    private Java8TimeAdapter<LocalDateTime> localDateTimeAdapter = defaultLocalDateTimeAdapter;
    private Java8TimeAdapter<LocalTime> localTimeAdapter = defaultLocalTimeAdapter;
    private Java8TimeAdapter<OffsetDateTime> offsetDateTimeAdapter = defaultOffsetDateTimeAdapter;
    private Java8TimeAdapter<OffsetTime> offsetTimeAdapter = defaultOffsetTimeAdapter;
    private Java8TimeAdapter<ZonedDateTime> zonedDateTimeAdapter = defaultZonedDateTimeAdapter;

    /**
     * Set custom Formatter for Instant type
     *
     * @param formatter the formatter to decode and encode Instant
     */
    public GsonJava8TypeAdapterFactory setInstantFormatter(DateTimeFormatter formatter) {
        this.instantAdapter = new Java8TimeAdapter<>(formatter, Instant::from);
        return this;
    }

    /**
     * Set custom Formatter for Instant type
     *
     * @param readFormatter  the formatter to decode Instant
     * @param writeFormatter the formatter to encode Instant
     */
    public GsonJava8TypeAdapterFactory setInstantFormatter(DateTimeFormatter readFormatter,
                                                           DateTimeFormatter writeFormatter) {
        this.instantAdapter = new Java8TimeAdapter<>(readFormatter, writeFormatter, Instant::from);
        return this;
    }


    /**
     * Set custom Formatter for LocalDate type
     *
     * @param formatter the formatter to decode and encode LocalDate
     */
    public GsonJava8TypeAdapterFactory setLocalDateFormatter(DateTimeFormatter formatter) {
        this.localDateAdapter = new Java8TimeAdapter<>(formatter, LocalDate::from);
        return this;
    }

    /**
     * Set custom Formatter for LocalDate type
     *
     * @param readFormatter  the formatter to decode LocalDate
     * @param writeFormatter the formatter to encode LocalDate
     */
    public GsonJava8TypeAdapterFactory setLocalDateFormatter(DateTimeFormatter readFormatter,
                                                             DateTimeFormatter writeFormatter) {
        this.localDateAdapter = new Java8TimeAdapter<>(readFormatter, writeFormatter, LocalDate::from);
        return this;
    }


    /**
     * Set custom Formatter for LocalDateTime type
     *
     * @param formatter the formatter to decode and encode LocalDateTime
     */
    public GsonJava8TypeAdapterFactory setLocalDateTimeFormatter(DateTimeFormatter formatter) {
        this.localDateTimeAdapter = new Java8TimeAdapter<>(formatter, LocalDateTime::from);
        return this;
    }

    /**
     * Set custom Formatter for LocalDateTime type
     *
     * @param readFormatter  the formatter to decode LocalDateTime
     * @param writeFormatter the formatter to encode LocalDateTime
     */
    public GsonJava8TypeAdapterFactory setLocalDateTimeFormatter(DateTimeFormatter readFormatter,
                                                                 DateTimeFormatter writeFormatter) {
        this.localDateTimeAdapter = new Java8TimeAdapter<>(readFormatter, writeFormatter, LocalDateTime::from);
        return this;
    }


    /**
     * Set custom Formatter for LocalTime type
     *
     * @param formatter the formatter to decode and encode LocalTime
     */
    public GsonJava8TypeAdapterFactory setLocalTimeFormatter(DateTimeFormatter formatter) {
        this.localTimeAdapter = new Java8TimeAdapter<>(formatter, LocalTime::from);
        return this;
    }

    /**
     * Set custom Formatter for LocalTime type
     *
     * @param readFormatter  the formatter to decode LocalTime
     * @param writeFormatter the formatter to encode LocalTime
     */
    public GsonJava8TypeAdapterFactory setLocalTimeFormatter(DateTimeFormatter readFormatter,
                                                             DateTimeFormatter writeFormatter) {
        this.localTimeAdapter = new Java8TimeAdapter<>(readFormatter, writeFormatter, LocalTime::from);
        return this;
    }

    /**
     * Set custom Formatter for OffsetDateTime type
     *
     * @param formatter the formatter to decode and encode OffsetDateTime
     */
    public GsonJava8TypeAdapterFactory setOffsetDateTimeFormatter(DateTimeFormatter formatter) {
        this.offsetDateTimeAdapter = new Java8TimeAdapter<>(formatter, OffsetDateTime::from);
        return this;
    }

    /**
     * Set custom Formatter for OffsetDateTime type
     *
     * @param readFormatter  the formatter to decode OffsetDateTime
     * @param writeFormatter the formatter to encode OffsetDateTime
     */
    public GsonJava8TypeAdapterFactory setOffsetDateTimeFormatter(DateTimeFormatter readFormatter,
                                                                  DateTimeFormatter writeFormatter) {
        this.offsetDateTimeAdapter = new Java8TimeAdapter<>(readFormatter, writeFormatter, OffsetDateTime::from);
        return this;
    }

    /**
     * Set custom Formatter for OffsetTime type
     *
     * @param formatter the formatter to decode and encode OffsetTime
     */
    public GsonJava8TypeAdapterFactory setOffsetTimeFormatter(DateTimeFormatter formatter) {
        this.offsetTimeAdapter = new Java8TimeAdapter<>(formatter, OffsetTime::from);
        return this;
    }

    /**
     * Set custom Formatter for OffsetTime type
     *
     * @param readFormatter  the formatter to decode OffsetTime
     * @param writeFormatter the formatter to encode OffsetTime
     */
    public GsonJava8TypeAdapterFactory setOffsetTimeFormatter(DateTimeFormatter readFormatter,
                                                              DateTimeFormatter writeFormatter) {
        this.offsetTimeAdapter = new Java8TimeAdapter<>(readFormatter, writeFormatter, OffsetTime::from);
        return this;
    }


    /**
     * Set custom Formatter for ZonedDateTime type
     *
     * @param formatter the formatter to decode and encode ZonedDateTime
     */
    public GsonJava8TypeAdapterFactory setZonedDateTimeFormatter(DateTimeFormatter formatter) {
        this.zonedDateTimeAdapter = new Java8TimeAdapter<>(formatter, ZonedDateTime::from);
        return this;
    }

    /**
     * Set custom Formatter for ZonedDateTime type
     *
     * @param readFormatter  the formatter to decode ZonedDateTime
     * @param writeFormatter the formatter to encode ZonedDateTime
     */
    public GsonJava8TypeAdapterFactory setZonedDateTimeFormatter(DateTimeFormatter readFormatter,
                                                                 DateTimeFormatter writeFormatter) {
        this.zonedDateTimeAdapter = new Java8TimeAdapter<>(readFormatter, writeFormatter, ZonedDateTime::from);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Class<T> cls = (Class<T>) typeToken.getRawType();

        if (cls == Optional.class) {
            // optional
            return (TypeAdapter<T>) OptionalAdapter.getInstance(gson, typeToken);
        } else if (cls == OptionalInt.class) {
            return (TypeAdapter<T>) OptionalIntAdapter.instance;
        } else if (cls == OptionalLong.class) {
            return (TypeAdapter<T>) OptionalLongAdapter.instance;
        } else if (cls == OptionalDouble.class) {
            return (TypeAdapter<T>) OptionalDoubleAdapter.instance;
        } else if (cls == Instant.class) {
            return (TypeAdapter<T>) instantAdapter;
        } else if (cls == LocalDate.class) {
            return (TypeAdapter<T>) localDateAdapter;
        } else if (cls == LocalDateTime.class) {
            return (TypeAdapter<T>) localDateTimeAdapter;
        } else if (cls == LocalTime.class) {
            return (TypeAdapter<T>) localTimeAdapter;
        } else if (cls == OffsetDateTime.class) {
            return (TypeAdapter<T>) offsetDateTimeAdapter;
        } else if (cls == OffsetTime.class) {
            return (TypeAdapter<T>) offsetTimeAdapter;
        } else if (cls == ZonedDateTime.class) {
            return (TypeAdapter<T>) zonedDateTimeAdapter;
        } else if (cls == DayOfWeek.class || cls == Month.class) {
            // use default enum adapter
            return null;
        } else if (cls == MonthDay.class) {
            return (TypeAdapter<T>) MonthDayAdapter.instance;
        } else if (cls == YearMonth.class) {
            return (TypeAdapter<T>) YearMonthAdapter.instance;
        } else if (Year.class.isAssignableFrom(cls)) {
            return (TypeAdapter<T>) YearAdapter.instance;
        } else if (cls == Period.class) {
            return (TypeAdapter<T>) PeriodAdapter.instance;
        } else if (cls == Duration.class) {
            return (TypeAdapter<T>) DurationAdapter.instance;
        } else if (cls == ZoneOffset.class) {
            return (TypeAdapter<T>) ZoneOffsetAdapter.instance;
        } else {
            return null;
        }
    }
}
