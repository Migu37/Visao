package Visao;

import java.util.List;
import java.util.Scanner;

import Entidade.ProdutoEntidade;
import Controle.ProdutoControle;

public class ProdutoVisao{
	
	public void Gerenciar(){
		int opcao, codigo;
		Scanner teclado = new Scanner(System.in);
		List<ProdutoEntidade> produtos = null;
		ProdutoEntidade produto = null;
		ProdutoControle controle = new ProdutoControle();
				
		do 
		{ 
			System.out.println("Gerenciar Produto");
			System.out.println("Opções");		
			System.out.println("1 - Cadastrar");
			System.out.println("2 - Alterar");
			System.out.println("3 - Excluir");
			System.out.println("4 - Listar");
			System.out.println("0 - Voltar");
			System.out.println("\nDigite sua opção: ");
			opcao = teclado.nextInt();
			
			switch (opcao)
			{
				
				case 1:  
					produto = new ProdutoEntidade();
					System.out.println("Nome: ");
					produto.setNome(teclado.next());
					System.out.println("Preço: ");
					produto.setPreco(teclado.nextFloat());
					System.out.println("Estoque: ");
					produto.setEstoque(teclado.nextInt());						
					controle.salvar(produto);		
					break;
				case 2:
					produtos = controle.listar();
					for (ProdutoEntidade p: produtos) {
						System.out.println("Codigo: " + p.getCodigo() + 
								           " Nome: " + p.getNome() + 
								           " Preço: " + p.getPreco() +
								           " Estoque: " + p.getEstoque());
					}
					System.out.println("Codigo para alteracao: ");
					codigo = (teclado.nextInt());
					produto = controle.buscaRegistro(codigo);
					System.out.println("Nome: ");
					produto.setNome(teclado.next());
					System.out.println("Preco: ");
					produto.setPreco(teclado.nextFloat());
					System.out.println("Estoque: ");
					produto.setEstoque(teclado.nextInt());											
					controle.atualizar(produto);
					break;
					
				case 3:
					produtos = controle.listar();
					for (ProdutoEntidade p: produtos) {
						System.out.println("Codigo: " + p.getCodigo() + 
						                   " Nome: " + p.getNome() + 
						                   " Preço: " + p.getPreco()+
										   " Estoque: " + p.getEstoque());
					}
					System.out.println("Codigo para exclusao: ");
					codigo = (teclado.nextInt());
					produto = controle.buscaRegistro(codigo);
					controle.excluir(produto);					
					break;					
					
				case 4:
					produtos = controle.listar();
					for (ProdutoEntidade p: produtos) {
						System.out.println("Codigo: " + p.getCodigo() + 
				                           " Nome: " + p.getNome() + 
						                   " Preço: " + p.getPreco()+
										   " Estoque: " + p.getEstoque());
					}
					break;
					
				default:
					System.out.println("Opção inválida.");
				break;					
			}

		} 
		while (opcao != 0);
		

	}

}
