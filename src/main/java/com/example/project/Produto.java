package com.example.project;

public class Produto {

    private int codigo;
    private String descricao;
    private String unidade;
    private Double valor_unitario;
    private String substituicao_tributaria;

    public Produto(int codigo, String descricao, String unidade, Double valor_unitario, String substituicao_tributaria) {
        this.setCodigo(codigo);
        this.setDescricao(descricao);
        this.setUnidade(unidade);
        this.setValor_unitario(valor_unitario);
        this.setSubstituicao_tributaria(substituicao_tributaria);
    }

    public Object getSubstituicao_tributaria() {
        return substituicao_tributaria;
    }

    public void setSubstituicao_tributaria(String substituicao_tributaria) {
        this.substituicao_tributaria = substituicao_tributaria;
    }

    public Double getValor_unitario() {
        return valor_unitario;
    }

    public void setValor_unitario(Double valor_unitario) {
        this.valor_unitario = valor_unitario;
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
