/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package final3bi.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 *
 * @author mathe
 */
public class ConectaBD {
    
    public Connection conexao(){
         Connection conn = null;
         
         try {
            String url = "jdbc:mysql://localhost:3306/bd_cleancity?user=root&password=";
            conn = DriverManager.getConnection(url); //Faz a conexao usando a url
            
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Conexao DAO ERRO: "+ e.getMessage());
        }
        
        return conn; //Retorna a conexao para usar
         
    }
}

