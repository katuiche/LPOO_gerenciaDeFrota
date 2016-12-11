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
import java.util.ArrayList;
import java.util.List;
import trabalho.locadora.Van.ModeloVan;
import trabalho.locadora.Veiculo.Categoria;
import trabalho.locadora.Veiculo.Estado;
import trabalho.locadora.Veiculo.Marca;

/**
 *
 * @author Belniak
 */
public class VanDAO {
    public void inserirMoto(Van van){
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            //DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            con = ConnectionFactory.getConnection();       
            
            stmt = con.prepareStatement("INSERT INTO veiculo(marca,estado,categoria,placa,locacao,ano,valorCompra) VALUES (?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, van.getMarca().toString());
            stmt.setString(2, van.getEstado().toString());
            stmt.setString(3, van.getCategoria().toString());
            stmt.setString(4, van.getPlaca());
            if (van.getLocacao() != null){
                stmt.setString(5, Integer.toString(van.getLocacao().getId()));
            }
            else {
                stmt.setString(5,"0");
            }
            
            stmt.setString(6, Integer.toString(van.getAno()));
            stmt.setString(7, Double.toString(van.getValorCompra()));
            
           
            stmt.executeUpdate();
            
            int id = lerIdVeiculo(stmt);
            
            String modelo;
            
            stmt = con.prepareStatement("INSERT INTO van(id_veiculo,modelo) VALUES (?,?)",PreparedStatement.RETURN_GENERATED_KEYS);  
            
            modelo = van.getModelo().toString();
            
            
            stmt.setString(1, Integer.toString(id));
            stmt.setString(2, modelo);
            
            stmt.executeUpdate();
            
            con.close();
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir uma van no banco de dados. Origem="+ex.getMessage());
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
    
    
    public List<Veiculo> listarVan() throws SQLException{
        //Select para pegar as locações
        Connection con = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        List<Veiculo> vans = new ArrayList();
        ResultSet resultado = null;
        ResultSet resultado2 = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement("SELECT * FROM veiculo");
            stmt2 = con.prepareStatement("SELECT * FROM van WHERE id_veiculo = ?");
            resultado = stmt.executeQuery();
            //LocacaoDAO l = new LocacaoDAO();
            while (resultado.next()) {
                stmt2.setString(1,resultado.getString("id"));
                resultado2 = stmt2.executeQuery();
                if (resultado2.next()){
                    Van van = new Van(ModeloVan.valueOf(resultado2.getString("modelo")),  Categoria.valueOf(resultado.getString("categoria")) , Estado.valueOf(resultado.getString("estado")) , resultado.getString("placa"), resultado.getInt("ano"), Marca.valueOf(resultado.getString("marca")),resultado.getDouble("valorCompra"));
                    vans.add(van);
                }
            }
            con.close();
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar vans no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }
    

        return vans;
    }

    public List<Veiculo> filtrarVan(Veiculo v) throws SQLException{
        //Select para pegar as locações
        Connection con = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        List<Veiculo> motos = new ArrayList<Veiculo>();
        ResultSet resultado = null;
        ResultSet resultado2 = null;
        String sql1 = "SELECT * FROM veiculo";
        boolean and = false;
        
        if(v.marca != null){
            sql1 += " WHERE marca = '"+v.getMarca().toString()+"'";
            and = true;
        }
        
        if(v.categoria != null){
            if(and)
                sql1 += " AND categoria = '" + v.getCategoria().toString()+ "'";
            else
                sql1 += " WHERE categoria = '" + v.getCategoria().toString()+ "'";
            and = true;
        }
        
        if(v.estado != null){
            if(and)
                sql1 += " AND estado = '" + v.getEstado().toString()+ "'";
            else
                sql1 += " WHERE estado = '" + v.getEstado().toString()+ "'";
        }
      
        
        
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(sql1);
            stmt2 = con.prepareStatement("SELECT * FROM van WHERE id_veiculo = ?");
            resultado = stmt.executeQuery();
            //LocacaoDAO l = new LocacaoDAO();
            while (resultado.next()) {
                stmt2.setString(1,resultado.getString("id"));
                resultado2 = stmt2.executeQuery();
                if (resultado2.next()){
                    Van moto = new Van(Van.ModeloVan.valueOf(resultado2.getString("modelo")),  Categoria.valueOf(resultado.getString("categoria")) , Estado.valueOf(resultado.getString("estado")) , resultado.getString("placa"), resultado.getInt("ano"), Marca.valueOf(resultado.getString("marca")),resultado.getDouble("valorCompra"));
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
    
    
    
    
    public void atualizarVan(Van van){
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            //DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            con = ConnectionFactory.getConnection();       
            
            stmt = con.prepareStatement("UPDATE veiculo set marca = ?,estado=?,categoria=?,placa=?,locacao=?,ano=?,valorCompra=? WHERE placa = ?",PreparedStatement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, van.getMarca().toString());
            stmt.setString(2, van.getEstado().toString());
            stmt.setString(3, van.getCategoria().toString());
            stmt.setString(4, van.getPlaca());
            if (van.getLocacao() != null){
                stmt.setString(5, Integer.toString(van.getLocacao().getId()));
            }
            else {
                stmt.setString(5,"0");
            }
            
            stmt.setString(6, Integer.toString(van.getAno()));
            stmt.setString(7, Double.toString(van.getValorCompra()));
            stmt.setString(8, van.getPlaca());
            
            stmt.executeUpdate();
            
            stmt = con.prepareStatement("SELECT * FROM veiculo WHERE placa = ?");
            stmt.setString(1, van.getPlaca());
            stmt.executeQuery();
            ResultSet rs = stmt.executeQuery();
            
            int id = 0;
            
            while(rs.next()){
                id = rs.getInt("id");
            }
            
            
            
            
            String modelo;
            //
            stmt = con.prepareStatement("UPDATE van SET modelo = ? WHERE id_veiculo = ?",PreparedStatement.RETURN_GENERATED_KEYS);  
            
            modelo = van.getModelo().toString();
            
            stmt.setString(1, modelo);
            stmt.setString(2, Integer.toString(id));
            
            
            stmt.executeUpdate();
            
            con.close();
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar uma van no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }
              
        
    }
    
    
    
    public void excluirVan(Van van){
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            //DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            con = ConnectionFactory.getConnection(); 
            
            
            stmt = con.prepareStatement("SELECT * FROM veiculo WHERE placa = ?");
            stmt.setString(1, van.getPlaca());
            stmt.executeQuery();
            ResultSet rs = stmt.executeQuery();
            
            int id = 0;
            
            while(rs.next()){
                id = rs.getInt("id");
            }
            
            
            
            stmt = con.prepareStatement("DELETE FROM veiculo WHERE placa = ?",PreparedStatement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, van.getPlaca());
           
            stmt.executeUpdate();
            
         
            
            stmt = con.prepareStatement("DELETE FROM van WHERE id_veiculo = ?",PreparedStatement.RETURN_GENERATED_KEYS);  
                      
            stmt.setString(1, Integer.toString(id));
            
            stmt.executeUpdate();
            
            con.close();
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar uma van no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }
              
        
    }
    
    
    public Van consultarVan(String placa) throws SQLException{
        //Select para pegar as locações
        Connection con = null;
        Van van = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet resultado = null;
        ResultSet resultado2 = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement("SELECT * FROM veiculo WHERE placa = ?");
            stmt.setString(1,placa);
            stmt2 = con.prepareStatement("SELECT * FROM van WHERE id_veiculo = ?");
            resultado = stmt.executeQuery();
            //LocacaoDAO l = new LocacaoDAO();
            while (resultado.next()) {
                stmt2.setString(1,resultado.getString("id"));
                resultado2 = stmt2.executeQuery();
                if (resultado2.next()){
                    van = new Van(ModeloVan.valueOf(resultado2.getString("modelo")),  Categoria.valueOf(resultado.getString("categoria")) , Estado.valueOf(resultado.getString("estado")) , resultado.getString("placa"), resultado.getInt("ano"), Marca.valueOf(resultado.getString("marca")),resultado.getDouble("valorCompra"));
                    
                }
            }
            con.close();
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar van no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }
    

        return van;
    }
    
}
