/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfacegrafica.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author dinho
 */
public class ConexaoBD {
    private static String USERNAME = "aluno";
    private static String PASSWORD = "123456";
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/aulajava";
    
    public Connection getConnection(){
        try {
            Class.forName(DRIVER);
            return DriverManager
                    .getConnection(URL,USERNAME,PASSWORD);
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
        catch(ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
