/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.locadora;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Belniak
 */
public class ClienteDAO {
    
    
    public void inserirCliente(Cliente cliente){
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement("INSERT INTO cliente(nome,sobrenome,rg,cpf,endereco) VALUES (?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getSobrenome());
            stmt.setString(3, cliente.getRg());
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getEndereco());
            stmt.executeUpdate();
            
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir um autor no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }
        
    }
    
    private int lerIdCliente(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }
    
    
    
    
    
    
    
    
}
