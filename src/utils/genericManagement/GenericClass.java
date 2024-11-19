package utils.genericManagement;

import model.exceptions.DuplicateElementException;
import java.util.LinkedList;
import java.util.List;

public class GenericClass<T> {
    private List<T> elements;
    public GenericClass() {
        this.elements = new LinkedList<>();
    }

    public GenericClass(List<T> elements) {
        this.elements = elements;
    }

    public void addElement(T e) throws DuplicateElementException {
        if(elements.contains(e))
        {
            throw new DuplicateElementException("The element is alredy exists");
        }
        elements.add(e);
    }
///MANEJAR EXCPETION NUEVA DE NO PODER ELIMINAR SI ESTA EN PROCESO DE VENTA O RENTA
    public void deleteElement(T e) {
        elements.remove(e);
    }

    public void modifyElement(T oldElement, T newElement) {
        Integer i = elements.indexOf(oldElement);
        if (i != -1) {
            elements.set(i, newElement);
        } else {
            System.out.println("Object dont found it to modify");
        }
    }

    public void showElements() {
        if (elements.isEmpty()) {
            System.out.println("The list is empty");
        } else {
            for (T e : elements) {
                System.out.println(e);
            }
        }
    }
    public Boolean isEmpty()
    {
        return elements.isEmpty();
    }

    public List<T> returnList() {
        return elements;
    }

    public T getLastObject () {
        if (!elements.isEmpty()) {
           return elements.getLast();
        } else
            return null;
        }
}