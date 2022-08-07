/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import java.util.ArrayList;

import model.HobbyModel;

/**
 *
 * @author jerar
 */
public class HobbyDAO implements DAOTemplate<HobbyModel> {
    // DB Connection
    Connection conn = ConnectionDB.getConnection();
    
    
    @Override
    public ArrayList<HobbyModel> getAll(){
        ArrayList<HobbyModel> countries = new ArrayList<>();
        try{
            String query        = "SELECT id_hobby AS id, nombre FROM hobby;";
            Statement statement = conn.createStatement();
            ResultSet result    = statement.executeQuery( query );
            
            int count = 0;
            while(result.next()){
                HobbyModel hobby = new HobbyModel(result.getInt(1), result.getString(2));
                countries.add( hobby );
                count++;
            }
            System.out.println(count + " hobbies retrieved");
        }
        catch(SQLException e){}
        return countries;
    }
    
    @Override
    public HobbyModel getOne(int id) {
        HobbyModel hobby = null;
        try {
            String query = "SELECT nombre FROM hobby WHERE id_hobby=?;";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                hobby = new HobbyModel(id, result.getString(1));
                break;
            }
        } 
        catch (SQLException ex) {}
        return hobby;
    }
    
    @Override
    public void insert(HobbyModel hobby){
        try {
            String query = "INSERT hobby(nombre) VALUES (?);";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, hobby.getName());
            int rowsInserted = statement.executeUpdate();
            if(rowsInserted > 0){
                System.out.println("Hobby added. Rows: " + rowsInserted);
            }
        }
        catch(SQLException e){}
    }
    
    @Override
    public void update(int id, HobbyModel hobby){
        try {
            String query = "UPDATE hobby SET nombre=? WHERE id_hobby=?;";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, hobby.getName());
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            if(rowsUpdated > 0){
                System.out.println("Hobby updated. Rows: " + rowsUpdated);
            }
        }
        catch(SQLException e){}
    }
           
    @Override
    public void delete(int id){
        try {
            String query = "DELETE FROM hobby WHERE id_hobby=?;";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if(rowsDeleted > 0){
                System.out.println("Hobby deleted. Rows: " + rowsDeleted);
            }
        }
        catch(SQLException e){}
    }
}