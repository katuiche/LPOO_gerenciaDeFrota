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
import java.util.Date;
import java.util.List;

/**
 *
 * @author Belniak
 */
public class LocacaoDAO {
    
    public void inserirLocacao(Locacao locacao){
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            //DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement("INSERT INTO locacao(dias,valor,data,cliente) VALUES (?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, Integer.toString(locacao.getDias()));
            stmt.setString(2, Double.toString(locacao.getValor()));
            stmt.setString(3, df.format(locacao.getData().getTime()));
            if (locacao.getCliente() != null)
                stmt.setString(4, Integer.toString(locacao.getCliente().getId()));
            else
                stmt.setString(4,"0");
                
            stmt.executeUpdate();
            locacao.setId(lerIdLocacao(stmt));
            
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir uma locação no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }
        
    }
    
    private int lerIdLocacao(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }
    
    
    public void atualizarLocacao(Locacao locacao){
        Connection con = null;
        PreparedStatement stmt = null;
        
        
        try{
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement("UPDATE locacao SET dias=? , valor=?, data=?, cliente=? WHERE id=?",PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, Integer.toString(locacao.getDias()));
            stmt.setString(2, Double.toString(locacao.getValor()));
            stmt.setString(3, df.format(locacao.getData().getTime()));
            if (locacao.getCliente() != null)
                stmt.setString(4, Integer.toString(locacao.getCliente().getId()));
            else
                stmt.setString(4,"0");
            stmt.setString(5, Integer.toString(locacao.getId()));
            stmt.executeUpdate();
            
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar uma locação no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }
        
    }
    
    
    
    public void excluirLocacao(Locacao locacao){
        Connection con = null;
        PreparedStatement stmt = null;
        
        
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement("DELETE FROM locacao WHERE id=?",PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, Integer.toString(locacao.getId()));
            stmt.executeUpdate();
            
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao apagar uma locacao no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }
    }
    
    
    
    public List<Locacao> listarLocacao() throws SQLException{
        //Select para pegar as locações
        Connection con = null;
        PreparedStatement stmt = null;
        
        List<Locacao> locacoes = new ArrayList();
        ResultSet resultado = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement("SELECT * FROM locacao");
            
            resultado = stmt.executeQuery();
            ClienteDAO c = new ClienteDAO();
            while (resultado.next()) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(resultado.getDate("data"));
                Locacao locacao = new Locacao(c.consultarCliente(Integer.parseInt(resultado.getString("cliente"))),cal,resultado.getInt("dias"),resultado.getDouble("valor"));
                locacao.setId(resultado.getInt("id"));
                locacoes.add(locacao);
            }
            
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar locações no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }
    

        return locacoes;
    }
    
    public Locacao consultarLocacao(int id){
        Locacao locacao = null;
        
        
        Connection con = null;
        PreparedStatement stmt = null;
        
        ResultSet resultado = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement("SELECT * FROM locacao WHERE id=?");
            stmt.setString(1, Integer.toString(id));
            resultado = stmt.executeQuery();
            
            ClienteDAO c = new ClienteDAO();
            while (resultado.next()) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(resultado.getDate("data"));
                locacao = new Locacao(c.consultarCliente(Integer.parseInt(resultado.getString("cliente"))),cal,resultado.getInt("dias"),resultado.getDouble("valor"));
                locacao.setId(resultado.getInt("id"));
                          
            }
            
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar clientes no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }
        
        
        
        return locacao;
    }
}
