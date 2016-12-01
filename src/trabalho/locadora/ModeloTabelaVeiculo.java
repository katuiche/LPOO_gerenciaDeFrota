/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trabalho.locadora;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rafael
 */
public class ModeloTabelaVeiculo extends AbstractTableModel{
    private String[] colunas=new String[]{"Placa","Marca", "Modelo", "Ano","Preço da diária"};

    private List<Veiculo> lista=new ArrayList();

    
    public ModeloTabelaVeiculo(List<Veiculo> lista){
        this.lista=lista;
    }

    public ModeloTabelaVeiculo(){
    }


    @Override
    public int getRowCount() {
        return this.lista.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public String getColumnName(int index) {
        return this.colunas[index];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
        //if(column==0)
            //return false;
        //return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Veiculo customer = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return customer.getPlaca();//if column 0 (code)
            case 1: return customer.getMarca();//if column 1 (name)
            //case 2: return customer.getModelo();//if column 2 (birthday)
            case 3: return customer.getAno();//if column 2 (birthday)
            case 4: return customer.getValorDiariaLocacao() ;
            default : return null;
        }
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        try {
            Veiculo customer = lista.get(row);
            switch (col) {
                case 0:
                    customer.placa =((String) value); //if column 0 (code)
                    break;
                case 1:
                    customer.marca = ((Veiculo.Marca) value);
                    break;
                case 2:
                    //customer.modelo((String) value);
                    break;
                case 3:
                    customer.ano = ((int) value);
                    break;
                case 4:
                    customer.valorCompra = ((double) value);
                    break;
                default:
            }
            this.fireTableCellUpdated(row, col);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean removeVeiculo(Veiculo customer) {
        int linha = this.lista.indexOf(customer);
        boolean result = this.lista.remove(customer);
        this.fireTableRowsDeleted(linha,linha);//update JTable
        return result;
    }

    public void adicionaVeiculo(Veiculo customer) {
        this.lista.add(customer);
        //this.fireTableDataChanged();
        this.fireTableRowsInserted(lista.size()-1,lista.size()-1);//update JTable
    }

    public void setListaVeiculos(List<Veiculo> veiculos) {
        this.lista = veiculos;
        this.fireTableDataChanged();
        //this.fireTableRowsInserted(0,veiculos.size()-1);//update JTable
    }

    public void limpaTabela() {
        int indice = lista.size()-1;
        if(indice<0)
            indice=0;
        this.lista = new ArrayList();
        this.fireTableRowsDeleted(0,indice);//update JTable
    }

    public Veiculo getVeiculo(int linha){
        return lista.get(linha);
    }
    
}
