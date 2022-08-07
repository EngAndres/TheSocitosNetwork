/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author jerar
 */
public class UserModel extends ModelTemplate {
    //Attributes
    private String                name;
    private String                nickname;
    private String                password;
    private String                birthday;
    private String                description;
    private String                email;
    private LocationModel         location;
    private TimeZoneModel         timezone;
    private ArrayList<HobbyModel> hobbies;

    
    // Constructors
    /**
     * 
     * @param id
     * @param name 
     */
    public UserModel(int id, String name){
        super(id);
        this.name = name;
    }

    /**
     * 
     * @param id
     * @param password
     * @param descripcion
     * @param location
     * @param timezone
     * @param hobbies 
     */
    public UserModel(int id, String password, String descripcion, LocationModel location, 
                     TimeZoneModel timezone, ArrayList<HobbyModel> hobbies){
        super(id);
        this.password    = password;
        this.description = descripcion;
        this.location    = location;
        this.timezone    = timezone;
        this.hobbies     = hobbies;
    }
 
    /**
     * 
     * @param id
     * @param name
     * @param nickname
     * @param password
     * @param birthday
     * @param descripcion
     * @param email
     * @param location
     * @param timezone
     * @param hobbies 
     */
    public UserModel(int id, String name, String nickname, String password, String birthday, 
                     String descripcion, String email, LocationModel location, TimeZoneModel timezone, 
                     ArrayList<HobbyModel> hobbies){
        super(id);
        this.name        = name;
        this.nickname    = nickname;
        this.password    = password;
        this.birthday    = birthday;
        this.description = descripcion;
        this.email       = email;
        this.location    = location;
        this.timezone    = timezone;
        this.hobbies     = hobbies;
        
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
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the location
     */
    public LocationModel getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(LocationModel location) {
        this.location = location;
    }

    /**
     * @return the timezone
     */
    public TimeZoneModel getTimeZone() {
        return timezone;
    }

    /**
     * @param timezone the timezone to set
     */
    public void setZone(TimeZoneModel timezone) {
        this.timezone = timezone;
    }

    /**
     * @return the hobbies
     */
    public ArrayList<HobbyModel> getHobbies() {
        return hobbies;
    }

    /**
     * @param hobbies the hobbies to set
     */
    public void setHobbies(ArrayList<HobbyModel> hobbies) {
        this.hobbies = hobbies;
    }
}