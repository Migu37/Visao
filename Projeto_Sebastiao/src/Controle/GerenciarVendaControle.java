package Controle;

import java.util.*;

import Entidade.VendaEntidade;
import Entidade.ProdutoEntidade;
import Entidade.VendaItemEntidade;
import AcessoDados.VendaAcessoDados;
import AcessoDados.VendaItemAcessoDados;

public class GerenciarVendaControle{
	
	public void salvar(VendaEntidade venda, List<ProdutoEntidade> produtos){
		int codigo;
		VendaItemEntidade vendaItem = new VendaItemEntidade();
		
		VendaAcessoDados vendaAcessoDados = new VendaAcessoDados();
		VendaItemAcessoDados vendaItemAcessoDados = new VendaItemAcessoDados();
		
		codigo = vendaAcessoDados.salvar(venda);
		
		for (ProdutoEntidade p: produtos) {
			vendaItem.setCodigoVenda(codigo);
			vendaItem.setCodigoProduto(p.getCodigo());
			vendaItemAcessoDados.salvar(vendaItem);
		}		
	}

	public void excluir(VendaEntidade venda){
		VendaAcessoDados vendaAcessoDados = new VendaAcessoDados();
		VendaItemAcessoDados vendaItemAcessoDados = new VendaItemAcessoDados();
		vendaItemAcessoDados.excluirTodosItens(venda);
		vendaAcessoDados.excluir(venda);
	}
	
	public List<VendaItemEntidade> listarVendaItem(VendaEntidade venda){
		VendaItemAcessoDados vendaItemAcessoDados = new VendaItemAcessoDados();		
		List<VendaItemEntidade> vendaItens = new ArrayList<VendaItemEntidade>();   
		vendaItens = vendaItemAcessoDados.listarVendaItens(venda);		
		return vendaItens;
	}		
}




