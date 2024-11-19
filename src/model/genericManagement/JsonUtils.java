package model.genericManagement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .enable(SerializationFeature.INDENT_OUTPUT)
            .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .setSerializationInclusion(JsonInclude.Include.ALWAYS)
            .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)  // Para permitir vacíos como null
            .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);



    // SAVE LIST IN A JSON FILE
    public static <T> void saveList(List<T> list, String fileString,Class<T> type) {
        try {
            mapper.writerFor(mapper.getTypeFactory()
                            .constructCollectionType(List.class, type))
                    .writeValue(new File(fileString), list);
            System.out.println("List saved in the file " + fileString);
        } catch (IOException e) {
            System.out.println("Error to save the file: " + e.getMessage());
        }
    }

    // LOAD LIST TO THE JSON FILE
    public static <T> List<T> loadList(String fileString, Class<T> type) {
        try {
            List<T> list = new ArrayList<>();

            File file = new File(fileString);
            if (file.exists() && file.length() > 0) {
                JavaType listType = mapper.getTypeFactory().constructCollectionType(List.class, type);
                list = mapper.readValue(file, listType);
            }
    return list;
        } catch (IOException e) {
            System.out.println("Error to load the file: " + e.getMessage());
            return new ArrayList<>();
        }
    }


}
