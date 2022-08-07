/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author jerar
 */
public class TimeZoneModel extends ModelTemplate {
    //Attributes
    private String name;
    private int delta;
    
    /**
     * 
     * @param id
     * @param name
     * @param delta 
     */
    public TimeZoneModel(int id, String name, int delta){
        super(id);
        this.name = name;
        this.delta = delta;
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
     * @return the delta
     */
    public int getDelta() {
        return delta;
    }

    /**
     * @param delta the delta to set
     */
    public void setDelta(int delta) {
        this.delta = delta;
    }
    
    @Override
    public String toString(){
        return this.name + (this.delta==50 ? "" : "(UTC " + this.delta + ")");
    }
}