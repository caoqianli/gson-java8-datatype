# gson-java8-datatype
Java8 data type(Optional and DateTime) module for gson

## Get from maven central repo
```xml
<dependency>
    <groupId>net.dongliu</groupId>
    <artifactId>gson-java8-datatype</artifactId>
    <version>1.0.0</version>
</dependency>
```


## Register
```java
Gson gson = new GsonBuilder().registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory()).create();
```


## Optinal Types
Empty Optional value is treated as null; Non-empty optional value is treat as optional.get() 
```java
gson.toJson(OptionalInt.of(10)); // 10
gson.fromJson("10", OptionalInt.class); // = OptionalInt.of(10)
gson.toJson(OptionalInt.empty()); // null
gson.fromJson("null", OptionalInt.class); // = OptionalInt.empty()

gson.toJson(Optional.of("test")); // "test"
gson.toJson(Optional.of(Optional.of("test"))); // "test"

gson.toJson(Optional.empty()); // null
gson.toJson(Optional.of(Optional.empty())); // null
gson.fromJson("\"test\"", new TypeToken<Optional<String>>() {}.getType()); // = Optional.of("test")
gson.fromJson("\"test\"", new TypeToken<Optional<Optional<String>>>() {}.getType()); // =Optional.of(Optional.of("test"))
gson.fromJson("null", new TypeToken<Optional<String>>() {}.getType()); // = Optional.empty()
gson.fromJson("null", new TypeToken<Optional<Optional<String>>>() {}.getType()); // = Optional.empty()
gson.fromJson("null", Optional.class); // = Optional.empty()
```

## DateTime Types
Java8 new datetime types serialized using ISO-9601 format 

```java
ZoneId zoneId = ZoneId.of("Asia/Shanghai");
Instant instant = Instant.ofEpochMilli(1457595643101L);
assertEquals("\"2016-03-10T07:40:43.101Z\"", gson.toJson(instant));
assertEquals(instant, gson.fromJson("\"2016-03-10T07:40:43.101Z\"", Instant.class));
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
```
