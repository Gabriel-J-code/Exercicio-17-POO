package com.example.project;

public class Imposto{

    private String lei;
    private Double taxaFederal;
    private Double taxaEstadual;

    public Imposto(String lei, Double taxaFederal, Double taxaEstadual){        
        this.lei = lei;
        if(taxaFederal<0){
            throw new RuntimeException("Taxa federal invalida");
        }
        this.taxaFederal = taxaFederal;
        if(taxaEstadual<0){
            throw new RuntimeException("Taxa estadual invalida");
        }
        this.taxaEstadual = taxaEstadual;
    }
      
    public String getLei() {        
        return lei;
    }
    
    public Double getTaxaFederal() {       
        return taxaFederal;
    }
    
    public Double getTaxaEstadual() {        
        return taxaEstadual;
    }
    
    public Double calcularImpostoFederal(Double valor) {        
        return valor * (taxaFederal/100);
    }

    public Double calcularImpostoEstadual(Double valor) {        
        return valor * (taxaEstadual/100);
    }    
}