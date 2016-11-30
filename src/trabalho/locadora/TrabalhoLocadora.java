/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.locadora;

import java.sql.SQLException;
import java.util.Calendar;
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
        LocacaoDAO l = new LocacaoDAO();
        Calendar c = Calendar.getInstance();
        c.getTime();
        
        Locacao lo = new Locacao(null, c, 7, 80.2 );
        
       
        l.inserirLocacao(lo);
        
        
        for(Locacao f: l.listarLocacao()){
            System.out.println(f.getId() + " " + f.getValor());
        }
        
        lo.setValor(10.5);
        
        l.atualizarLocacao(lo);
        
        for(Locacao f: l.listarLocacao()){
            System.out.println(f.getId() + " " + f.getValor());
        }
        
        l.excluirLocacao(lo);
        
        for(Locacao f: l.listarLocacao()){
            System.out.println(f.getId() + " " + f.getValor());
        }
        
    }
    
}
