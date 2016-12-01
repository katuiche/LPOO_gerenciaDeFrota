/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.locadora;

import java.sql.SQLException;
import java.util.Calendar;
import trabalho.locadora.Moto.ModeloMoto;
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
        MotoDAO m = new MotoDAO();
        ClienteDAO c = new ClienteDAO();
        
        //Moto mm = new Moto(ModeloMoto.CG125, Categoria.INTERMEDIARIO, Estado.DISPONIVEL , "XXX1254", 1997, Marca.Fiat,155.00);
        
        Cliente cc = c.consultarCliente(1);
        
        //m.inserirMoto(mm);
        
        
        
        for(Moto f: m.listarMoto()){
            System.out.println(f.getAno() + " " + f.getValorCompra());
        }
//        
//        lo.setValor(10.5);
//        
//        l.atualizarLocacao(lo);
//        
//        for(Locacao f: l.listarLocacao()){
//            System.out.println(f.getId() + " " + f.getValor());
//        }
//        
//        l.excluirLocacao(lo);
//        
//        for(Locacao f: l.listarLocacao()){
//            System.out.println(f.getId() + " " + f.getValor());
//        }
        
    }
    
}
