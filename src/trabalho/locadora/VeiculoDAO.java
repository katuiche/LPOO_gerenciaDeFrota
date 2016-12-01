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
public class VeiculoDAO {
    
    
    public void inserirVeiculo(Veiculo veiculo){
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            con = ConnectionFactory.getConnection();       
            
            stmt = con.prepareStatement("INSERT INTO veiculo(marca,estado,categoria,placa,locacao) VALUES (?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, veiculo.getMarca().toString());
            stmt.setString(2, veiculo.getEstado().toString());
            stmt.setString(3, veiculo.getCategoria().toString());
            stmt.setString(4, veiculo.getPlaca());
            stmt.setString(5, Integer.toString(veiculo.getLocacao().getId()));
           
            stmt.executeUpdate();
            
            int id = lerIdVeiculo(stmt);
            
            String modelo;
            
            if (veiculo instanceof Moto){
                stmt = con.prepareStatement("INSERT INTO moto(id_veiculo,modelo) VALUES (?,?)",PreparedStatement.RETURN_GENERATED_KEYS);  
                Moto m = (Moto) veiculo;
                modelo = m.getModelo().toString();
            }
            else if (veiculo instanceof Automovel){
                stmt = con.prepareStatement("INSERT INTO automovel(id_veiculo,modelo) VALUES (?,?)",PreparedStatement.RETURN_GENERATED_KEYS);    
                Automovel a = (Automovel) veiculo;
                modelo = a.getModelo().toString();
            }
            else {
                stmt = con.prepareStatement("INSERT INTO van(id_veiculo,modelo) VALUES (?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
                Van v = (Van) veiculo;
                modelo = v.getModelo().toString();
            }
            
            stmt.setString(1, Integer.toString(id));
            stmt.setString(2, modelo);
            
            
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir uma moto no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }
        
        
        
        
    }
    
    
    
    private int lerIdVeiculo(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }
    
    
}
