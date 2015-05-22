package Visao;

import java.util.List;
import java.util.Scanner;

import Entidade.ClienteEntidade;
import Controle.ClienteControle;

public class ClienteVisao{
	
	public void Gerenciar(){
		int opcao, codigo;
		Scanner teclado = new Scanner(System.in);
		List<ClienteEntidade> clientes = null;
		ClienteEntidade cliente = null;
		ClienteControle controle = new ClienteControle();
				
		do 
		{ 
			System.out.println("Gerenciar Cliente");
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
					cliente = new ClienteEntidade();
					System.out.println("Nome: ");
					cliente.setNome(teclado.next());
					System.out.println("Email: ");
					cliente.setEmail(teclado.next());				
					controle.salvar(cliente);		
					break;
				case 2:
					clientes = controle.listar();
					for (ClienteEntidade c: clientes) {
						System.out.println("Codigo: " + c.getCodigo() + 
								           " Nome: " + c.getNome() + 
								           " Email: " + c.getEmail());
					}
					System.out.println("Codigo para alteracao: ");
					codigo = (teclado.nextInt());
					cliente = controle.buscaRegistro(codigo);
					System.out.println("Nome: ");
					cliente.setNome(teclado.next());
					System.out.println("Email: ");
					cliente.setEmail(teclado.next());				
					controle.atualizar(cliente);
					break;
					
				case 3:
					clientes = controle.listar();
					for (ClienteEntidade c: clientes) {
						System.out.println("Codigo: " + c.getCodigo() + 
								           " Nome: " + c.getNome() + 
								           " Email: " + c.getEmail());
					}
					System.out.println("Codigo para exclusao: ");
					codigo = (teclado.nextInt());
					cliente = controle.buscaRegistro(codigo);
					controle.excluir(cliente);					
					break;					
					
				case 4:
					clientes = controle.listar();
					for (ClienteEntidade c: clientes) {
						System.out.println("Codigo: " + c.getCodigo() + 
								           " Nome: " + c.getNome() + 
								           " Email: " + c.getEmail());
					}					break;
					
				default:
					System.out.println("Opção inválida.");
				break;					
			}

		} 
		while (opcao != 0);
		

	}

}
