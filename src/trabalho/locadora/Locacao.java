/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.locadora;

import java.util.Calendar;

/**
 *
 * @author Belniak
 */
public class Locacao {
    private int dias;
    private double valor;
    Calendar data;
    Cliente cliente;

    public Locacao(Cliente cliente, Calendar data, int dias, double valor ) {
        this.dias = dias;
        this.valor = valor;
        this.data = data;
        this.cliente = cliente;
    }
    
    
    
    public double getValor(){
        return valor;
    }
    
    public Calendar getData(){
        return data;
    }
    
    public Cliente getCliente(){
        return cliente;
    }
}
