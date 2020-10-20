package com.example.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestLoja {

	private String BREAK = System.lineSeparator();

	private String TEXTO_ESPERADO_LOJA_COMPLETA = "Loja 1" + BREAK + "Log 1, 10 C1" + BREAK + "Bai 1 - Mun 1 - E1"
			+ BREAK + "CEP:11111-111 Tel (11) 1111-1111" + BREAK + "Obs 1" + BREAK + "CNPJ: 11.111.111/1111-11" + BREAK
			+ "IE: 123456789";

	private String TEXTO_ESPERADO_SEM_NUMERO = "Loja 1" + BREAK + "Log 1, s/n C1" + BREAK + "Bai 1 - Mun 1 - E1" + BREAK
			+ "CEP:11111-111 Tel (11) 1111-1111" + BREAK + "Obs 1" + BREAK + "CNPJ: 11.111.111/1111-11" + BREAK
			+ "IE: 123456789";

	private String TEXTO_ESPERADO_SEM_COMPLEMENTO = "Loja 1" + BREAK + "Log 1, 10" + BREAK + "Bai 1 - Mun 1 - E1"
			+ BREAK + "CEP:11111-111 Tel (11) 1111-1111" + BREAK + "Obs 1" + BREAK + "CNPJ: 11.111.111/1111-11" + BREAK
			+ "IE: 123456789";

	private String TEXTO_ESPERADO_SEM_BAIRRO = "Loja 1" + BREAK + "Log 1, 10 C1" + BREAK + "Mun 1 - E1" + BREAK
			+ "CEP:11111-111 Tel (11) 1111-1111" + BREAK + "Obs 1" + BREAK + "CNPJ: 11.111.111/1111-11" + BREAK
			+ "IE: 123456789";

	private String TEXTO_ESPERADO_SEM_CEP = "Loja 1" + BREAK + "Log 1, 10 C1" + BREAK + "Bai 1 - Mun 1 - E1" + BREAK
			+ "Tel (11) 1111-1111" + BREAK + "Obs 1" + BREAK + "CNPJ: 11.111.111/1111-11" + BREAK + "IE: 123456789";

	private String TEXTO_ESPERADO_SEM_TELEFONE = "Loja 1" + BREAK + "Log 1, 10 C1" + BREAK + "Bai 1 - Mun 1 - E1"
			+ BREAK + "CEP:11111-111" + BREAK + "Obs 1" + BREAK + "CNPJ: 11.111.111/1111-11" + BREAK + "IE: 123456789";

	private String TEXTO_ESPERADO_SEM_OBSERVACAO = "Loja 1" + BREAK + "Log 1, 10 C1" + BREAK + "Bai 1 - Mun 1 - E1"
			+ BREAK + "CEP:11111-111 Tel (11) 1111-1111" + BREAK + "" + BREAK + "CNPJ: 11.111.111/1111-11" + BREAK
			+ "IE: 123456789";

	private String TEXTO_ESPERADO_SEM_NUMERO_SEM_COMPLEMENTO = "Loja 1" + BREAK + "Log 1, s/n" + BREAK
			+ "Bai 1 - Mun 1 - E1" + BREAK + "CEP:11111-111 Tel (11) 1111-1111" + BREAK + "Obs 1" + BREAK
			+ "CNPJ: 11.111.111/1111-11" + BREAK + "IE: 123456789";

	private String TEXTO_ESPERADO_SEM_NUMERO_SEM_COMPLEMENTO_SEM_BAIRRO = "Loja 1" + BREAK + "Log 1, s/n" + BREAK
			+ "Mun 1 - E1" + BREAK + "CEP:11111-111 Tel (11) 1111-1111" + BREAK + "Obs 1" + BREAK
			+ "CNPJ: 11.111.111/1111-11" + BREAK + "IE: 123456789";

	private String NOME_LOJA = "Loja 1";
	private String LOGRADOURO = "Log 1";
	private int NUMERO = 10;
	private String COMPLEMENTO = "C1";
	private String BAIRRO = "Bai 1";
	private String MUNICIPIO = "Mun 1";
	private String ESTADO = "E1";
	private String CEP = "11111-111";
	private String TELEFONE = "(11) 1111-1111";
	private String OBSERVACAO = "Obs 1";
	private String CNPJ = "11.111.111/1111-11";
	private String INSCRICAO_ESTADUAL = "123456789";

	@Test
	public void lojaCompleta() {
		Loja lojaCompleta = new Loja(NOME_LOJA,
				new Endereco(LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, MUNICIPIO, ESTADO, CEP), TELEFONE, OBSERVACAO,
				CNPJ, INSCRICAO_ESTADUAL);
		rodarTestarRetorno(TEXTO_ESPERADO_LOJA_COMPLETA, lojaCompleta);
	}

	@Test
	public void validarNome() {
		Loja nomeVazio = new Loja("", new Endereco(LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, MUNICIPIO, ESTADO, CEP),
				TELEFONE, OBSERVACAO, CNPJ, INSCRICAO_ESTADUAL);
		verificarCampoObrigatorio("O campo nome da loja é obrigatório", nomeVazio);

		Loja nomeNulo = new Loja(null, new Endereco(LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, MUNICIPIO, ESTADO, CEP),
				TELEFONE, OBSERVACAO, CNPJ, INSCRICAO_ESTADUAL);
		verificarCampoObrigatorio("O campo nome da loja é obrigatório", nomeNulo);
	}

	@Test
	public void validarLogradouro() {
		Loja logradouroVazio = new Loja(NOME_LOJA,
				new Endereco("", NUMERO, COMPLEMENTO, BAIRRO, MUNICIPIO, ESTADO, CEP), TELEFONE, OBSERVACAO, CNPJ,
				INSCRICAO_ESTADUAL);
		verificarCampoObrigatorio("O campo logradouro do endereço é obrigatório", logradouroVazio);

		Loja logradouroNulo = new Loja(NOME_LOJA,
				new Endereco(null, NUMERO, COMPLEMENTO, BAIRRO, MUNICIPIO, ESTADO, CEP), TELEFONE, OBSERVACAO, CNPJ,
				INSCRICAO_ESTADUAL);
		verificarCampoObrigatorio("O campo logradouro do endereço é obrigatório", logradouroNulo);
	}

	@Test
	public void validarNumero() {
		Loja numeroZero = new Loja(NOME_LOJA, new Endereco(LOGRADOURO, 0, COMPLEMENTO, BAIRRO, MUNICIPIO, ESTADO, CEP),
				TELEFONE, OBSERVACAO, CNPJ, INSCRICAO_ESTADUAL);
		rodarTestarRetorno(TEXTO_ESPERADO_SEM_NUMERO, numeroZero);

		// Não compila porque a tipagem não permite esse erro
		// Loja numeroNulo = new Loja(NOME_LOJA,
		// new Endereco(LOGRADOURO, null, COMPLEMENTO, BAIRRO,
		// MUNICIPIO, ESTADO, CEP), TELEFONE,
		// OBSERVACAO, CNPJ, INSCRICAO_ESTADUAL);
		// rodarTestarRetorno(TEXTO_ESPERADO_SEM_NUMERO, numeroNulo);
	}

	@Test
	public void validarComplemento() {
		Loja complementoVazio = new Loja(NOME_LOJA,
				new Endereco(LOGRADOURO, NUMERO, "", BAIRRO, MUNICIPIO, ESTADO, CEP), TELEFONE, OBSERVACAO, CNPJ,
				INSCRICAO_ESTADUAL);
		rodarTestarRetorno(TEXTO_ESPERADO_SEM_COMPLEMENTO, complementoVazio);

		Loja complementoNulo = new Loja(NOME_LOJA,
				new Endereco(LOGRADOURO, NUMERO, null, BAIRRO, MUNICIPIO, ESTADO, CEP), TELEFONE, OBSERVACAO, CNPJ,
				INSCRICAO_ESTADUAL);
		rodarTestarRetorno(TEXTO_ESPERADO_SEM_COMPLEMENTO, complementoNulo);
	}

	@Test
	public void validarBairro() {
		Loja bairroVazio = new Loja(NOME_LOJA,
				new Endereco(LOGRADOURO, NUMERO, COMPLEMENTO, "", MUNICIPIO, ESTADO, CEP), TELEFONE, OBSERVACAO, CNPJ,
				INSCRICAO_ESTADUAL);
		rodarTestarRetorno(TEXTO_ESPERADO_SEM_BAIRRO, bairroVazio);

		Loja bairroNulo = new Loja(NOME_LOJA,
				new Endereco(LOGRADOURO, NUMERO, COMPLEMENTO, null, MUNICIPIO, ESTADO, CEP), TELEFONE, OBSERVACAO, CNPJ,
				INSCRICAO_ESTADUAL);
		rodarTestarRetorno(TEXTO_ESPERADO_SEM_BAIRRO, bairroNulo);
	}

	@Test
	public void validarMunicipio() {
		Loja municipioVazio = new Loja(NOME_LOJA,
				new Endereco(LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, "", ESTADO, CEP), TELEFONE, OBSERVACAO, CNPJ,
				INSCRICAO_ESTADUAL);
		verificarCampoObrigatorio("O campo município do endereço é obrigatório", municipioVazio);

		Loja municipioNulo = new Loja(NOME_LOJA,
				new Endereco(LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, null, ESTADO, CEP), TELEFONE, OBSERVACAO, CNPJ,
				INSCRICAO_ESTADUAL);
		verificarCampoObrigatorio("O campo município do endereço é obrigatório", municipioNulo);
	}

	@Test
	public void validarEstado() {
		Loja estadoVazio = new Loja(NOME_LOJA,
				new Endereco(LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, MUNICIPIO, "", CEP), TELEFONE, OBSERVACAO, CNPJ,
				INSCRICAO_ESTADUAL);
		verificarCampoObrigatorio("O campo estado do endereço é obrigatório", estadoVazio);

		Loja estadoNulo = new Loja(NOME_LOJA,
				new Endereco(LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, MUNICIPIO, null, CEP), TELEFONE, OBSERVACAO, CNPJ,
				INSCRICAO_ESTADUAL);
		verificarCampoObrigatorio("O campo estado do endereço é obrigatório", estadoNulo);
	}

	@Test
	public void validarCep() {
		Loja cepVazio = new Loja(NOME_LOJA,
				new Endereco(LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, MUNICIPIO, ESTADO, ""), TELEFONE, OBSERVACAO,
				CNPJ, INSCRICAO_ESTADUAL);
		rodarTestarRetorno(TEXTO_ESPERADO_SEM_CEP, cepVazio);

		Loja cepNulo = new Loja(NOME_LOJA,
				new Endereco(LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, MUNICIPIO, ESTADO, null), TELEFONE, OBSERVACAO,
				CNPJ, INSCRICAO_ESTADUAL);
		rodarTestarRetorno(TEXTO_ESPERADO_SEM_CEP, cepNulo);
	}

	@Test
	public void validarTelefone() {
		Loja telefoneVazio = new Loja(NOME_LOJA,
				new Endereco(LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, MUNICIPIO, ESTADO, CEP), "", OBSERVACAO, CNPJ,
				INSCRICAO_ESTADUAL);
		rodarTestarRetorno(TEXTO_ESPERADO_SEM_TELEFONE, telefoneVazio);

		Loja telefoneNulo = new Loja(NOME_LOJA,
				new Endereco(LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, MUNICIPIO, ESTADO, CEP), null, OBSERVACAO, CNPJ,
				INSCRICAO_ESTADUAL);
		rodarTestarRetorno(TEXTO_ESPERADO_SEM_TELEFONE, telefoneNulo);
	}

	@Test
	public void validarObservacao() {
		Loja observacaoVazia = new Loja(NOME_LOJA,
				new Endereco(LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, MUNICIPIO, ESTADO, CEP), TELEFONE, "", CNPJ,
				INSCRICAO_ESTADUAL);
		rodarTestarRetorno(TEXTO_ESPERADO_SEM_OBSERVACAO, observacaoVazia);

		Loja observacaoNula = new Loja(NOME_LOJA,
				new Endereco(LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, MUNICIPIO, ESTADO, CEP), TELEFONE, null, CNPJ,
				INSCRICAO_ESTADUAL);
		rodarTestarRetorno(TEXTO_ESPERADO_SEM_OBSERVACAO, observacaoNula);
	}

	@Test
	public void validarCnpj() {
		Loja cnpjVazio = new Loja(NOME_LOJA,
				new Endereco(LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, MUNICIPIO, ESTADO, CEP), TELEFONE, OBSERVACAO, "",
				INSCRICAO_ESTADUAL);
		verificarCampoObrigatorio("O campo cnpj da loja é obrigatório", cnpjVazio);

		Loja cnpjNulo = new Loja(NOME_LOJA,
				new Endereco(LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, MUNICIPIO, ESTADO, CEP), TELEFONE, OBSERVACAO,
				null, INSCRICAO_ESTADUAL);
		verificarCampoObrigatorio("O campo cnpj da loja é obrigatório", cnpjNulo);
	}

	@Test
	public void validarInscricaoEstadual() {
		Loja ieVazia = new Loja(NOME_LOJA,
				new Endereco(LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, MUNICIPIO, ESTADO, CEP), TELEFONE, OBSERVACAO,
				CNPJ, "");
		verificarCampoObrigatorio("O campo inscrição estadual da loja é obrigatório", ieVazia);

		Loja ieNula = new Loja(NOME_LOJA, new Endereco(LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, MUNICIPIO, ESTADO, CEP),
				TELEFONE, OBSERVACAO, CNPJ, "");
		verificarCampoObrigatorio("O campo inscrição estadual da loja é obrigatório", ieNula);
	}

	@Test
	public void validarNumeroComplemento() {
		Loja semNumeroSemComplemento = new Loja(NOME_LOJA,
				new Endereco(LOGRADOURO, 0, null, BAIRRO, MUNICIPIO, ESTADO, CEP), TELEFONE, OBSERVACAO, CNPJ,
				INSCRICAO_ESTADUAL);
		rodarTestarRetorno(TEXTO_ESPERADO_SEM_NUMERO_SEM_COMPLEMENTO, semNumeroSemComplemento);
	}

	@Test
	public void validarNumeroComplementoBairro() {
		Loja semNumeroSemComplementoBairro = new Loja(NOME_LOJA,
				new Endereco(LOGRADOURO, 0, null, null, MUNICIPIO, ESTADO, CEP), TELEFONE, OBSERVACAO, CNPJ,
				INSCRICAO_ESTADUAL);
		rodarTestarRetorno(TEXTO_ESPERADO_SEM_NUMERO_SEM_COMPLEMENTO_SEM_BAIRRO, semNumeroSemComplementoBairro);
	}

	@Test
	public void exercicio02_Customizado() {
		// Defina seus próprios valores para as variáveis a seguir
		String nomeLoja = "Tropical";
		String logradouro = "Rua siqueira Campos";
		int numero = 580;
		String complemento = "";
		String bairro = "Centro";
		String municipio = "Paulista";
		String estado = "Pernambuco";
		String cep = "53401-320";
		String telefone = "(81) 3438-5714";
		String observacao = "";
		String cnpj = "37.886.772/0001-82";
		String inscricaoEstadual = "4232303-79";


		Loja lojaCustomizada = new Loja(nomeLoja,
				new Endereco(logradouro, numero, complemento, bairro, municipio, estado, cep), telefone, observacao,
				cnpj, inscricaoEstadual);

		// E atualize o texto esperado abaixo
		rodarTestarRetorno("Tropical" + BREAK + 
		"Rua siqueira Campos, 580" + BREAK + 
		"Centro - Paulista - Pernambuco" + BREAK + 
		"CEP:53401-320 Tel (81) 3438-5714" + BREAK + 
		"" + BREAK + 
		"CNPJ: 37.886.772/0001-82" + BREAK + 
		"IE: 4232303-79", lojaCustomizada);
	}

	private void rodarTestarRetorno(String expected, Loja loja) {

		// action
		String retorno = loja.dadosLoja();

		// assertion
		assertEquals(expected, retorno);
	}

	private void verificarCampoObrigatorio(String mensagemEsperada, Loja loja) {
		try {
			loja.dadosLoja();
		} catch (RuntimeException e) {
			assertEquals(mensagemEsperada, e.getMessage());
		}
	}

}
