Java8 data type(Optional and DateTime) module for gson

## Get from maven central repo
```xml
<dependency>
    <groupId>net.dongliu</groupId>
    <artifactId>gson-java8-datatype</artifactId>
    <version>1.1.0</version>
</dependency>
```


## Register
```java
Gson gson = new GsonBuilder().registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory()).create();
```


## Optional Types
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

### Default Setting

Java8 new datetime types serialized using ISO-9601 format by default.

```java
ZoneId zoneId = ZoneId.of("Asia/Shanghai");
Instant instant = Instant.ofEpochMilli(1457595643101L);
gson.toJson(instant); // "2016-03-10T07:40:43.101Z"
ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, zoneId);
gson.toJson(zonedDateTime); // "2016-03-10T15:40:43.101+08:00[Asia/Shanghai]"
LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
gson.toJson(localDateTime); // "2016-03-10T15:40:43.101"
LocalDate localDate = localDateTime.toLocalDate();
gson.toJson(localDate); // "2016-03-10"
LocalTime localTime = localDateTime.toLocalTime();
gson.toJson(localTime); // "15:40:43.101"
OffsetDateTime offsetDateTime = OffsetDateTime.ofInstant(instant, zoneId);
gson.toJson(offsetDateTime); // "2016-03-10T15:40:43.101+08:00"
OffsetTime offsetTime = offsetDateTime.toOffsetTime();
gson.toJson(offsetTime); // "15:40:43.101+08:00"

YearMonth yearMonth = YearMonth.of(2016, 3);
gson.toJson(yearMonth); // "2016-03"
Year year = Year.of(2016);
gson.toJson(year); // "2016"

Period period = Period.ofDays(1);
gson.toJson(period); // "P1D"
Duration duration = Duration.ofDays(1);
gson.toJson(period); // "P1D"
```

### Custom DateTimeFormatter

GsonJava8TypeAdapterFactory has methods for setting custom DateTimeFormatter of Type Instant/LocalDateTime/OffsetDateTime etc..

```java
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH)
        .withZone(ZoneId.of("Asia/Shanghai"));
GsonJava8TypeAdapterFactory typeAdapterFactory = new GsonJava8TypeAdapterFactory()
        .setInstantFormatter(formatter);
Gson gson = new GsonBuilder().registerTypeAdapterFactory(typeAdapterFactory).create();
Instant instant = Instant.ofEpochMilli(1457595643101L);
assertEquals("\"2016-03-10 15:40:43.101\"", gson.toJson(instant));
assertEquals(instant, gson.fromJson("\"2016-03-10 15:40:43.101\"", Instant.class));
```