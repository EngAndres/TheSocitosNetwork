/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author jerar
 */
public class HobbyModel extends ModelTemplate {
    //Attributes
    private String name;
    
    /**
     * 
     * @param id
     * @param name 
     */
    public HobbyModel(int id, String name){
        super(id);
        this.name = name;
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
    
    @Override
    public String toString(){
        return this.name;
    }
}
