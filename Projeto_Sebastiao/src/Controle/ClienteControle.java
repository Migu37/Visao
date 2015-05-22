package Controle;

import java.util.*;

import Entidade.ClienteEntidade;
import AcessoDados.ClienteAcessoDados;

public class ClienteControle{
	
	public int  salvar(ClienteEntidade cliente){
		int codigo = new ClienteAcessoDados().salvar(cliente);
		return codigo;
	}

	public void atualizar(ClienteEntidade cliente){
		new ClienteAcessoDados().atualizar(cliente);	
	}

	public void excluir(ClienteEntidade cliente){
		new ClienteAcessoDados().excluir(cliente);
	}

	public List<ClienteEntidade> listar() {
		return new ClienteAcessoDados().listar();
	}

	public ClienteEntidade buscaRegistro(int valor) {
		return new ClienteAcessoDados().buscaRegistro(valor);
	}
	
}



