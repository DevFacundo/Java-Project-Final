package model.genericManagement;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.clients.Client;
import model.interfaces.Identifiable;
import model.properties.Property;
import model.rents.Rent;
import model.sales.Sale;

import java.io.File;
import java.io.IOException;
import java.util.*;

//THIS IS A GENERIC CLASS TO MANAGEMENT THE CLASS
// YOU CAN ADD, REMOVE, EDIT, AND SHOW ALL THE ELEMENTS FOR EACH SUBCLASS
public class GenericManagement<T extends Identifiable> {

    private List<T> elements;
    private String fileName;
    private ObjectMapper mapper;

    public GenericManagement(String fileName) {
        this.elements = new LinkedList<>();
        this.fileName = fileName;
        this.mapper = new ObjectMapper();
        File file = new File(fileName);
        loadToFile();
    }

    public Boolean addElement (T e)
    {
        if (e != null && !elements.contains(e)) {
            Integer newId = getLastId() + 1; // Incrementar el último ID
            e.setId(newId);
            elements.add(e);
            saveToFile();
            return true;
        }
        return false;
    }

    public Boolean deleteElement (T e)
    {
        boolean removed = elements.remove(e);
        if (removed) {
            saveToFile();
        }
        return removed;
    }

    public T getElementByID(Integer id) {
        for (T e : elements) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        System.out.println("Element with ID " + id + " not found");
        return null;
    }

    public void showElements ()
    {
       for (T e:elements)
       {
           System.out.println(e);
       }
    }

    // LOAD JSON FILE IN A LIST
    public void loadToFile() {
        try {
            File archivo = new File(fileName);
            if (archivo.exists()) {
                elements = mapper.readValue(archivo, new TypeReference<List<T>>() {});
            }
            else {
                System.out.println("The file it doesnt exists. Inicializing with an empty list");
            }
        } catch (IOException e) {
            System.err.println("Error to load the file" + e.getMessage());
            e.printStackTrace();
        } catch (SecurityException e) {
            System.err.println("You dont have permission " + e.getMessage());
            e.printStackTrace();
        }
    }


    // SAVE LIST IN JSON FILE
    public void saveToFile() {
        try {
            mapper.writeValue(new File(fileName), elements);
        } catch (IOException e) {
            System.err.println("Error to save the file: " + e.getMessage());
            e.printStackTrace();
         }
    }

    public Integer getLastId() {
        if (!elements.isEmpty()) {
            T lastElement= elements.get(elements.size() - 1);
            return lastElement.getId();
        }
        return 0;
    }
}
/*
    //SEARCH THE ELEMENT IN THE COLLECTION FOR THE ID AND RETURN IT
    public T getElementByID (Integer id)
    {
        for (T e: elements)
        {
            switch(e.getClass().getName())
            {
                case "Property":
                    if (((Property) e).getId().equals(id))
                    {
                        return e;
                    }else {
                        System.out.println("The Id of The property it doesnt exists");
                    }
                    break;
                case "Client":
                    if (((Client) e).getId().equals(id))
                    {
                        return e;
                    }else {
                        System.out.println("The Id of The Client it doesnt exists");
                    }
                    break;
                case "Rent":
                    if (((Rent) e).getId().equals(id))
                    {
                        return e;
                    }else {
                        System.out.println("The Id of The Rents it doesnt exists");
                    }
                    break;
                case "Sale":
                    if (((Sale) e).getId().equals(id))
                    {
                        return e;
                    }else {
                        System.out.println("The Id of The Sales it doesnt exists");
                    }
                    break;

                default:
                    System.out.println("Incorrect answer");
                    break;
            }
        }
        return null;
        }
*/