package com.example.project;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Pagamento {
    private double total;
    private double valorRecebido;
    private double troco;
    private String tipo;
    private boolean pago;

    private String[] tiposValidos = {"Dinheiro","Cartao de credito", "Cartao de debito"};

    public String getTipo() {
        return tipo;
    }
    public boolean isPago() {
        return pago;
    }
    private void setPago(boolean pago) {
        this.pago = pago;
    }
    private void setTipo(String tipo) {
        boolean funcionou = false;
        for (String in : this.tiposValidos) {
            if (tipo.toUpperCase().equals(in.toUpperCase())){
                this.tipo = in;
                funcionou = true;                
            }            
        }
        if (!funcionou){
            throw new RuntimeException("Tipo de pagamento não valido");
        }              
		
	}
	public double getTroco() {
        return troco;
    }
    private void setTroco(double troco){
        this.troco = troco;
    }
    public double getValorRecebido() {
        return valorRecebido;
    }
    private void setValorRecebido(double valorRecebido) {
        if (valorRecebido<this.total){
            throw new RuntimeException("Valor recebido insuficiente");
        }
        this.valorRecebido = valorRecebido;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }

    public Pagamento(double total){
        this.setTotal(total);
        pago = false;
    }    

    public void efetuar(double valorRecebido, String tipo){
        this.setTipo(tipo);
        this.setValorRecebido(valorRecebido);
        double troco = valorRecebido - this.total;
        this.setTroco(troco);
        setPago(true);        
    }
    
    public String imprimir() {
        String BREAK = System.lineSeparator();
        if (!pago){
            throw new RuntimeException("O pagamento ainda não foi efetuado");
        }
        
        DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.US);
        dfs.setDecimalSeparator('.');
        DecimalFormat dFormat = new DecimalFormat("#.00");
        dFormat.setDecimalFormatSymbols(dfs);

        StringBuilder impressao = new StringBuilder();
        impressao.append(String.format("%s %s",tipo,dFormat.format(valorRecebido)));
        if(troco>0.0){
            impressao.append(BREAK);
            impressao.append(String.format("Troco R$ %s", dFormat.format(troco)));
        }        
        return impressao.toString();
    }

    public String efetuarEImprimir(double valorRecebido, String tipo){
        efetuar(valorRecebido, tipo);
        return imprimir();
    }
    
}