package Controle;

import java.util.*;
import Entidade.ProdutoEntidade;
import AcessoDados.ProdutoAcessoDados;

public class ProdutoControle{
	
	public int salvar(ProdutoEntidade produto){
		int codigo = new ProdutoAcessoDados().salvar(produto);
		return codigo;
	}

	public void atualizar(ProdutoEntidade produto){
		new ProdutoAcessoDados().atualizar(produto);	
	}

	public void excluir(ProdutoEntidade produto){
		new ProdutoAcessoDados().excluir(produto);
	}

	public List<ProdutoEntidade> listar() {
		return new ProdutoAcessoDados().listar();
	}

	public ProdutoEntidade buscaRegistro(int valor) {
		return new ProdutoAcessoDados().buscaRegistro(valor);
	}
	
}



