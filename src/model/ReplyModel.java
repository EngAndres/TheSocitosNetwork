/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author jerar
 */
public class ReplyModel extends NewsModel {
    // Attributes
    private int newsFK;
    
    /**
     * 
     * @param id
     * @param post
     * @param date
     * @param userFK
     * @param newsFK 
     */
    public ReplyModel(int id, String post, String date, int userFK, int newsFK){
        super(id, post, date, userFK);
        this.newsFK = newsFK;
    }

    // Getters & Setters
    /**
     * @return the newsFK
     */
    public int getNewsFK() {
        return newsFK;
    }

    /**
     * @param newsFK the newsFK to set
     */
    public void setNewsFK(int newsFK) {
        this.newsFK = newsFK;
    }
}