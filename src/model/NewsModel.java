/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author jerar
 */
public class NewsModel extends ModelTemplate {
    //Attributes
    private String post;
    private String date;
    private int userFK;
    
    /**
     * 
     * @param id
     * @param post
     * @param date
     * @param userFK 
     */
    public NewsModel(int id, String post, String date, int userFK){
        super(id);
        this.post = post;
        this.date = date;
        this.userFK = userFK;
    }

    // Getters & Setters
    /**
     * @return the post
     */
    public String getPost() {
        return post;
    }

    /**
     * @param post the post to set
     */
    public void setPost(String post) {
        this.post = post;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the userFK
     */
    public int getUserFK() {
        return userFK;
    }

    /**
     * @param userFK the userFK to set
     */
    public void setUserFK(int userFK) {
        this.userFK = userFK;
    }
}
