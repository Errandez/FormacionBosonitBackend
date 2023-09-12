package org.Ejercicio_1;

import org.Ejercicio_1.Person;
import org.Ejercicio_1.Persons;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class Main {

    public static Persons lectura() throws IOException, InvalidLineFormatException, Exception{
        boolean b = false;
        try {
            Scanner lectura = new Scanner(System.in);
            String archivo = "";
            if(!b) {
                System.out.println("Inserte el nombre del archivo csv: ");
                String archivoaux = (String) lectura.nextLine();
                String[] aux = archivoaux.split("/");

                String[] nombre = aux[aux.length-1].split("\\.");

                if(nombre[1].equalsIgnoreCase("csv")) {
                    b=true;
                    archivo=archivoaux;
                }
            }
            Path p = new File(archivo).toPath();
            if(!Files.exists(p)) {
                throw new Exception("No existe");
            }
            BufferedReader reader = Files.newBufferedReader(p);
            Persons persons = new Persons();
            String read = reader.readLine();
            while(read != null && !read.isBlank()) {
                String partes[] = read.split(":");
                Person person = null;
                int contador = 0;
                for(int i=0; i<read.length();i++) {
                    if(read.charAt(i) == ':') {
                        contador++;
                    }
                }

                if(contador != 2) {
                    throw new InvalidLineFormatException("No posee los tres campos.");
                }
                if(partes[0].isBlank()) {
                    throw new InvalidLineFormatException("No posee el nombre de la persona.");
                }

                if(partes[0] != "" && partes[0] != null) {
                    person = new Person(partes[0]);
                }

                if(partes.length>2 && !partes[1].isBlank() && partes[1] != null) {
                    person.setTown(partes[1]);
                }

                if(partes.length>2 && !partes[2].isBlank() && partes[2] != null) {
                    Integer a = Integer.parseInt(partes[2]);
                    if(a>0) {
                        person.setAge(a);
                    }
                }
                read= reader.readLine();
                persons.addPerson(person);
            }
            return persons;
        }catch(IOException e) {
            e.printStackTrace();
            throw e;
        }catch (InvalidLineFormatException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void listar(Persons persons) {
        System.out.println("Lista: ");
        System.out.println(" ");
        persons.showPersons();
        System.out.println("----------------------");
        //Apartado A.
        System.out.println("A. Invocar a la función con un filtro que se conserve únicamente las personas menores de 25 años.");
        System.out.println(" ");
        List<Person> apartadoa = persons.getPersonsList().stream().filter(a->a.getAge()!=null && a.getAge()<=25 && a.getAge()>0).toList();
        for(Person p : apartadoa) {
            System.out.println("Nombre: " + p.getName() + " Ciudad: " + p.getTown() + " Edad: " + p.getAge());
        }
        System.out.println("----------------------");

        //Apartado B.
        System.out.println("B. Invocar a la función con un filtro que elimine a las personas cuyo nombre empiece con la letra A.");
        System.out.println(" ");
        List<Person> apartadoB = persons.getPersonsList().stream().filter(b->b.getName().charAt(0)!='A'
                && b.getName().charAt(0)!='Á' && b.getName().charAt(0)!='a' && b.getName().charAt(0)!='á').toList();
        for(Person p : apartadoB) {
            System.out.print("Nombre: " + p.getName());
            if(p.getTown()!=null) {
                System.out.print(" Ciudad: " + p.getTown());
            }else {
                System.out.print(" Ciudad: unknown");
            }

            if(p.getAge()!=null){
                System.out.println(" Edad: " + p.getAge());
            }else {
                System.out.println(" Edad: unknown");
            }

        }
        System.out.println("----------------------");

        //Apartado C.
        System.out.println("C. Usando el resultado del apartado A, crear un stream a partir de la lista y obtener el primer elemento cuya"
                + " ciudad sea Madrid.");
        System.out.println(" ");
        Optional<Person> apartadoC = apartadoa.stream().filter(c -> c.getTown().equalsIgnoreCase("Madrid")).findFirst();
        if(apartadoC!=null && !apartadoC.isEmpty()) {
            Person aux = apartadoC.get();
            System.out.println("Nombre: " + aux.getName() + " Ciudad: " + aux.getTown() + " Edad: " + aux.getAge());
        }else {
            System.out.println("No hay ninguna persona en Madrid.");
        }
        System.out.println("----------------------");

        //Apartado D.
        System.out.println("D. Usando el resultado del apartado A, crear un Stream a partir de las lista y obtener el primer elemento"
                + " cuya ciudad sea Barcelona.");
        System.out.println(" ");
        Optional<Person> apartadoD = apartadoa.stream().filter(c ->c.getTown()!=null && c.getTown().equalsIgnoreCase("Barcelona")).findFirst();
        if(apartadoD!=null && !apartadoD.isEmpty()) {
            Person aux = apartadoD.get();
            System.out.println("Nombre: " + aux.getName() + " Ciudad: " + aux.getTown() + " Edad: " + aux.getAge());
        }else {
            System.out.println("No hay ninguna persona en Barcelona.");
        }

    }

    public static void main(String[] args) throws IOException, InvalidLineFormatException, Exception{
        try {
            Persons persons = lectura();
            listar(persons);
        }catch(IOException e) {
            e.printStackTrace();
        }catch(InvalidLineFormatException ex) {
            ex.printStackTrace();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
