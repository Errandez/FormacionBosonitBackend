package org.Ejercicio_1;

import java.util.ArrayList;
import java.util.List;

public class Persons {
    private List<Person> persons = new ArrayList<Person>();
    public void addPerson(Person p) {
        persons.add(p);
    }
    public void showPersons() {
        for(Person p : persons) {
            System.out.println("Nombre: " + p.getName() + " Ciudad: " + p.getTown() + " Edad: " + p.getAge());
        }
    }
    public List<Person> getPersonsList(){
        return this.persons;
    }
}
