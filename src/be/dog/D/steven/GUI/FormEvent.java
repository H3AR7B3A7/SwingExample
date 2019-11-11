package be.dog.D.steven.GUI;

import java.util.EventObject;

public class FormEvent extends EventObject {

    // Fields
    private String name;
    private String occupation;
    private int ageCategory;
    private String empCat;
    private String taxId;
    private boolean euCitizen;
    private String gender;

    // Constructor
    FormEvent(Object source, String name, String occupation, int ageCat, String empCat,
              String taxId, boolean euCitizen, String gender) {
        super(source);
        this.name = name;
        this.occupation = occupation;
        this.ageCategory = ageCat;
        this.empCat = empCat;
        this.taxId = taxId;
        this.euCitizen = euCitizen;
        this.gender = gender;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getOccupation() {
        return occupation;
    }

    public int getAgeCategory(){
        return ageCategory;
    }

    public String getEmpCat() { return empCat; }

    public String getGender() { return gender; }


    // UNUSED
    public String getTaxId() { return taxId; }

    public boolean isEuCitizen() { return euCitizen; }

}
