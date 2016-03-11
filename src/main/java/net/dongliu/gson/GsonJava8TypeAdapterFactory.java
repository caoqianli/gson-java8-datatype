package net.dongliu.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.time.*;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

/**
 * Deal with java8 optional and datetime type for gson
 *
 * @author Liu Dong
 */
public class GsonJava8TypeAdapterFactory implements TypeAdapterFactory {
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
            return (TypeAdapter<T>) Java8TimeAdapter.instantAdapter;
        } else if (cls == LocalDate.class) {
            return (TypeAdapter<T>) Java8TimeAdapter.localDateAdapter;
        } else if (cls == LocalDateTime.class) {
            return (TypeAdapter<T>) Java8TimeAdapter.localDateTimeAdapter;
        } else if (cls == LocalTime.class) {
            return (TypeAdapter<T>) Java8TimeAdapter.localTimeAdapter;
        } else if (cls == OffsetDateTime.class) {
            return (TypeAdapter<T>) Java8TimeAdapter.offsetDateTimeAdapter;
        } else if (cls == OffsetTime.class) {
            return (TypeAdapter<T>) Java8TimeAdapter.offsetTimeAdapter;
        } else if (cls == ZonedDateTime.class) {
            return (TypeAdapter<T>) Java8TimeAdapter.zonedDateTimeAdapter;
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
