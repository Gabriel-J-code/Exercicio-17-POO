package com.example.project;

public class Produto {

    private int codigo;
    private String descricao;
    private String unidade;
    private Double valorUnitario;
    private String substituicaoTributaria;

    public Produto(int codigo, String descricao, String unidade, Double valorUnitario, String substituicaoTributaria) {
        this.setCodigo(codigo);
        this.setDescricao(descricao);
        this.setUnidade(unidade);
        this.setValorUnitario(valorUnitario);
        this.setSubstituicaoTributaria(substituicaoTributaria);
    }

    public Object getSubstituicaoTributaria() {
        return substituicaoTributaria;
    }

    public void setSubstituicaoTributaria(String substituicaoTributaria) {
        this.substituicaoTributaria = substituicaoTributaria;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    
}
