package be.dog.D.steven.controller;

import be.dog.D.steven.GUI.FormEvent;
import be.dog.D.steven.model.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Controller {

    private DataBase db = new DataBase();

    public List<Person> getPeople(){
        return db.getPeople();
    }

    public void removePerson(int index){
        db.removePerson(index);
    }

    public void addPerson(FormEvent ev){
        String name = ev.getName();
        String occupation = ev.getOccupation();
        int ageCatId = ev.getAgeCategory();
        String empCat = ev.getEmpCat();
        boolean isEu = ev.isEuCitizen();
        String taxId = ev.getTaxId();
        String gender = ev.getGender();

        AgeCategory ageCategory;

        switch(ageCatId){
        case 0:
            ageCategory = AgeCategory.CHILD;
            break;
        case 1:
            ageCategory = AgeCategory.ADULT;
            break;
        case 2:
            ageCategory = AgeCategory.SENIOR;
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + ageCatId);
        }

        EmploymentCategory empCategory;
        switch (empCat) {
            case "employed":
                empCategory = EmploymentCategory.EMPLOYED;
                break;
            case "self-employed":
                empCategory = EmploymentCategory.SELF_EMPLOYED;
                break;
            case "unemployed":
                empCategory = EmploymentCategory.UNEMPLOYED;
                break;
            default:
                empCategory = EmploymentCategory.OTHER;
                System.err.println(empCat); // TODO : handle this somehow
                break;
        }

        Gender genderCat;

        if (gender.equals("male")){
            genderCat = Gender.MALE;
        } else {
            genderCat = Gender.FEMALE;
        }

        Person person = new Person(name,occupation,ageCategory,empCategory,taxId,isEu,genderCat);

        db.addPerson(person);
    }

    public void saveToFile(File file) throws IOException {
        db.saveToFile(file);
    }
    public void loadFromFile(File file) throws IOException {
        db.loadFromFile(file);
    }
}
