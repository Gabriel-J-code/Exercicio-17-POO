package com.example.project;

public class ItemVenda {

    private Venda venda;
    private int item;
    private Produto produto;
    private int quantidade;

    public ItemVenda(Venda venda, int item, Produto produto, int quantidade) {
        this.setVenda(venda);
        this.setItem(item);
        this.setProduto(produto);
        this.setQuantidade(quantidade);
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
