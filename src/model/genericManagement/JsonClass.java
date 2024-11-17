package model.genericManagement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import com.fasterxml.jackson.databind.jsontype.impl.TypeNameIdResolver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonClass {
    private static final ObjectMapper mapper = new ObjectMapper();
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
    public static <T> void saveList(List<T> lista, String fileString) {
        try {
            mapper.writeValue(new File(fileString), lista);
            System.out.println("List saved in the file " + fileString);
        } catch (IOException e) {
            System.out.println("Error to save the file: " + e.getMessage());
        }
    }

    // LOAD LIST TO THE JSON FILE
    public static <T> List<T> loadList(String fileString, Class<T> type) {
        try {
            List<T> lista = mapper.readValue(new File(fileString),
                    mapper.getTypeFactory().constructCollectionType(List.class, type));
            System.out.println("Lista loaded by the file: " + fileString);
            return lista;
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

    // Método para cargar lista de propiedades
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
