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

import model.LocationModel;

/**
 *
 * @author jerar
 */
public class LocationDAO implements DAOTemplate<LocationModel> {
    // DB Connection
    Connection conn = ConnectionDB.getConnection();
    
    @Override
    public ArrayList<LocationModel> getAll(){
        ArrayList<LocationModel> locations = new ArrayList<>();
        try{
            String query        = "SELECT id_ubicacion AS id, nombre FROM ubicacion;";
            Statement statement = conn.createStatement();
            ResultSet result    = statement.executeQuery( query );
            
            int count = 0;
            while(result.next()){
                LocationModel location = new LocationModel(result.getInt(1), result.getString(2));
                locations.add( location );
                count++;
            }
            System.out.println(count + " locations retrieved");
        }
        catch(SQLException e){}
        return locations;
    }
    
    /**
     * 
     * @param country
     * @return 
     */
    public ArrayList<LocationModel> getAllByCountry(int country){
        ArrayList<LocationModel> locations = new ArrayList<>();
        try{
            String query = "SELECT id_ubicacion AS id, nombre FROM ubicacion WHERE pais_fk=?;";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, country);
            ResultSet result = statement.executeQuery();
            
            int count = 0;
            while (result.next()) {
                LocationModel location = new LocationModel(result.getInt(1), result.getString(2));
                locations.add( location );
                count++;
            }
            System.out.println(count + " locations retrieved");
        }
        catch(SQLException e){}
        return locations;
    }
    
    @Override
    public LocationModel getOne(int id) {
        LocationModel location = null;
        try {
            String query = "SELECT nombre, pais_fk FROM ubicacion WHERE id=?;";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                location = new LocationModel(id, result.getString(1), result.getInt(2));
                break;
            }
        } 
        catch (SQLException ex) {}
        return location;
    }
    
    @Override
    public void insert(LocationModel location){
        try {
            String query = "INSERT ubicacion(nombre, pais_fk) VALUES (?,?);";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, location.getName());
            statement.setInt(2, location.getCountryFK());
            int rowsInserted = statement.executeUpdate();
            if(rowsInserted > 0){
                System.out.println("Location added. Rows: " + rowsInserted);
            }
        }
        catch(SQLException e){}
    }
    
    @Override
    public void update(int id, LocationModel location){
        try {
            String query = "UPDATE ubicacion SET nombre=? WHERE id_ubicacion=?;";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, location.getName());
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            if(rowsUpdated > 0){
                System.out.println("Location updated. Rows: " + rowsUpdated);
            }
        }
        catch(SQLException e){}
    }
           
    @Override
    public void delete(int id){
        try {
            String query = "DELETE FROM ubicacion WHERE id_ubicacion=?;";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if(rowsDeleted > 0){
                System.out.println("Location deleted. Rows: " + rowsDeleted);
            }
        }
        catch(SQLException e){}
    }
}