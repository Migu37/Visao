package Visao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Entidade.ClienteEntidade;
import Entidade.ProdutoEntidade;
import Entidade.VendaItemEntidade;
import Controle.ProdutoControle;
import Entidade.VendaEntidade;
import Controle.ClienteControle;
import Controle.VendaControle;
import Controle.GerenciarVendaControle;

public class VendaVisao{
	
	public void Gerenciar(){
		int opcao, codigo;
		String resposta;
		Scanner teclado = new Scanner(System.in);
		List<ProdutoEntidade> produtos = null;
		List<VendaEntidade> vendas = null;
		List<ClienteEntidade> clientes = null;
		List<VendaItemEntidade> vendaItens = null;
		VendaEntidade venda = null;
		ProdutoEntidade produto = null;
		ClienteEntidade cliente = null;
		GerenciarVendaControle gerenciarVendaControle = new GerenciarVendaControle();
				
		do 
		{ 
			System.out.println("Gerenciar Vendas");
			System.out.println("Opções");		
			System.out.println("1 - Realizar Venda");
			System.out.println("2 - Cancelar Venda");
			System.out.println("3 - Listar Vendas");
			System.out.println("0 - Voltar");
			System.out.println("\nDigite sua opção: ");
			opcao = teclado.nextInt();
			
			switch (opcao)
			{
				
				case 1:  
					venda = new VendaEntidade();
					
					System.out.println(" - Clientes");
					clientes = new ClienteControle().listar();
					
					for (ClienteEntidade c: clientes) {
						System.out.println("Codigo: " + c.getCodigo() + 
				                           " Nome: " + c.getNome() + 
				                           " Cliente: " + c.getEmail());
					}
					
					System.out.println("Digite o cód. do Cliente: ");
					venda.setCodigoCliente(teclado.nextInt());
					
					System.out.println(" - Produtos");
					produtos = new ProdutoControle().listar();

					
					for (ProdutoEntidade p: produtos) {
						System.out.println("Codigo: " + p.getCodigo() + 
				                           " Nome: " + p.getNome() + 
						                   " Preço: " + p.getPreco()+
										   " Estoque: " + p.getEstoque());
					}
					
					List<ProdutoEntidade> produtosVendidos = new ArrayList<ProdutoEntidade>();
					
					do{
						System.out.println("Digite o cód. do produto: ");
						codigo = (teclado.nextInt());
						produtosVendidos.add(new ProdutoControle().buscaRegistro(codigo));
						System.out.println("Deseja incluir outro produto (S ou N): ");
						resposta = (teclado.next());
					}
					while(resposta.equalsIgnoreCase("S"));
					
					gerenciarVendaControle.salvar(venda, produtosVendidos);
					
					break;
				case 2:
					
					vendas = new VendaControle().listar();
					for (VendaEntidade v: vendas) {
						System.out.println("Codigo: " + v.getCodigo() + 
								           " Cliente: " + v.getCodigoCliente());
					}
					System.out.println("Codigo para cancelamento: ");
					codigo = (teclado.nextInt());
					venda =  new VendaControle().buscaRegistro(codigo);
					gerenciarVendaControle.excluir(venda);
					break;
					
				case 3:
					vendas =  new VendaControle().listar();
					for (VendaEntidade v: vendas) {
						cliente = new ClienteControle().buscaRegistro(v.getCodigoCliente());
						System.out.println("Codigo: " + v.getCodigo() + 
								           " Cliente: " + cliente.getNome());
						
						vendaItens = gerenciarVendaControle.listarVendaItem(v);

						System.out.println("\tProdutos");
						for (VendaItemEntidade vi: vendaItens){
							produto = new ProdutoControle().buscaRegistro(vi.getCodigoProduto());
							System.out.println("\tCodigo: " + produto.getCodigo() + 
							           " Nome: " + produto.getNome() +
							           " Preço: " + produto.getPreco()+
					                   " Estoque: " + produto.getEstoque());
						}
						
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
