package com.example.project;

public class Endereco {

    private String logradouro;
    private int numero;
    private String complemento;
    private String bairro;
    private String municipio;
    private String estado;
    private String cep;

    public Endereco(String logradouro, int numero, String complemento, String bairro, String municipio, String estado, String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.municipio = municipio;
        this.estado = estado;
        this.cep = cep;
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    public int getNumero() {
        return this.numero;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public String getBairro() {
        return this.bairro;
    }

    public String getMunicipio() {
        return this.municipio;
    }

    public String getEstado() {
        return this.estado;
    }

    public String getCep() {
        return this.cep;
    }

    public String dadosEndereco(){        

        validarCamposObrigatorios();

        String _NUMERO = "s/n";
        if (this.getNumero() > 0){
            _NUMERO = Integer.toString(this.getNumero());
        }
        
        String _COMPLEMENTO = "";
        if (!isEmpty(this.getComplemento())){
            _COMPLEMENTO = " " + this.getComplemento();
        }
    
        String _BAIRRO = "";
        if(!isEmpty(this.getBairro())){
            _BAIRRO = this.getBairro() + " - " ;
        }
    
        String _CEP = "";
        if(!isEmpty(this.getCep())){
            _CEP = "CEP:" + this.getCep();
        }

        String BREAK = System.lineSeparator();

        String nota = "";
        nota += String.format("%s, %s%s",this.getLogradouro(),_NUMERO,_COMPLEMENTO) + BREAK;
        nota += String.format("%s%s - %s",_BAIRRO,this.getMunicipio(),this.getEstado()) + BREAK;
        nota += String.format("%s",_CEP);
            
        return nota;
    }

    public void validarCamposObrigatorios(){

		if (isEmpty(this.getLogradouro())){
			throw new RuntimeException("O campo logradouro do endereço é obrigatório");
		}		
		if (isEmpty(this.getMunicipio())){
			throw new RuntimeException("O campo município do endereço é obrigatório");
		}
		if (isEmpty(this.getEstado())){
			throw new RuntimeException("O campo estado do endereço é obrigatório");
		} 		
    }

    private static boolean isEmpty(String s){
		if (s == null) return true;
		if (s.equals("")) return true;
		return false;
	}


}