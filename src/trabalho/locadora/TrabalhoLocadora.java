/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.locadora;

import trabalho.locadora.Veiculo.*;

/**
 *
 * @author Belniak
 */
public class TrabalhoLocadora {

    /**
     * @param args the command line arguments
     */
   
    
    public static void main(String[] args) {
        ClienteDAO d = new ClienteDAO();
        Cliente cliente = new Cliente("João","Eugenio","1545454","7454554","Rua das ingridis");
        d.inserirCliente(cliente);
        
        
    }
    
}
