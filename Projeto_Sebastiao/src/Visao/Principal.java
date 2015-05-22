package Visao;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		int opcao;
		Scanner teclado = new Scanner(System.in);
		
		do 
		{ 
			System.out.println("Controle de Estoque");
			System.out.println("Opções");		
			System.out.println("1 - Gerenciar Produto");
			System.out.println("2 - Gerenciar Venda");
			System.out.println("3 - Gerenciar Cliente");	
			System.out.println("0 - Sair");
			System.out.println("\nDigite sua opção: ");
			opcao = teclado.nextInt();
			
			switch (opcao)
			{  
				case 1:  
						ProdutoVisao produtovisao = new ProdutoVisao();
						produtovisao.Gerenciar();
					break;
				case 2:  
					VendaVisao vendavisao = new VendaVisao();
					vendavisao.Gerenciar();						
				break;
				case 3:  
					ClienteVisao clientevisao = new ClienteVisao();
					clientevisao.Gerenciar();						
				break;				
				default:
					System.out.println("Opção inválida.");
				break;					
			}

		} 
		while (opcao != 0);
		
	}
}



