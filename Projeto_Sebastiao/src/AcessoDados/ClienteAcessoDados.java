package AcessoDados;
import java.sql.*;
import java.util.*;
import Entidade.ClienteEntidade;
import Configuracao.Conexao;

public class ClienteAcessoDados{
	
	public int salvar(ClienteEntidade cliente) {
		Connection conexao = new Conexao().geraConexao();
		PreparedStatement insereSt = null;

		ResultSet resultado = null;
		int codigoGerado = 0;
		String sql2 = "SELECT LAST_INSERT_ID() as codigo";

		String sql = "insert into cliente(nome, email) values(?, ?)";

		try {
			insereSt = conexao.prepareStatement(sql);
			insereSt.setString(1, cliente.getNome());
			insereSt.setString(2, cliente.getEmail());			
			insereSt.executeUpdate();
			
			
			insereSt = conexao.prepareStatement(sql2);
			resultado = insereSt.executeQuery();
			if (resultado.next()) {
				codigoGerado = resultado.getInt("codigo");
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao incluir cliente. Mensagem: " + e.getMessage());
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

	public void atualizar(ClienteEntidade cliente) {
		Connection conexao = new Conexao().geraConexao();
		PreparedStatement atualizaSt = null;

		String sql = "update cliente set nome=?, email=? where codigo=?";

		try {
			atualizaSt = conexao.prepareStatement(sql);
			atualizaSt.setString(1, cliente.getNome());
			atualizaSt.setString(2, cliente.getEmail());
			atualizaSt.setInt(3, cliente.getCodigo());
			atualizaSt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar cliente. Mensagem: " + e.getMessage());
		} finally {
			try {
				atualizaSt.close();
				conexao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operações de atualização. Mensagem: " + e.getMessage());
			}
		}
	}

	public void excluir(ClienteEntidade cliente) {
		Connection conexao = new Conexao().geraConexao();
		PreparedStatement excluiSt = null;

		String sql = "delete from cliente where codigo = ?";

		try {
			excluiSt = conexao.prepareStatement(sql);
			excluiSt.setInt(1, cliente.getCodigo());
			excluiSt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao excluir cliente. Mensagem: " + e.getMessage());
		} finally {
			try {
				excluiSt.close();
				conexao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operações de exclusão. Mensagem: " + e.getMessage());
			}
		}
	}

	public List<ClienteEntidade> listar() {
		Connection conexao = new Conexao().geraConexao();
		List<ClienteEntidade> clientes = new ArrayList<ClienteEntidade>();
		Statement consulta = null;
		ResultSet resultado = null;
		ClienteEntidade cliente = null;

		String sql = "select * from cliente";

		try {
			consulta = conexao.createStatement();
			resultado = consulta.executeQuery(sql);

			while (resultado.next()) {
				cliente = new ClienteEntidade();
				cliente.setCodigo(resultado.getInt("codigo"));
				cliente.setNome(resultado.getString("nome"));
				cliente.setEmail(resultado.getString("email"));
				clientes.add(cliente);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar código do cliente. Mensagem: " + e.getMessage());
		} finally {
			try {
				consulta.close();
				resultado.close();
				conexao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operações de consulta. Mensagem: " + e.getMessage());
			}
		}
		return clientes;
	}

	public ClienteEntidade buscaRegistro(int valor) {
		Connection conexao = new Conexao().geraConexao();
		PreparedStatement consulta = null;
		ResultSet resultado = null;
		ClienteEntidade cliente = null;

		String sql = "select * from cliente where codigo = ?";

		try {
			consulta = conexao.prepareStatement(sql);
			consulta.setInt(1, valor);
			resultado = consulta.executeQuery();

			if (resultado.next()) {
				cliente = new ClienteEntidade();
				cliente.setCodigo(resultado.getInt("codigo"));
				cliente.setNome(resultado.getString("nome"));
				cliente.setEmail(resultado.getString("email"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar código do cliente. Mensagem: " + e.getMessage());
		} finally {
			try {
				consulta.close();
				resultado.close();
				conexao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operações de consulta. Mensagem: " + e.getMessage());
			}
		}
		return cliente;
	}
}



