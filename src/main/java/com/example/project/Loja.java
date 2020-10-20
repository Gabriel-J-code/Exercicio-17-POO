package com.example.project;

import java.util.ArrayList;
import java.util.Calendar;

public class Loja {

    private String nomeLoja;
    private Endereco endereco;
    private String telefone;
    private String observacao;
    private String cnpj;
    private String inscricaoEstadual;
    private ArrayList<Venda> vendas;

    public Loja(String nomeLoja, Endereco endereco, String telefone, String observacao, String cnpj,
            String inscricaoEstadual) {
        this.nomeLoja = nomeLoja;
        this.endereco = endereco;
        this.telefone = telefone;
        this.observacao = observacao;
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.vendas = new ArrayList<Venda>();
    }

    public ArrayList<Venda> getVendas() {
        return vendas;
    }

    public String getNomeLoja() {
        return this.nomeLoja;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public String getObservacao() {
        return this.observacao;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public String getInscricaoEstadual() {
        return this.inscricaoEstadual;
    }

    public Endereco getEndereco() {
        return this.endereco;
    }

    public Venda vender(Calendar datahora, String ccf,String coo){
        Venda nova_venda = new Venda(this, datahora, ccf, coo);
        this.vendas.add(nova_venda);
        return nova_venda;
    }


    public String dadosLoja() {

        validarCamposObrigatorios();		
		
		String _TELEFONE = "";
		if(!isEmpty(this.getTelefone())){
			_TELEFONE = "Tel " + this.getTelefone();

			if(!isEmpty(this.endereco.getCep())){
				_TELEFONE = " " + _TELEFONE;
			}
		}		
	
		String _OBSERVACAO = "";
		if (!isEmpty(this.getObservacao())){
			_OBSERVACAO = this.getObservacao();
		}

        String BREAK = System.lineSeparator();
        
        StringBuilder nota = new StringBuilder();

		nota.append(String.format("%s",this.getNomeLoja()) + BREAK);		
		nota.append(String.format("%s%s",this.endereco.dadosEndereco(),_TELEFONE) + BREAK);
		nota.append(String.format("%s",_OBSERVACAO) + BREAK);
		nota.append(String.format("CNPJ: %s",this.getCnpj()) + BREAK);
		nota.append(String.format("IE: %s",this.getInscricaoEstadual()));
	
        return nota.toString();
    }

    public void validarCamposObrigatorios(){
        if (isEmpty(this.getNomeLoja())) {
			throw new RuntimeException("O campo nome da loja é obrigatório");		
		}		
		if (isEmpty(this.getCnpj())){
			throw new RuntimeException("O campo cnpj da loja é obrigatório");
		} 
		if (isEmpty(this.getInscricaoEstadual())){
			throw new RuntimeException("O campo inscrição estadual da loja é obrigatório");
		}
    }
    private static boolean isEmpty(String s){
		if (s == null) return true;
		if (s.equals("")) return true;
		return false;
	}

}