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
            cliente.setId(lerIdCliente(stmt));
            con.close();
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir um cliente no banco de dados. Origem="+ex.getMessage());
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
    
    
    public void atualizarCliente(Cliente cliente){
        Connection con = null;
        PreparedStatement stmt = null;
        
        
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement("UPDATE cliente SET nome=? , sobrenome=?, rg=?, cpf=?, endereco=? WHERE id=?",PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getSobrenome());
            stmt.setString(3, cliente.getRg());
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getEndereco());
            stmt.setString(6, Integer.toString(cliente.getId()));
            stmt.executeUpdate();
            con.close();
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar um cliente no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }
        
    }
    
    
    
    public void excluirCliente(Cliente cliente){
        Connection con = null;
        PreparedStatement stmt = null;
        
        
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement("DELETE FROM cliente WHERE id=?",PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, Integer.toString(cliente.getId()));
            stmt.executeUpdate();
            con.close();
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao apagar um cliente no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }
    }
    
    
    
    public List<Cliente> lerClientes() throws SQLException{
        //Select para pegar os Clientes
        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM cliente";
        List<Cliente> clientes = new ArrayList<Cliente>();
        ResultSet resultado = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement("SELECT * FROM cliente");
            
            resultado = stmt.executeQuery();
            
            while (resultado.next()) {
                Cliente cliente = new Cliente(resultado.getString("nome"),resultado.getString("sobrenome"),resultado.getString("rg"),resultado.getString("cpf"),resultado.getString("endereco"));
                cliente.setId(resultado.getInt("id"));
                clientes.add(cliente);
            }
            con.close();
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar clientes no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }
    

        return clientes;
    }
    
    public Cliente consultarCliente(int id){
        Cliente cliente = null;
        
        
        Connection con = null;
        PreparedStatement stmt = null;
        
        ResultSet resultado = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE id=?");
            stmt.setString(1, Integer.toString(id));
            resultado = stmt.executeQuery();
            
            while (resultado.next()) {
                cliente = new Cliente(resultado.getString("nome"),resultado.getString("sobrenome"),resultado.getString("rg"),resultado.getString("cpf"),resultado.getString("endereco"));
                cliente.setId(resultado.getInt("id"));               
            }
            con.close();
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar clientes no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }
        
        
        
        return cliente;
    }
    
    public List<Cliente> filtrarClientes(Cliente filtro) throws SQLException{
        //Select para pegar os Clientes
        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM cliente";
        List<Cliente> clientes = new ArrayList<Cliente>();
        ResultSet resultado = null;
        boolean and = false;
        
        if(!"".equals(filtro.getNome())){
            sql += " WHERE nome = '"+filtro.getNome()+"'";
            and = true;
        }
        
        if(!"".equals(filtro.getSobrenome())){
            if(and)
                sql += " AND";
            else
                sql += " WHERE";
            sql += " sobrenome = '"+filtro.getSobrenome()+"'";
            and = true;
        }
        
        if(!"".equals(filtro.getCpf())){
            if(and)
                sql += " AND";
            else
                sql += " WHERE";
            sql += " cpf = '"+filtro.getCpf()+"'";
            and = true;
        }
        
                
                
                
        //System.out.println(sql);
                
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            
            resultado = stmt.executeQuery();
            
            while (resultado.next()) {
                Cliente cliente = new Cliente(resultado.getString("nome"),resultado.getString("sobrenome"),resultado.getString("rg"),resultado.getString("cpf"),resultado.getString("endereco"));
                cliente.setId(resultado.getInt("id"));
                clientes.add(cliente);
            }
            
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar clientes no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }
    
        con.close();
        return clientes;
    }
    
    public int checarLocacao(int id){
        Cliente cliente = null;
        
        
        Connection con = null;
        PreparedStatement stmt = null;
        int count = 0;
        ResultSet resultado = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement("SELECT * FROM locacao WHERE ? = locacao.cliente");
            stmt.setInt(1, id);
            resultado = stmt.executeQuery();
            
            while (resultado.next()) {
               count++;              
            }
            
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar clientes no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }
        
        
        
        return count;
    }
    
    
}
