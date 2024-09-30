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
import java.util.ArrayList;

/**
 *
 * @author mathe
 */
public class UsuarioDAO {

    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<UsuarioDTO> lista = new ArrayList<>();

    public ResultSet autenticacaoUsuario(UsuarioDTO objusuariodto) {
        conn = new ConectaBD().conexao();

        try {
            String sql = "select * from usuarios where user = ? and senha = ? ";
            
            pstm = conn.prepareStatement(sql);
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
            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objuserDto.getUser());
            pstm.setString(2, objuserDto.getNome());
            pstm.setString(3, objuserDto.getSenha());
            
            pstm.execute();
            pstm.close();
            
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro no cadastro de usuário: " + e);
        }
    }
    
    
    public int getId(UsuarioDTO objusuarioDto){
        String sql = "SELECT id_user FROM usuarios WHERE user = ?";
        
        conn = new ConectaBD().conexao();
        
        try{
            //Preparar mensagem para banco
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objusuarioDto.getUser());
            //Respsta do banco
            ResultSet rs = pstm.executeQuery();
            //Obtendo Id        
            if(rs.next()){
                return rs.getInt("id_user");
            }
            
        }catch(SQLException e ){
            JOptionPane.showMessageDialog(null, "UsuarioDAO getId: "+ e);            
        }
        //Retorna -1 caso o user nao seja encontrado
        return -1;
    }
        
}
