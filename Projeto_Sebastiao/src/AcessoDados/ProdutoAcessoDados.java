package AcessoDados;
import java.sql.*;
import java.util.*;
import Entidade.ProdutoEntidade;
import Configuracao.Conexao;

public class ProdutoAcessoDados{
	
	public int salvar(ProdutoEntidade produto) {
		Connection conexao = new Conexao().geraConexao();
		PreparedStatement insereSt = null;
		
		ResultSet resultado = null;
		int codigoGerado = 0;
		String sql2 = "SELECT LAST_INSERT_ID() as codigo";
			
		String sql = "insert into produto(nome, preco, estoque) values(?, ?, ?)";

		try {
			insereSt = conexao.prepareStatement(sql);
			insereSt.setString(1, produto.getNome());
			insereSt.setFloat(2, produto.getPreco());
			insereSt.setInt(3, produto.getEstoque());			
			insereSt.executeUpdate();
			
			insereSt = conexao.prepareStatement(sql2);
			resultado = insereSt.executeQuery();
			if (resultado.next()) {
				codigoGerado = resultado.getInt("codigo");
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao incluir produto. Mensagem: " + e.getMessage());
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

	public void atualizar(ProdutoEntidade produto) {
		Connection conexao = new Conexao().geraConexao();
		PreparedStatement atualizaSt = null;

		String sql = "update produto set nome=?, preco=?, estoque=? where codigo=?";

		try {
			atualizaSt = conexao.prepareStatement(sql);
			atualizaSt.setString(1, produto.getNome());
			atualizaSt.setFloat(2, produto.getPreco());
			atualizaSt.setInt(3, produto.getEstoque());
			atualizaSt.setInt(4, produto.getCodigo());
			atualizaSt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar produto. Mensagem: " + e.getMessage());
		} finally {
			try {
				atualizaSt.close();
				conexao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operações de atualização. Mensagem: " + e.getMessage());
			}
		}
	}

	public void excluir(ProdutoEntidade produto) {
		Connection conexao = new Conexao().geraConexao();
		PreparedStatement excluiSt = null;

		String sql = "delete from produto where codigo = ?";

		try {
			excluiSt = conexao.prepareStatement(sql);
			excluiSt.setInt(1, produto.getCodigo());
			excluiSt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao excluir produto. Mensagem: " + e.getMessage());
		} finally {
			try {
				excluiSt.close();
				conexao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operações de exclusão. Mensagem: " + e.getMessage());
			}
		}
	}

	public List<ProdutoEntidade> listar() {
		Connection conexao = new Conexao().geraConexao();
		List<ProdutoEntidade> produtos = new ArrayList<ProdutoEntidade>();
		Statement consulta = null;
		ResultSet resultado = null;
		ProdutoEntidade produto = null;

		String sql = "select * from produto";

		try {
			consulta = conexao.createStatement();
			resultado = consulta.executeQuery(sql);

			while (resultado.next()) {
				produto = new ProdutoEntidade();
				produto.setCodigo(resultado.getInt("codigo"));
				produto.setNome(resultado.getString("nome"));
				produto.setPreco(resultado.getFloat("preco"));
				produto.setEstoque(resultado.getInt("estoque"));
				produtos.add(produto);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar código do produto. Mensagem: " + e.getMessage());
		} finally {
			try {
				consulta.close();
				resultado.close();
				conexao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operações de consulta. Mensagem: " + e.getMessage());
			}
		}
		return produtos;
	}

	public ProdutoEntidade buscaRegistro(int valor) {
		Connection conexao = new Conexao().geraConexao();
		PreparedStatement consulta = null;
		ResultSet resultado = null;
		ProdutoEntidade produto = null;

		String sql = "select * from produto where codigo = ?";

		try {
			consulta = conexao.prepareStatement(sql);
			consulta.setInt(1, valor);
			resultado = consulta.executeQuery();

			if (resultado.next()) {
				produto = new ProdutoEntidade();
				produto.setCodigo(resultado.getInt("codigo"));
				produto.setNome(resultado.getString("nome"));
				produto.setPreco(resultado.getFloat("preco"));
				produto.setEstoque(resultado.getInt("estoque"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar código do produto. Mensagem: " + e.getMessage());
		} finally {
			try {
				consulta.close();
				resultado.close();
				conexao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operações de consulta. Mensagem: " + e.getMessage());
			}
		}
		return produto;
	}
}



