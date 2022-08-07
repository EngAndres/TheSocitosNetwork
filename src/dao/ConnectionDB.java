/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.FileReader;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author jerar
 */
public class ConnectionDB {
    
    public static Connection getConnection(){
        Connection conn   = null;
        JSONParser parser = new JSONParser();
        try{
            String credentialsPath = System.getProperty("user.dir") + "/src/dao/config.json";
            JSONObject credentials = (JSONObject)parser.parse( new FileReader(credentialsPath) );
            
            String host     = (String)credentials.get("db_ip");
            String port     = (String)credentials.get("db_port");
            String username = (String)credentials.get("db_user");
            String password = (String)credentials.get("db_pass");
            
            String urlDB = "jdbc:mysql://" + host + ":" + port + "/socitos_net";
            conn = DriverManager.getConnection(urlDB, username, password);
            if(conn != null){
                System.out.println("Conexion realizada");
            }
        }
        catch(IOException | SQLException | ParseException e){
            e.printStackTrace();
        }
        return conn;
    }
    
    public static void main(String[] args){
        ConnectionDB.getConnection();
    }
}
