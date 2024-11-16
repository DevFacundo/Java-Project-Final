package model.genericManagement;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonClass {
    private static final ObjectMapper mapper = new ObjectMapper();

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

}
