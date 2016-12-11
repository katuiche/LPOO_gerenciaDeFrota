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
        
        public Marca checkString(String str){
            for(Marca m:this.values()){
                if(m.toString().equals(str))
                    return m;
            }
            return this;
        }
    }
    
    enum Estado{
        Novo, Locado, Disponivel, Vendido;
        
        public Estado checkString(String str){
            for(Estado m:this.values()){
                if(m.toString().equals(str))
                    return m;
            }
            return this;
        }
    }
    
    enum Categoria{
        Popular, Intermediario, Luxo;
        
        public Categoria checkString(String str){
            for(Categoria m:this.values()){
                if(m.toString().equals(str))
                    return m;
            }
            return this;
        }
    }
    
    abstract String getModeloString();
    //abstract String getModeloString();
    
    
    double valorCompra;   
    String placa;//tem algumas limitações como 9 digitos e apenas letras e numeros, talvez implementar uma função para validar
    int ano;
    Estado estado;
    Categoria categoria;
    Marca marca;
    Locacao locacao;
    Object modelo;
    
}
