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
public class Moto extends Veiculo{
    
    enum ModeloMoto{
        CG125, CBR500;
        
        public ModeloMoto checkString(String str){
            //ModeloMoto mot;
            for(ModeloMoto m:this.values()){
                if(m.toString().equals(str))
                    return m;
            }
            return null;
        }
        
        
    }

    public Moto(ModeloMoto modelo, Categoria categoria, Estado estado , String placa, int ano, Marca marca,double valorCompra) {
        this.modelo = modelo;
        this.categoria = categoria;
        this.estado = estado;
        this.placa = placa;
        this.ano = ano;
        this.marca = marca;
        this.locacao = null;
        this.valorCompra = valorCompra;
    }
    
    
    
    private ModeloMoto modelo;
    
    @Override
    public void locar(int dias, Calendar data, Cliente cliente) {
        estado = Estado.Locado;
        locacao = new Locacao(cliente,data,dias,getValorDiariaLocacao());
        
    }

    @Override
    public void vender() {
        estado = Estado.Vendido;
    }

    @Override
    public void devolver() {
        estado = Estado.Disponivel;
    }

    @Override
    public Estado getEstado() {
        return estado;
    }

    @Override
    public Marca getMarca() {
        return marca;
    }

    @Override
    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public Locacao getLocacao() {
        return locacao;
    }

    @Override
    public String getPlaca() {
        return placa;
    }

    @Override
    public int getAno() {
        return ano;
    }
    
    public double getValorCompra(){
        return valorCompra;
    }

    @Override
    public double getValorParaVenda() {
        double valorVenda = 0;
        
        valorVenda = valorCompra - Calendar.YEAR * 0.15 * valorCompra;
        
        if (valorVenda < valorCompra * 0.10){
            valorVenda = valorCompra * 0.10;
        }
        
        
        return valorVenda;
    }

    @Override
    public double getValorDiariaLocacao() {
        if (categoria == Categoria.Popular){
            return 70.00;
        }
        else if (categoria == Categoria.Intermediario){
            return 200.00;
        }
        else
            return 350.00;
    }
    
    public ModeloMoto getModelo(){
        return modelo;
    }
    
    public String getModeloString(){
        return modelo.toString();
        
    }
}
