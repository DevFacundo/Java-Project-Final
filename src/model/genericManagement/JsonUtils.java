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
/*
    static {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // Configuración para la serialización de tipos polimórficos de forma correcta
        mapper.activateDefaultTyping(
                mapper.getPolymorphicTypeValidator(),
                ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE
        );
    }



 */


    // SAVE LIST IN A JSON FILE
    public static <T> void saveList(List<T> list, String fileString,Class<T> type) {
        try {
            mapper.writerFor(mapper.getTypeFactory()
                            .constructCollectionType(List.class, type))
                    .writeValue(new File(fileString), list);
           // mapper.registerModule(new JavaTimeModule());
           // mapper.writeValue(new File(fileString), lista);
            System.out.println("List saved in the file " + fileString);
        } catch (IOException e) {
            System.out.println("Error to save the file: " + e.getMessage());
        }
    }

    // LOAD LIST TO THE JSON FILE
    public static <T> List<T> loadList(String fileString, Class<T> type) {
        try {
            // Inicializamos una lista vacía para evitar retornos de null
            List<T> list = new ArrayList<>();

            // Intentamos cargar los datos desde el archivo
            File file = new File(fileString);
            if (file.exists() && file.length() > 0) {
                // Si el archivo tiene datos, los cargamos
                JavaType listType = mapper.getTypeFactory().constructCollectionType(List.class, type);
                list = mapper.readValue(file, listType);
            }

            // Si el archivo está vacío o no existe, la lista se queda vacía
    return list;
        } catch (IOException e) {
            System.out.println("Error to load the file: " + e.getMessage());
            return null;
        }
    }

 /*
public static void saveList(List<?> lista, String fileString) {
    try {
        // Antes de guardar, vamos a imprimir el JSON para debug
        String jsonContent = mapper.writeValueAsString(lista);
        System.out.println("JSON a guardar:");
        System.out.println(jsonContent);

        // Guardar al archivo
        mapper.writeValue(new File(fileString), lista);
        System.out.println("Lista guardada en el archivo " + fileString);
    } catch (IOException e) {
        System.out.println("Error al guardar el archivo: " + e.getMessage());
        e.printStackTrace();
    }
}


    public static <T> List<T> loadList(String fileString, Class<T> type) {
        try {
            JavaType listType = mapper.getTypeFactory().constructCollectionType(List.class, type);
            List<T> lista = mapper.readValue(new File(fileString), listType);
            System.out.println("Lista cargada desde el archivo: " + fileString);
            return lista;
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static <T> void printJson(T object) {
        try {
            String json = mapper.writeValueAsString(object);
            System.out.println("JSON generado:");
            System.out.println(json);
        } catch (JsonProcessingException e) {
            System.out.println("Error al generar JSON: " + e.getMessage());
        }
    }


  */
}
