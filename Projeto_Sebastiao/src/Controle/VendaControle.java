package Controle;

import java.util.*;

import Entidade.VendaEntidade;
import AcessoDados.VendaAcessoDados;

public class VendaControle{
	
	public int  salvar(VendaEntidade venda){
		int codigo = new VendaAcessoDados().salvar(venda);
		return codigo;
	}

	public void atualizar(VendaEntidade venda){
		new VendaAcessoDados().atualizar(venda);	
	}

	public void excluir(VendaEntidade venda){
		new VendaAcessoDados().excluir(venda);
	}

	public List<VendaEntidade> listar() {
		return new VendaAcessoDados().listar();
	}

	public VendaEntidade buscaRegistro(int valor) {
		return new VendaAcessoDados().buscaRegistro(valor);
	}
	
}



