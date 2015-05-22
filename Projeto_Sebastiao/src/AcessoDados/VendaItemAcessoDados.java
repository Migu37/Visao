package AcessoDados;
import java.sql.*;
import java.util.*;
import Entidade.VendaItemEntidade;
import Entidade.VendaEntidade;
import Configuracao.Conexao;

public class VendaItemAcessoDados{
	
	public int salvar(VendaItemEntidade vendaItem) {
		Connection conexao = new Conexao().geraConexao();
		PreparedStatement insereSt = null;
			
		String sql = "insert into vendaitem(codigovenda, codigoproduto) values(?, ?)";
		
		ResultSet resultado = null;
		int codigoGerado = 0;
		String sql2 = "SELECT LAST_INSERT_ID() as codigo";

		try {
			insereSt = conexao.prepareStatement(sql);
			insereSt.setInt(1, vendaItem.getCodigoVenda());
			insereSt.setInt(2, vendaItem.getCodigoProduto());
			insereSt.executeUpdate();
			
			
			insereSt = conexao.prepareStatement(sql2);
			resultado = insereSt.executeQuery();
			if (resultado.next()) {
				codigoGerado = resultado.getInt("codigo");
			}

			
		} catch (SQLException e) {
			System.out.println("Erro ao incluir item de venda. Mensagem: " + e.getMessage());
		} finally {
			try {
				insereSt.close();
				conexao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operações de inserção. Mensagem: " + e.getMessage());
			}
		}
		
		return codigoGerado;
	}

	public void atualizar(VendaItemEntidade vendaItem) {
		Connection conexao = new Conexao().geraConexao();
		PreparedStatement atualizaSt = null;

		String sql = "update vendaitem set codigovenda=?, codigoproduto=? where codigo=?";

		try {
			atualizaSt = conexao.prepareStatement(sql);
			atualizaSt.setInt(1, vendaItem.getCodigoVenda());
			atualizaSt.setInt(2, vendaItem.getCodigoProduto());
			atualizaSt.setInt(3, vendaItem.getCodigo());
			atualizaSt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar item de venda. Mensagem: " + e.getMessage());
		} finally {
			try {
				atualizaSt.close();
				conexao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operações de atualização. Mensagem: " + e.getMessage());
			}
		}
	}

	public void excluir(VendaItemEntidade vendaItem) {
		Connection conexao = new Conexao().geraConexao();
		PreparedStatement excluiSt = null;

		String sql = "delete from vendaitem where codigo = ?";

		try {
			excluiSt = conexao.prepareStatement(sql);
			excluiSt.setInt(1, vendaItem.getCodigo());
			excluiSt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao excluir item de venda. Mensagem: " + e.getMessage());
		} finally {
			try {
				excluiSt.close();
				conexao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operações de exclusão. Mensagem: " + e.getMessage());
			}
		}
	}

	public List<VendaItemEntidade> listar() {
		Connection conexao = new Conexao().geraConexao();
		List<VendaItemEntidade> vendaItens = new ArrayList<VendaItemEntidade>();
		Statement consulta = null;
		ResultSet resultado = null;
		VendaItemEntidade vendaItem = null;

		String sql = "select * from vendaitem";

		try {
			consulta = conexao.createStatement();
			resultado = consulta.executeQuery(sql);

			while (resultado.next()) {
				vendaItem = new VendaItemEntidade();
				vendaItem.setCodigo(resultado.getInt("codigo"));
				vendaItem.setCodigoVenda(resultado.getInt("codigovenda"));
				vendaItem.setCodigoProduto(resultado.getInt("codigoproduto"));
				vendaItens.add(vendaItem);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar código do item da venda. Mensagem: " + e.getMessage());
		} finally {
			try {
				consulta.close();
				resultado.close();
				conexao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operações de consulta. Mensagem: " + e.getMessage());
			}
		}
		return vendaItens;
	}

	public VendaItemEntidade buscaRegistro(int valor) {
		Connection conexao = new Conexao().geraConexao();
		PreparedStatement consulta = null;
		ResultSet resultado = null;
		VendaItemEntidade vendaItem = null;

		String sql = "select * from vendaitem where codigo = ?";

		try {
			consulta = conexao.prepareStatement(sql);
			consulta.setInt(1, valor);
			resultado = consulta.executeQuery();

			if (resultado.next()) {
				vendaItem = new VendaItemEntidade();
				vendaItem.setCodigo(resultado.getInt("codigo"));
				vendaItem.setCodigoVenda(resultado.getInt("codigovenda"));
				vendaItem.setCodigoProduto(resultado.getInt("codigoproduto"));		
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar código do item da venda. Mensagem: " + e.getMessage());
		} finally {
			try {
				consulta.close();
				resultado.close();
				conexao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operações de consulta. Mensagem: " + e.getMessage());
			}
		}
		return vendaItem;
	}
	
	public List<VendaItemEntidade> listarVendaItens(VendaEntidade venda) {
		Connection conexao = new Conexao().geraConexao();
		List<VendaItemEntidade> vendaItens = new ArrayList<VendaItemEntidade>();
		PreparedStatement consulta = null;
		ResultSet resultado = null;
		VendaItemEntidade vendaItem = null;

		String sql = "select * from vendaitem where codigovenda = ?";

		try {
			consulta = conexao.prepareStatement(sql);
			consulta.setInt(1, venda.getCodigo());
			resultado = consulta.executeQuery();			
			

			while (resultado.next()) {
				vendaItem = new VendaItemEntidade();
				vendaItem.setCodigo(resultado.getInt("codigo"));
				vendaItem.setCodigoVenda(resultado.getInt("codigovenda"));
				vendaItem.setCodigoProduto(resultado.getInt("codigoproduto"));
				vendaItens.add(vendaItem);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar código do item da venda. Mensagem: " + e.getMessage());
		} finally {
			try {
				consulta.close();
				resultado.close();
				conexao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operações de consulta. Mensagem: " + e.getMessage());
			}
		}
		return vendaItens;
	}
	

	public void excluirTodosItens(VendaEntidade venda) {
		Connection conexao = new Conexao().geraConexao();
		PreparedStatement excluiSt = null;

		String sql = "delete from vendaitem where codigovenda = ?";

		try {
			excluiSt = conexao.prepareStatement(sql);
			excluiSt.setInt(1, venda.getCodigo());
			excluiSt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao excluir itens da venda. Mensagem: " + e.getMessage());
		} finally {
			try {
				excluiSt.close();
				conexao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operações de exclusão. Mensagem: " + e.getMessage());
			}
		}
	}


}



