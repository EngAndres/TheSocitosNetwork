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
import model.NewsModel;


/**
 *
 * @author jerar
 */
public class NewsDAO implements DAOTemplate<NewsModel> {
    // DB Connection
    Connection conn = ConnectionDB.getConnection();
    
    @Override
    public ArrayList<NewsModel> getAll(){
        ArrayList<NewsModel> news = new ArrayList<>();
        try{
            String query        = """
                                    SELECT novedad.id_novedad, novedad.contenido, novedad.fecha, usuario.nombre 
                                    FROM novedad 
                                    JOIN usuario ON novedad.usuario_fk=usuario.id_usuario;
                                  """;
            Statement statement = conn.createStatement();
            ResultSet result    = statement.executeQuery( query );
            
            int count = 0;
            while(result.next()){
                NewsModel new_ = new NewsModel( result.getInt(1), result.getString(2), result.getString(3), result.getString(4) );
                news.add( new_ );
                count++;
            }
            System.out.println(count + " news retrieved");
        }
        catch(SQLException e){}
        return news;
    }
    
    
    /**
     * 
     * @param userId
     * @return 
     */
    public ArrayList<NewsModel> getByUser(int userId){
        ArrayList<NewsModel> news = new ArrayList<>();
        try{
            String query        = """
                                    SELECT id_novedad AS id, contenido, fecha 
                                    FROM novedad
                                    WHERE usuario_fk=?;
                                  """;
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            int count = 0;
            while(result.next()){
                NewsModel new_ = new NewsModel( result.getInt(1), result.getString(2), result.getString(3), result.getString(4) );
                news.add( new_ );
                count++;
            }
            System.out.println(count + " news retrieved");
        }
        catch(SQLException e){}
        return news;
    }
    
    
    /**
     * 
     * @param userId
     * @return 
     */
    public ArrayList<NewsModel> getByOtherUsers(int userId){
        ArrayList<NewsModel> news = new ArrayList<>();
        try{
            String query        = """
                                    SELECT id_novedad AS id, contenido, fecha 
                                    FROM novedad
                                    WHERE usuario_fk!=?;
                                  """;
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            int count = 0;
            while(result.next()){
                NewsModel new_ = new NewsModel( result.getInt(1), result.getString(2), result.getString(3), result.getString(4) );
                news.add( new_ );
                count++;
            }
            System.out.println(count + " news retrieved");
        }
        catch(SQLException e){}
        return news;
    }
    
    
    /**
     * 
     * @param keyword
     * @return 
     */
    public ArrayList<NewsModel> getByKeyword(String keyword){
        ArrayList<NewsModel> news = new ArrayList<>();
        try{
            String query        = """
                                    SELECT novedad.id_novedad AS id, novedad.contenido, 
                                    	   novedad.fecha, usuario.nombre AS nombre
                                    FROM novedad
                                    JOIN usuario ON novedad.usuario_fk=usuario.id_usuario
                                    WHERE novedad.contenido LIKE "%?%"
                                    ORDER BY novedad.fecha
                                    LIMIT 10;
                                  """;
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, keyword);
            ResultSet result = statement.executeQuery();
            int count = 0;
            while(result.next()){
                NewsModel new_ = new NewsModel( result.getInt(1), result.getString(2), result.getString(3), result.getString(4) );
                news.add( new_ );
                count++;
            }
            System.out.println(count + " news retrieved");
        }
        catch(SQLException e){}
        return news;
    }
    
    
    /**
     * 
     * @param startDate
     * @param endDate
     * @return 
     */
    public ArrayList<NewsModel> getByRangeDates(String startDate, String endDate){
        ArrayList<NewsModel> news = new ArrayList<>();
        try{
            String query        = """
                                    SELECT novedad.id_novedad AS id, novedad.contenido, 
                                    	   novedad.fecha, usuario.nombre AS nombre
                                    FROM novedad
                                    JOIN usuario ON novedad.usuario_fk=usuario.id_usuario
                                    WHERE novedad.fecha>=? AND novedad.fecha<=?;
                                  """;
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, startDate);
            statement.setString(2, endDate);
            ResultSet result = statement.executeQuery();
            int count = 0;
            while(result.next()){
                NewsModel new_ = new NewsModel( result.getInt(1), result.getString(2), result.getString(3), result.getString(4) );
                news.add( new_ );
                count++;
            }
            System.out.println(count + " news retrieved");
        }
        catch(SQLException e){}
        return news;
    }
    
    
    @Override
    public NewsModel getOne(int id) {
        NewsModel news = null;
        try {
            String sql = """
                            SELECT novedad.contenido, novedad.fecha, usuario.nombre 
                            FROM novedad 
                            JOIN usuario ON novedad.usuario_fk=usuario.id_usuario
                            WHERE novedad.id_novedad=?;
                         """;
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                news = new NewsModel(id, result.getString(1), result.getString(2), result.getString(3));
                break;
            }
        } 
        catch (SQLException ex) {}
        return news;
    }
    
    
    @Override
    public void insert(NewsModel news){
        try {
            String sql = "INSERT novedad(contenido, fecha, usuario_fk) VALUES (?,?,?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, news.getPost());
            statement.setString(2, news.getDate());
            statement.setInt(3, news.getUserFK());
            int rowsInserted = statement.executeUpdate();
            if(rowsInserted > 0){
                System.out.println("News added. Rows: " + rowsInserted);
            }
        }
        catch(SQLException e){}
    }
    
    
    @Override
    public void update(int id, NewsModel news){
        try {
            String sql = "UPDATE novedades SET contenido=? WHERE id_pais=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, news.getPost());
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            if(rowsUpdated > 0){
                System.out.println("News updated. Rows: " + rowsUpdated);
            }
        }
        catch(SQLException e){}
    }
    
           
    @Override
    public void delete(int id){
        try {
            String sql = "DELETE FROM novedades WHERE id_novedad=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if(rowsDeleted > 0){
                System.out.println("News deleted. Rows: " + rowsDeleted);
            }
        }
        catch(SQLException e){}
    }
}