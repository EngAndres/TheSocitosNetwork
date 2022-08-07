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

import model.CountryModel;

/**
 *
 * @author jerar
 */
public class CountryDAO implements DAOTemplate<CountryModel> {
    // DB Connection
    Connection conn = ConnectionDB.getConnection();
    
    @Override
    public ArrayList<CountryModel> getAll(){
        ArrayList<CountryModel> countries = new ArrayList<>();
        try{
            String query        = "SELECT id_pais AS id, nombre FROM pais;";
            Statement statement = conn.createStatement();
            ResultSet result    = statement.executeQuery( query );
            
            int count = 0;
            while(result.next()){
                CountryModel country = new CountryModel(result.getInt(1), result.getString(2));
                countries.add( country );
                count++;
            }
            System.out.println(count + " countries retrieved");
        }
        catch(SQLException e){}
        return countries;
    }
    
    @Override
    public CountryModel getOne(int id) {
        CountryModel country = null;
        try {
            String sql = "SELECT nombre FROM pais WHERE id_pais=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                country = new CountryModel(id, result.getString(1));
                break;
            }
        } 
        catch (SQLException ex) {}
        return country;
    }
    
    @Override
    public void insert(CountryModel country){
        try {
            String sql = "INSERT pais(nombre) VALUES (?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, country.getName());
            int rowsInserted = statement.executeUpdate();
            if(rowsInserted > 0){
                System.out.println("Country added. Rows: " + rowsInserted);
            }
        }
        catch(SQLException e){}
    }
    
    @Override
    public void update(int id, CountryModel country){
        try {
            String sql = "UPDATE pais SET nombre=? WHERE id_pais=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, country.getName());
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            if(rowsUpdated > 0){
                System.out.println("Country updated. Rows: " + rowsUpdated);
            }
        }
        catch(SQLException e){}
    }
           
    @Override
    public void delete(int id){
        try {
            String sql = "DELETE FROM pais WHERE id_pais=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if(rowsDeleted > 0){
                System.out.println("Country deleted. Rows: " + rowsDeleted);
            }
        }
        catch(SQLException e){}
    }
}