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

import model.TimeZoneModel;

/**
 *
 * @author jerar
 */
public class TimeZoneDAO implements DAOTemplate<TimeZoneModel> {
    // DB Connection
    Connection conn = ConnectionDB.getConnection();
    
    @Override
    public ArrayList<TimeZoneModel> getAll(){
        ArrayList<TimeZoneModel> timezones = new ArrayList<>();
        try{
            String query        = "SELECT id_zona AS id, nombre, variacion FROM zona_horaria;";
            Statement statement = conn.createStatement();
            ResultSet result    = statement.executeQuery( query );
            
            int count = 0;
            while(result.next()){
                TimeZoneModel timezone = new TimeZoneModel(result.getInt(1), result.getString(2), result.getInt(3));
                timezones.add( timezone );
                count++;
            }
            System.out.println(count + " timezones retrieved");
        }
        catch(SQLException e){}
        return timezones;
    }
    
    @Override
    public TimeZoneModel getOne(int id) {
        TimeZoneModel timezone = null;
        try {
            String query = "SELECT nombre, variacion FROM zona_horario WHERE id_zona=?;";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                timezone = new TimeZoneModel(id, result.getString(1), result.getInt(2));
                break;
            }
        } 
        catch (SQLException ex) {}
        return timezone;
    }
    
    @Override
    public void insert(TimeZoneModel timezone){
        try {
            String query = "INSERT zona_horaria(nombre, variacion) VALUES (?,?);";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, timezone.getName());
            statement.setInt(2, timezone.getDelta());
            int rowsInserted = statement.executeUpdate();
            if(rowsInserted > 0){
                System.out.println("Timezone added. Rows: " + rowsInserted);
            }
        }
        catch(SQLException e){}
    }
    
    @Override
    public void update(int id, TimeZoneModel timezone){
        try {
            String query = "UPDATE zona_horaria SET nombre=?, variacion=? WHERE id_zona=?;";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, timezone.getName());
            statement.setInt(2, timezone.getDelta());
            statement.setInt(3, id);
            int rowsUpdated = statement.executeUpdate();
            if(rowsUpdated > 0){
                System.out.println("Timezone updated. Rows: " + rowsUpdated);
            }
        }
        catch(SQLException e){}
    }
           
    @Override
    public void delete(int id){
        try {
            String query = "DELETE FROM zona_horaria WHERE id_zona=?;";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if(rowsDeleted > 0){
                System.out.println("Timezone deleted. Rows: " + rowsDeleted);
            }
        }
        catch(SQLException e){}
    }
}