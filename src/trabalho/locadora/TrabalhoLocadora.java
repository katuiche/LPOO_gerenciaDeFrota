/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.locadora;

import java.sql.SQLException;
import trabalho.locadora.Veiculo.*;

/**
 *
 * @author Belniak
 */
public class TrabalhoLocadora {

    /**
     * @param args the command line arguments
     */
   
    
    public static void main(String[] args) throws SQLException {
        ClienteDAO d = new ClienteDAO();
        Cliente cliente = new Cliente("lucas","Eugenio","sdfr","69786","Rua das lunas");
        //d.inserirCliente(cliente);
        
        for (Cliente c : d.lerClientes()){
            System.out.println(c.getNome());
        }
        
    }
    
}
