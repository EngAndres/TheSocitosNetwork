/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.ArrayList;

/**
 *
 * @author jerar
 */
public interface DAOTemplate<T> {
    /**
     * 
     * @return 
     */
    public ArrayList<T> getAll();
    
    /**
     * 
     * @param id
     * @return 
     */
    public T getOne(int id);
    
    /**
     * 
     * @param model 
     */
    public void insert(T model);
    
    /**
     * 
     * @param id
     * @param model 
     */
    public void update(int id, T model);
    
    /**
     * 
     * @param id 
     */
    public void delete(int id);
}