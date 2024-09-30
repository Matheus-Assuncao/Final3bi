/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package final3bi.DAO;

/**
 *
 * @author mathe
 */

import final3biDTO.FeedbackDTO;
import java.sql.Connection;
import final3biDTO.UsuarioDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class FeedbackDAO {
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    
    public void cadastraFeedback(FeedbackDTO objfeedbackDto){
        conn = new ConectaBD().conexao();
        
        try {
            String sql = "INSERT INTO feedbacks(feedback, id_user) values (?,?)";
            
            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objfeedbackDto.getFeedback());
            pstm.setInt(2, objfeedbackDto.getId_user());
            
            pstm.execute();
            pstm.close();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro FeedbackDAO: "+e);
        }
    }
    
}
