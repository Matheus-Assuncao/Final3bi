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
    
    public ArrayList<UsuarioDTO> pesquisarUser(){
        String sql = "SELECT * FROM usuarios";
        //Conexao com o banco
        conn = new ConectaBD().conexao();
        try {
            //Configurar o pstm
            pstm = conn.prepareStatement(sql);
            //Montar o ResultSet
            rs = pstm.executeQuery();
            
            //Enquanto o resultado do banco tiver proximo, significa que tem mais informações
            while(rs.next()){ 
                UsuarioDTO objuserDto = new UsuarioDTO();
                objuserDto.setId_user(rs.getInt("id_user")); //usando setter no DTOuser com o id que vem da BD
                objuserDto.setUser(rs.getString("user"));
                objuserDto.setNome(rs.getString("nome"));
                
                lista.add(objuserDto); //Adicionar o registros dentro de uma lista(array)
            }
               
            
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO pesquisar: "+err);
        }
        
        return lista;
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
