package Controle;

import java.util.*;

import Entidade.VendaItemEntidade;
import AcessoDados.VendaItemAcessoDados;

public class VendaItemControle{
	
	public int salvar(VendaItemEntidade vendaItem){
		int codigo = new VendaItemAcessoDados().salvar(vendaItem);
		return codigo;
	}

	public void atualizar(VendaItemEntidade vendaItem){
		new VendaItemAcessoDados().atualizar(vendaItem);	
	}

	public void excluir(VendaItemEntidade vendaItem){
		new VendaItemAcessoDados().excluir(vendaItem);
	}

	public List<VendaItemEntidade> listar() {
		return new VendaItemAcessoDados().listar();
	}

	public VendaItemEntidade buscaRegistro(int valor) {
		return new VendaItemAcessoDados().buscaRegistro(valor);
	}
	
}



