/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.locadora;

/**
 *
 * @author Belniak
 */
public abstract class Veiculo implements VeiculoI{
    
    enum Marca {
        VW, GM, Fiat, Honda, Mercedes; 
    }
    
    enum Estado{
        NOVO, LOCADO, DISPONIVEL, VENDIDO;
    }
    
    enum Categoria{
        POPULAR, INTERMEDIARIO, LUXO;
    }
    
    
    
    double valorCompra;   
    String placa;//tem algumas limitações como 9 digitos e apenas letras e numeros, talvez implementar uma função para validar
    int ano;
    Estado estado;
    Categoria categoria;
    Marca marca;
    Locacao locacao;
    
}
