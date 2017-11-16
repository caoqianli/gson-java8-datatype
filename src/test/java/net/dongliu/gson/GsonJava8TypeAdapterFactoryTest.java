package net.dongliu.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Liu Dong
 */
public class GsonJava8TypeAdapterFactoryTest {

    // java8 datetime
    @Test
    public void testDateTimeMarshal() throws Exception {
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory()).create();
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        Instant instant = Instant.ofEpochMilli(1457595643101L);
        assertEquals("\"2016-03-10T07:40:43.101Z\"", gson.toJson(instant));
        assertEquals(instant, gson.fromJson("\"2016-03-10T07:40:43.101Z\"", Instant.class));
        assertEquals(instant, gson.fromJson("\"2016-03-10T08:40:43.101+01:00\"", Instant.class));
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, zoneId);
        assertEquals("\"2016-03-10T15:40:43.101+08:00[Asia/Shanghai]\"", gson.toJson(zonedDateTime));
        assertEquals(zonedDateTime, gson.fromJson("\"2016-03-10T15:40:43.101+08:00[Asia/Shanghai]\"", ZonedDateTime.class));
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
        assertEquals("\"2016-03-10T15:40:43.101\"", gson.toJson(localDateTime));
        assertEquals(localDateTime, gson.fromJson("\"2016-03-10T15:40:43.101\"", LocalDateTime.class));
        LocalDate localDate = localDateTime.toLocalDate();
        assertEquals("\"2016-03-10\"", gson.toJson(localDate));
        assertEquals(localDate, gson.fromJson("\"2016-03-10\"", LocalDate.class));
        LocalTime localTime = localDateTime.toLocalTime();
        assertEquals("\"15:40:43.101\"", gson.toJson(localTime));
        assertEquals(localTime, gson.fromJson("\"15:40:43.101\"", LocalTime.class));
        OffsetDateTime offsetDateTime = OffsetDateTime.ofInstant(instant, zoneId);
        assertEquals("\"2016-03-10T15:40:43.101+08:00\"", gson.toJson(offsetDateTime));
        assertEquals(offsetDateTime, gson.fromJson("\"2016-03-10T15:40:43.101+08:00\"", OffsetDateTime.class));
        OffsetTime offsetTime = offsetDateTime.toOffsetTime();
        assertEquals("\"15:40:43.101+08:00\"", gson.toJson(offsetTime));
        assertEquals(offsetTime, gson.fromJson("\"15:40:43.101+08:00\"", OffsetTime.class));

        assertNull(gson.fromJson("null", OffsetTime.class));

        YearMonth yearMonth = YearMonth.of(2016, 3);
        assertEquals("\"2016-03\"", gson.toJson(yearMonth));
        assertEquals(yearMonth, gson.fromJson("\"2016-03\"", YearMonth.class));
        Year year = Year.of(2016);
        assertEquals("\"2016\"", gson.toJson(year));
        assertEquals(year, gson.fromJson("\"2016\"", Year.class));

        Period period = Period.ofDays(1);
        assertEquals("\"P1D\"", gson.toJson(period));
        assertEquals(period, gson.fromJson("\"P1D\"", Period.class));

        Duration duration = Duration.ofDays(1);
        assertEquals("\"P1D\"", gson.toJson(period));
        assertEquals(duration, gson.fromJson("\"P1D\"", Duration.class));
    }

    @Test
    public void testOptional() {
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory()).create();
        assertEquals("10", gson.toJson(OptionalInt.of(10)));
        assertEquals(OptionalInt.of(10), gson.fromJson("10", OptionalInt.class));
        assertEquals("null", gson.toJson(OptionalInt.empty()));
        assertEquals(OptionalInt.empty(), gson.fromJson("null", OptionalInt.class));
        assertEquals("10", gson.toJson(OptionalLong.of(10)));
        assertEquals(OptionalLong.of(10), gson.fromJson("10", OptionalLong.class));
        assertEquals("10.0", gson.toJson(OptionalDouble.of(10.0)));
        assertEquals(OptionalDouble.of(10.0), gson.fromJson("10.0", OptionalDouble.class));

        assertEquals("\"test\"", gson.toJson(Optional.of("test")));
        assertEquals("\"test\"", gson.toJson(Optional.of(Optional.of("test"))));

        assertEquals("null", gson.toJson(Optional.empty()));
        assertEquals("null", gson.toJson(Optional.of(Optional.empty())));
        assertEquals(Optional.of("test"), gson.fromJson("\"test\"", new TypeToken<Optional<String>>() {
        }.getType()));
        assertEquals(Optional.of(Optional.of("test")), gson.fromJson("\"test\"", new TypeToken<Optional<Optional<String>>>() {
        }.getType()));
        assertEquals("null", gson.toJson(Optional.empty()));
        assertEquals(Optional.empty(), gson.fromJson("null", new TypeToken<Optional<String>>() {
        }.getType()));
        assertEquals(Optional.empty(), gson.fromJson("null", new TypeToken<Optional<Optional<String>>>() {
        }.getType()));
        assertEquals(Optional.empty(), gson.fromJson("null", Optional.class));
    }

    @Test
    public void testCustomFormatter() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH)
                .withZone(ZoneId.of("Asia/Shanghai"));
        GsonJava8TypeAdapterFactory typeAdapterFactory = new GsonJava8TypeAdapterFactory()
                .setInstantFormatter(formatter);
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(typeAdapterFactory).create();
        Instant instant = Instant.ofEpochMilli(1457595643101L);
        assertEquals("\"2016-03-10 15:40:43.101\"", gson.toJson(instant));
        assertEquals(instant, gson.fromJson("\"2016-03-10 15:40:43.101\"", Instant.class));
    }
}