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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import trabalho.locadora.Moto.ModeloMoto;
import trabalho.locadora.Veiculo.Categoria;
import trabalho.locadora.Veiculo.Estado;
import trabalho.locadora.Veiculo.Marca;

/**
 *
 * @author Belniak
 */
public class MotoDAO {
    
    public void inserirMoto(Moto moto){
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
    
    
    public List<Moto> listarMoto() throws SQLException{
        //Select para pegar as locações
        Connection con = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        List<Moto> motos = new ArrayList();
        ResultSet resultado = null;
        ResultSet resultado2 = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement("SELECT * FROM veiculo");
            stmt2 = con.prepareStatement("SELECT * FROM moto WHERE id_veiculo = ?");
            resultado = stmt.executeQuery();
            //LocacaoDAO l = new LocacaoDAO();
            while (resultado.next()) {
                stmt2.setString(1,resultado.getString("id"));
                resultado2 = stmt2.executeQuery();
                if (resultado2.next()){
                    Moto moto = new Moto(ModeloMoto.valueOf(resultado2.getString("modelo")),  Categoria.valueOf(resultado.getString("categoria")) , Estado.valueOf(resultado.getString("estado")) , resultado.getString("placa"), resultado.getInt("ano"), Marca.valueOf(resultado.getString("marca")),resultado.getDouble("valorCompra"));
                    motos.add(moto);
                }
            }
            con.close();
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar motos no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }
    

        return motos;
    }
    
    
    
    
    
    public void atualizarMoto(Moto moto){
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            //DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            con = ConnectionFactory.getConnection();       
            
            stmt = con.prepareStatement("UPDATE veiculo set marca = ?,estado=?,categoria=?,placa=?,locacao=?,ano=?,valorCompra=? WHERE placa = ?",PreparedStatement.RETURN_GENERATED_KEYS);
            
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
            stmt.setString(8, moto.getPlaca());
           
            stmt.executeUpdate();
            
            int id = lerIdVeiculo(stmt);
            
            String modelo;
            
            stmt = con.prepareStatement("UPDATE moto SET modelo = ? WHERE id_veiculo = ?",PreparedStatement.RETURN_GENERATED_KEYS);  
            
            modelo = moto.getModelo().toString();
            
            stmt.setString(1, modelo);
            
            stmt.executeUpdate();
            
            con.close();
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar uma moto no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }
              
        
    }
    
    
    
    public void excluirMoto(Moto moto){
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            //DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            con = ConnectionFactory.getConnection();       
            
            stmt = con.prepareStatement("DELETE veiculo WHERE placa = ?",PreparedStatement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, moto.getPlaca());
           
            stmt.executeUpdate();
            
            int id = lerIdVeiculo(stmt);
            
            stmt = con.prepareStatement("DELETE moto WHERE id_veiculo = ?",PreparedStatement.RETURN_GENERATED_KEYS);  
                      
            stmt.setString(1, Integer.toString(id));
            
            stmt.executeUpdate();
            
            con.close();
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar uma moto no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }
              
        
    }
    
    
    public Moto procurarMoto(String placa) throws SQLException{
        //Select para pegar as locações
        Connection con = null;
        Moto moto = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet resultado = null;
        ResultSet resultado2 = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement("SELECT * FROM veiculo WHERE placa = ?");
            stmt.setString(1,placa);
            stmt2 = con.prepareStatement("SELECT * FROM moto WHERE id_veiculo = ?");
            resultado = stmt.executeQuery();
            //LocacaoDAO l = new LocacaoDAO();
            while (resultado.next()) {
                stmt2.setString(1,resultado.getString("id"));
                resultado2 = stmt2.executeQuery();
                if (resultado2.next()){
                    moto = new Moto(ModeloMoto.valueOf(resultado2.getString("modelo")),  Categoria.valueOf(resultado.getString("categoria")) , Estado.valueOf(resultado.getString("estado")) , resultado.getString("placa"), resultado.getInt("ano"), Marca.valueOf(resultado.getString("marca")),resultado.getDouble("valorCompra"));
                    
                }
            }
            con.close();
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar moto no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }
    

        return moto;
    }
    
    
    
}
