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
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Belniak
 */
public class MotoDAO {
    
    public void inserirVeiculo(Moto moto){
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            //DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            con = ConnectionFactory.getConnection();       
            
            stmt = con.prepareStatement("INSERT INTO veiculo(marca,estado,categoria,placa,locacao,ano,valorCompra) VALUES (?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, moto.getMarca().toString());
            stmt.setString(2, moto.getEstado().toString());
            stmt.setString(3, moto.getCategoria().toString());
            stmt.setString(4, moto.getPlaca());
            if (moto.getLocacao() != null){
                stmt.setString(5, Integer.toString(moto.getLocacao().getId()));
            }
            else {
                stmt.setString(5,"0");
            }
            
            stmt.setString(6, Integer.toString(moto.getAno()));
            stmt.setString(7, Double.toString(moto.getValorCompra()));
            
           
            stmt.executeUpdate();
            
            int id = lerIdVeiculo(stmt);
            
            String modelo;
            
            stmt = con.prepareStatement("INSERT INTO moto(id_veiculo,modelo) VALUES (?,?)",PreparedStatement.RETURN_GENERATED_KEYS);  
            
            modelo = moto.getModelo().toString();
            
            
            stmt.setString(1, Integer.toString(id));
            stmt.setString(2, modelo);
            
            stmt.executeUpdate();
            
            con.close();
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
