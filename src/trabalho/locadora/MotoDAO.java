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
public class MotoDAO {
    
//    
//    public void inserirMoto(Moto moto){
//        Connection con = null;
//        PreparedStatement stmt = null;
//        
//        try{
//            con = ConnectionFactory.getConnection();       
//            
//            stmt = con.prepareStatement("INSERT INTO veiculo(marca,estado,categoria,placa) VALUES (?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
//            
//            stmt.setString(1, moto.getMarca().toString());
//            stmt.setString(2, moto.getEstado().toString());
//            stmt.setString(3, moto.getCategoria().toString());
//            stmt.setString(4, moto.getPlaca());
//           
//            stmt.executeUpdate();
//            moto.setId(lerIdMoto(stmt));
//            int idAutorGravado = lerIdMoto(stmt);
//            moto.setId(idAutorGravado);
//            
//            
//            stmt = con.prepareStatement("INSERT INTO moto(id_veiculo,modelo) VALUES (?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
//            
//            
//        }
//        catch (SQLException ex) {
//            throw new RuntimeException("Erro ao inserir um cliente no banco de dados. Origem="+ex.getMessage());
//        } finally{
//            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
//            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
//        }
//        
//        private int lerIdMoto(PreparedStatement stmt) throws SQLException {
//            ResultSet rs = stmt.getGeneratedKeys();
//            rs.next();
//            return rs.getInt(1);
//        }
//        
//        
//    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
