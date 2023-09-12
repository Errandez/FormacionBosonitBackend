package org.Ejercicio_1;

public class Person {
    private String name;
    private String town;
    private Integer age;


    public Person(String n){
        this.name = n;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String n) {
        this.name = n;
    }

    public String getTown() {
        return this.town;
    }

    public void setTown(String t) {
        this.town = t;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(int a) {
        this.age = a;
    }

}
