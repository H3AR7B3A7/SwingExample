package be.dog.D.steven.model;

import java.io.*;
import java.util.*;

public class DataBase {

    // Field
    private List<Person> people;

    // Constructor -- Holding LinkedList = preferable over ArrayList when data gets moved a lot,
    // so it does not need to re-organize the list
    public DataBase() {
        people = new LinkedList<>();
    }

    // Methods
    public void addPerson(Person person){
        people.add(person);
    }

    public void removePerson(int index){
        people.remove(index);
    }

    public List<Person> getPeople(){
        return Collections.unmodifiableList(people);
    }

    public void saveToFile(File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        Person[] persons = people.toArray(new Person[0]);

        oos.writeObject(persons);

        oos.close();
    }

    public void loadFromFile (File file) throws IOException{
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        try {
            Person[] persons = (Person[])ois.readObject();
            people.clear(); // clears table before importing
            people.addAll(Arrays.asList(persons));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ois.close();
    }
}
