package com.example.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TestVenda {

    private void rodarTestarRetorno(String expected, Venda venda) {

		// action
		String retorno = venda.dadosVenda();

		// assertion
		assertEquals(expected, retorno);
	}

	private void verificarCampoObrigatorio(String mensagemEsperada, Venda venda) {
		try {
			venda.dadosVenda();
		} catch (RuntimeException e) {
			assertEquals(mensagemEsperada, e.getMessage());
		}
	}
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
    

	Loja LOJA_COMPLETA = new Loja(NOME_LOJA,
				new Endereco(LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, MUNICIPIO, ESTADO, CEP), TELEFONE, OBSERVACAO,
				CNPJ, INSCRICAO_ESTADUAL);

	//testes venda

	Calendar datahora = new GregorianCalendar(2015,10-1,29,11,9,47);
	String ccf = "021784";
	String coo = "035804";	

	//venda
	String TEXTO_ESPERADO_VENDA = "29/10/2015 11:09:47V CCF:021784 COO:035804";
	@Test

	public void test_venda(){
		rodarTestarRetorno(TEXTO_ESPERADO_VENDA,LOJA_COMPLETA.vender(datahora,ccf,coo));		
	}
	
	//venda sem ccf
	@Test
	public void test_venda_valida_ccf(){
		Venda VENDA_CCF_VAZIO = LOJA_COMPLETA.vender(datahora,"",coo);
		verificarCampoObrigatorio("O campo ccf da venda não é valido", VENDA_CCF_VAZIO);
		Venda VENDA_CCF_NULO = LOJA_COMPLETA.vender(datahora,null,coo);
		verificarCampoObrigatorio("O campo ccf da venda não é valido", VENDA_CCF_NULO);
	}
	//venda sem coo
	@Test
	public void test_venda_valida_coo(){
		Venda VENDA_COO_VAZIO = LOJA_COMPLETA.vender(datahora,ccf,"");
		verificarCampoObrigatorio("O campo coo da venda não é valido", VENDA_COO_VAZIO);
		Venda VENDA_COO_NULO = LOJA_COMPLETA.vender(datahora,ccf,null);
		verificarCampoObrigatorio("O campo coo da venda não é valido", VENDA_COO_NULO);
	}
    
    //tests dados itens
    public void verifica_campo_obrigatorio_itens_venda(String mensagemEsperada, Venda venda){
        try {
			venda.dadosItens();
		} catch (RuntimeException e) {
			assertEquals(mensagemEsperada, e.getMessage());
		}        
    }

    private String BREAK = System.lineSeparator();

    Produto produto1 = new Produto(123456, "Produto1", "kg", 4.35, "");
    Produto produto2 = new Produto(234567, "Produto2", "m", 1.01, "");

    String TEXTO_ESPERADO_DADOS_ITEM_fUNCIONAL = "ITEM CODIGO DESCRICAO QTD UN VL UNIT(R$) ST VL ITEM(R$)" + BREAK +
    "1 123456 Produto1 2 kg 4.35  8.70" + BREAK +
    "2 234567 Produto2 4 m 1.01  4.04";    

    //dados_itens
    @Test
    public void test_itens_venda(){
        Venda venda = LOJA_COMPLETA.vender(datahora,ccf,coo);
        venda.adicionarItem(1,produto1,2);
        venda.adicionarItem(2,produto2,4);
        assertEquals(TEXTO_ESPERADO_DADOS_ITEM_fUNCIONAL, venda.dadosItens());
    }

    //Venda sem itens - o cupom fiscal não pode ser impresso
    @Test
    public void test_venda_sem_itens(){
        Venda VENDA_SEM_ITENS = LOJA_COMPLETA.vender(datahora,ccf,coo);
        verifica_campo_obrigatorio_itens_venda("Não há itens na venda para que possa ser impressa", VENDA_SEM_ITENS);
    }

    //Venda com dois itens diferentes apontando para o mesmo produto - lança erro ao adicionar o item com produto repetido
    @Test
    public void test_venda_2_itens_mesmo_produto(){
        Venda VENDA_2_ITENS_MESMO_PRODUTO = LOJA_COMPLETA.vender(datahora,ccf,coo);
        VENDA_2_ITENS_MESMO_PRODUTO.adicionarItem(1,produto1,2);
        try{
            VENDA_2_ITENS_MESMO_PRODUTO.adicionarItem(2,produto1,3);
        }catch (RuntimeException e) {
            assertEquals("A venda ja possui um item com o produto", e.getMessage());
        }
    }

    //Item de Venda com quantidade zero ou negativa - não pode ser adicionado na venda
    @Test
    public void test_venda_itens_quant_0(){
        Venda VendaItemQuant0 = LOJA_COMPLETA.vender(datahora,ccf,coo);
        try{
            VendaItemQuant0.adicionarItem(1,produto1,0);
        }catch (RuntimeException e) {
            assertEquals( "Itens com quantidade invalida (0 ou negativa)", e.getMessage());
        }
    }

    //Produto com valor unitário zero ou negativo - item não pode ser adicionado na venda com produto nesse estado
    @Test
    public void test_venda_iten_produto_sem_valor(){
        Venda VENDA_ITEM_PRODUTO_SEM_VALOR = LOJA_COMPLETA.vender(datahora,ccf,coo);
        Produto PRODUTO_SEM_VALOR = new Produto(000000, "Produto0", "nenhum", 0.0, "");
        try{
            VENDA_ITEM_PRODUTO_SEM_VALOR.adicionarItem(1,PRODUTO_SEM_VALOR,1);
        }catch (RuntimeException e) {
            assertEquals("Produto com valor invalido (0 ou negativo)", e.getMessage());
        }
    }   

    //test pagamento 
    Double valorTotal = 12.74;
    Double pagamento = 20.00;
    Double troco = 7.26;
    String tipoDinheiro = "Dinheiro";
    String tipoCredito = "Cartao de credito";
    String tipoDebito = "Cartao de debito";

    //pagamento sem troco
    String PagamentoSemTrocoDinheiro = "Dinheiro 12.74";
    String PagamentoSemTrocoCredito = "Cartao de credito 12.74";
    String PagamentoSemTrocoDebito = "Cartao de debito 12.74";
    @Test
    public void testPagamentoSemTroco(){
        Pagamento pagamentoDin = new Pagamento(valorTotal);
        pagamentoDin.efetuar(valorTotal, tipoDinheiro);        
        assertEquals(PagamentoSemTrocoDinheiro, pagamentoDin.imprimir());
        Pagamento pagamentoCre = new Pagamento(valorTotal);
        pagamentoCre.efetuar(valorTotal, tipoCredito);        
        assertEquals(PagamentoSemTrocoCredito, pagamentoCre.imprimir());
        Pagamento pagamentoDeb = new Pagamento(valorTotal);
        pagamentoDeb.efetuar(valorTotal, tipoDebito);        
        assertEquals(PagamentoSemTrocoDebito, pagamentoDeb.imprimir());
        
    }  
    //pagamento com troco
    String PagamentoComTroco = "Dinheiro 20.00" + BREAK +
    "Troco R$ 7.26";
    @Test
    public void testPagamentoComTroco(){
        Pagamento pagamentoTroco = new Pagamento(valorTotal);
        pagamentoTroco.efetuar(pagamento, tipoDinheiro);        
        assertEquals(PagamentoComTroco, pagamentoTroco.imprimir());
    }
    //pagamento tipo invalido
    @Test
    public void testPagamentoTipoInvalido(){
        Pagamento pagamentoTipoIvalido = new Pagamento(valorTotal);
        try{
            pagamentoTipoIvalido.efetuar(pagamento, "");
        }catch(RuntimeException e){        
            assertEquals("Tipo de pagamento não valido", e.getMessage());
        }
    }    

    //pagamento insuficiente
    @Test
    public void testPagamentoInsuficiente(){
        Pagamento pagamentoTipoIvalido = new Pagamento(valorTotal);
        try{
            pagamentoTipoIvalido.efetuar(pagamento - 1.0, tipoDinheiro);
        }catch(RuntimeException e){        
            assertEquals("Valor recebido insuficiente", e.getMessage());
        }
    }
    //impressão sem estar pago
    @Test
    public void testPagamentoNaoPago(){
        Pagamento pagamentoNaoPago = new Pagamento(valorTotal);
        try{
            pagamentoNaoPago.imprimir();
        }catch(RuntimeException e){        
            assertEquals("O pagamento ainda não foi efetuado", e.getMessage());
        }
    }
    //testes imposto
    String lei = "Lei 12.741";
    Double taxaFederal = 7.54;
    Double taxaEstadual = 4.81;
    Imposto IMPOSTO = new Imposto(lei, taxaFederal, taxaEstadual);
    //dadosImposto da venda
    Loja LOJA_COMPLETA_COM_IMPOSTO = new Loja(NOME_LOJA,
    new Endereco(LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, MUNICIPIO, ESTADO, CEP), TELEFONE, OBSERVACAO,
    CNPJ, INSCRICAO_ESTADUAL,IMPOSTO);
    String IMPOSTO_ESPERADO = "Lei 12.741, Valor aprox., Imposto F=0.96 (7.54%), E=0.61 (4.81%)";
    
    @Test
    public void testVendaDadosImposto(){        
        Venda venda = LOJA_COMPLETA_COM_IMPOSTO.vender(datahora,ccf,coo);
        venda.adicionarItem(1,produto1,2);
        venda.adicionarItem(2,produto2,4);
        assertEquals(IMPOSTO_ESPERADO,venda.dadosImposto());
    }
    //imposto com taxaFederal invalida
    @Test
    public void testImpostoTaxaFederalInvalida(){        
        try {
            Imposto impostoTaxaFederalInvalida = new Imposto(lei, -10.0, taxaEstadual);
        } catch (RuntimeException e) {
            assertEquals("Taxa federal invalida", e.getMessage());
        }
    }

    //imposto com taxaEstadual invalida
    @Test
    public void testImpostoTaxaEstadualInvalida(){        
        try {
            Imposto impostoTaxaEstadualInvalida = new Imposto(lei, taxaFederal, -10.0);
        } catch (RuntimeException e) {
            assertEquals("Taxa estadual invalida", e.getMessage());
        }
    }

    //cupom complemento
    String TEXTO_ESPERADO_CUPOM_FISCAL = "Loja 1" + BREAK +
    "Log 1, 10 C1" + BREAK +
    "Bai 1 - Mun 1 - E1" + BREAK +
    "CEP:11111-111 Tel (11) 1111-1111" + BREAK +
    "Obs 1" + BREAK +
    "CNPJ: 11.111.111/1111-11" + BREAK +
    "IE: 123456789" + BREAK +
    "------------------------------" + BREAK +
    "29/10/2015 11:09:47V CCF:021784 COO:035804" + BREAK +
    "   CUPOM FISCAL" + BREAK +
    "ITEM CODIGO DESCRICAO QTD UN VL UNIT(R$) ST VL ITEM(R$)" + BREAK +
    "1 123456 Produto1 2 kg 4.35  8.70" + BREAK +
    "2 234567 Produto2 4 m 1.01  4.04" + BREAK +
    "------------------------------" + BREAK +
    "TOTAL R$ 12.74" + BREAK +
    "Dinheiro 20.00" + BREAK +
    "Troco R$ 7.26" + BREAK +
    "Lei 12.741, Valor aprox., Imposto F=0.96 (7.54%), E=0.61 (4.81%)";
    @Test
    public void test_venda_imprimir_cupom(){
        Venda venda = LOJA_COMPLETA_COM_IMPOSTO.vender(datahora,ccf,coo);
        venda.adicionarItem(1,produto1,2);
        venda.adicionarItem(2,produto2,4);        
        venda.pagar(pagamento, tipoDinheiro);
        assertEquals(TEXTO_ESPERADO_CUPOM_FISCAL, venda.imprimirCupom());
    }
}