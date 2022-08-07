/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author jerar
 */
public class LocationModel extends ModelTemplate {
    // Attributes
    private String name;
    private int countryFK;
    
    /**
     * 
     * @param id
     * @param name 
     */
    public LocationModel(int id, String name){
        super(id);
        this.name = name;
    }
    
    /**
     * 
     * @param id
     * @param name
     * @param countryFK 
     */
    public LocationModel(int id, String name, int countryFK){
        super(id);
        this.name = name;
        this.countryFK = countryFK;
    }

    
    // Getters & Setters
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the countryFK
     */
    public int getCountryFK() {
        return countryFK;
    }

    /**
     * @param countryFK the countryFK to set
     */
    public void setCountryFK(int countryFK) {
        this.countryFK = countryFK;
    }
    
    @Override
    public String toString(){
        return this.name;
    }
}