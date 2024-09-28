/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package final3bi.DAO;

import java.sql.Connection;
import final3biDTO.RelatoDTO;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author mathe
 */
public class RelatoDAO {
    Connection conn;
    
    public void enviarRelato(RelatoDTO objrelatoDto){
        //Nova conexao com BD
        conn = new ConectaBD().conexao();
 
        try{
            String sql = "INSERT INTO relatos (id_user,relato) VALUES (?,?)";
            //declarar pstm e fazer ele receber o sql
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, objrelatoDto.getId_user()); 
            pstm.setString(2, objrelatoDto.getRelato());
            //Executar e fechar
            pstm.execute();
            pstm.close();
            //Mensagem de Confirmacao
            JOptionPane.showMessageDialog(null, "Relato ENVIADO!!");
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro para inserir relato: " + e);
        }
   }
}
