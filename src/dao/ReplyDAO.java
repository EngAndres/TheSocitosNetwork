 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.ReplyModel;


/**
 *
 * @author jerar
 */
public class ReplyDAO implements DAOTemplate<ReplyModel> {
    // DB Connection
    Connection conn = ConnectionDB.getConnection();
    
    @Override
    public ArrayList<ReplyModel> getAll(){
        ArrayList<ReplyModel> replies = new ArrayList<>();
        try{
            String query        = """
                                    SELECT respuesta.id_respuesta, respuesta.contenido, respuesta.fecha, usuario.nombre, respuesta.novedad_fk 
                                    FROM novedad 
                                    JOIN usuario ON novedad.usuario_fk=usuario.id_usuario;
                                  """;
            Statement statement = conn.createStatement();
            ResultSet result    = statement.executeQuery( query );
            
            int count = 0;
            while(result.next()){
                ReplyModel reply = new ReplyModel( result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getInt(5) );
                replies.add( reply );
                count++;
            }
            System.out.println(count + " replies retrieved");
        }
        catch(SQLException e){}
        return replies;
    }
    
    
    public ArrayList<ReplyModel> getAllByNews(int news_id){
        ArrayList<ReplyModel> replies = new ArrayList<>();
        try{
            String query        = """
                                    SELECT respuesta.id_respuesta, respuesta.contenido, respuesta.fecha, usuario.nombre, respuesta.novedad_fk 
                                    FROM novedad 
                                    JOIN usuario ON novedad.usuario_fk=usuario.id_usuario
                                    WHERE respuesta.novedad_fk=?;
                                  """;
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, news_id);
            ResultSet result = statement.executeQuery();
            int count = 0;
            while(result.next()){
                ReplyModel reply = new ReplyModel( result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getInt(5) );
                replies.add( reply );
                count++;
            }
            System.out.println(count + " replies retrieved.");
        }
        catch(SQLException e){}
        return replies;
    }
    
    
    @Override
    public ReplyModel getOne(int id) {
        ReplyModel reply = null;
        try {
            String sql = """
                            SELECT respuesta.contenido, respuesta.fecha, usuario.nombre, respuesta.novedad_fk 
                            FROM respuesta
                            JOIN usuario ON respuesta.usuario_fk=usuario.id_usuario
                            WHERE respuesta.id_respuesta=?;
                         """;
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                reply = new ReplyModel(id, result.getString(1), result.getString(2), result.getString(3), result.getInt(4));
                break;
            }
        } 
        catch (SQLException ex) {}
        return reply;
    }
    
    
    @Override
    public void insert(ReplyModel reply){
        try {
            String sql = "INSERT respuesta(contenido, fecha, usuario_fk, novedad_fk) VALUES (?,?,?,?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, reply.getPost());
            statement.setString(2, reply.getDate());
            statement.setInt(3, reply.getUserFK());
            statement.setInt(4, reply.getNewsFK());
            int rowsInserted = statement.executeUpdate();
            if(rowsInserted > 0){
                System.out.println("Reply added. Rows: " + rowsInserted);
            }
        }
        catch(SQLException e){}
    }
    
    
    @Override
    public void update(int id, ReplyModel reply){
        try {
            String query = "UPDATE respuesta SET contenido=? WHERE id_contenido=?;";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, reply.getPost());
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            if(rowsUpdated > 0){
                System.out.println("Reply updated. Rows: " + rowsUpdated);
            }
        }
        catch(SQLException e){}
    }
    
           
    @Override
    public void delete(int id){
        try {
            String query = "DELETE FROM respuesta WHERE id_respuesta=?;";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if(rowsDeleted > 0){
                System.out.println("Reply deleted. Rows: " + rowsDeleted);
            }
        }
        catch(SQLException e){}
    }
}