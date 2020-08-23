package rudaks.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.NoSuchElementException;

public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    private JsonUtil() {
    }

    public static String toJson(Object obj) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException var2) {
            throw new NoSuchElementException("ss");
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException var3) {
            throw new NoSuchElementException("ss");
        }
    }
}

