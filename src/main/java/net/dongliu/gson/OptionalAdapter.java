package net.dongliu.gson;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

/**
 * Deal with java optional type
 *
 * @author Liu Dong
 */
class OptionalAdapter<T> extends TypeAdapter<Optional<T>> {
    private final TypeAdapter<T> delegate;

    public OptionalAdapter(TypeAdapter<T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void write(JsonWriter out, Optional<T> value) throws IOException {
        // optional should not be null
        if (value == null) {
            out.nullValue();
            return;
        }
        if (!value.isPresent()) {
            out.nullValue();
            return;
        }
        delegate.write(out, value.get());
    }

    @Override
    public Optional<T> read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return Optional.empty();
        }
        return Optional.of(delegate.read(in));
    }

    @SuppressWarnings("unchecked")
    public static OptionalAdapter getInstance(Gson gson, TypeToken typeToken) {
        TypeAdapter delegate;
        Type type = typeToken.getType();
        if (type instanceof ParameterizedType) {
            Type innerType = ((ParameterizedType) type).getActualTypeArguments()[0];
            delegate = gson.getAdapter(TypeToken.get(innerType));
        } else if (type instanceof Class) {
            delegate = gson.getAdapter(Object.class);
        } else {
            throw new JsonIOException("Unexpected type type:" + type.getClass());
        }
        return new OptionalAdapter<>(delegate);
    }
}