package model.genericManagement;

import java.util.LinkedList;
import java.util.List;

public class GenericClass<T> {
    private List<T> elements;
    private ClassValue<T> value;
    public GenericClass() {
        this.elements = new LinkedList<>();
    }

    public GenericClass(List<T> elements) {
        this.elements = elements;
    }

    public void addElement(T e) {
        elements.add(e);
    }

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