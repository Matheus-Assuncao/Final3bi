/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package final3bi.DAO;

import java.sql.Connection;
import final3biDTO.UsuarioDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;

/**
 *
 * @author mathe
 */
public class UsuarioDAO {

    Connection conn;

    public ResultSet autenticacaoUsuario(UsuarioDTO objusuariodto) {
        conn = new ConectaBD().conexao();

        try {
            String sql = "select * from usuarios where user = ? and senha = ? ";
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, objusuariodto.getUser());
            pstm.setString(2, objusuariodto.getSenha());
            
            pstm.execute();
            
            ResultSet rs = pstm.executeQuery();
            return rs;


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO: " + e);
            return null;
        }
    }
    
    public void cadastrarUser(UsuarioDTO objuserDto){
        conn = new ConectaBD().conexao();
        
        try{
            String sql = "INSERT INTO usuarios (user,nome,senha) VALUES (?,?,?)";
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, objuserDto.getUser());
            pstm.setString(2, objuserDto.getNome());
            pstm.setString(3, objuserDto.getSenha());
            
            pstm.execute();
            pstm.close();
            
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro no cadastro de usuário: " + e);
        }
    }
}
