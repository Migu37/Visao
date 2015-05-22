package AcessoDados;
import java.sql.*;
import java.util.*;
import Entidade.VendaEntidade;
import Configuracao.Conexao;

public class VendaAcessoDados{
	
	public int salvar(VendaEntidade venda) {
		Connection conexao = new Conexao().geraConexao();
		PreparedStatement insereSt = null;
		
		ResultSet resultado = null;
		int codigoGerado = 0;
		String sql2 = "SELECT LAST_INSERT_ID() as codigo";
		
		String sql = "insert into venda(codigocliente) values(?)";

		try {
			insereSt = conexao.prepareStatement(sql);
			insereSt.setInt(1, venda.getCodigoCliente());			
			insereSt.executeUpdate();
			
			insereSt = conexao.prepareStatement(sql2);
			resultado = insereSt.executeQuery();
			if (resultado.next()) {
				codigoGerado = resultado.getInt("codigo");
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao incluir venda. Mensagem: " + e.getMessage());
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

	public void atualizar(VendaEntidade venda) {
		Connection conexao = new Conexao().geraConexao();
		PreparedStatement atualizaSt = null;

		String sql = "update venda set codigocliente=? where codigo=?";

		try {
			atualizaSt = conexao.prepareStatement(sql);
			atualizaSt.setInt(1, venda.getCodigoCliente());
			atualizaSt.setInt(2, venda.getCodigo());
			atualizaSt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar venda. Mensagem: " + e.getMessage());
		} finally {
			try {
				atualizaSt.close();
				conexao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operações de atualização. Mensagem: " + e.getMessage());
			}
		}
		
		
	}

	public void excluir(VendaEntidade venda) {
		Connection conexao = new Conexao().geraConexao();
		PreparedStatement excluiSt = null;

		String sql = "delete from venda where codigo = ?";

		try {
			excluiSt = conexao.prepareStatement(sql);
			excluiSt.setInt(1, venda.getCodigo());
			excluiSt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao excluir venda. Mensagem: " + e.getMessage());
		} finally {
			try {
				excluiSt.close();
				conexao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operações de exclusão. Mensagem: " + e.getMessage());
			}
		}
	}

	public List<VendaEntidade> listar() {
		Connection conexao = new Conexao().geraConexao();
		List<VendaEntidade> vendas = new ArrayList<VendaEntidade>();
		Statement consulta = null;
		ResultSet resultado = null;
		VendaEntidade venda = null;

		String sql = "select * from venda";

		try {
			consulta = conexao.createStatement();
			resultado = consulta.executeQuery(sql);

			while (resultado.next()) {
				venda = new VendaEntidade();
				venda.setCodigo(resultado.getInt("codigo"));
				venda.setCodigoCliente(resultado.getInt("codigocliente"));
				vendas.add(venda);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar código da venda. Mensagem: " + e.getMessage());
		} finally {
			try {
				consulta.close();
				resultado.close();
				conexao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operações de consulta. Mensagem: " + e.getMessage());
			}
		}
		return vendas;
	}

	public VendaEntidade buscaRegistro(int valor) {
		Connection conexao = new Conexao().geraConexao();
		PreparedStatement consulta = null;
		ResultSet resultado = null;
		VendaEntidade venda = null;

		String sql = "select * from venda where codigo = ?";

		try {
			consulta = conexao.prepareStatement(sql);
			consulta.setInt(1, valor);
			resultado = consulta.executeQuery();

			if (resultado.next()) {
				venda = new VendaEntidade();
				venda.setCodigo(resultado.getInt("codigo"));
				venda.setCodigoCliente(resultado.getInt("codigocliente"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar código da venda. Mensagem: " + e.getMessage());
		} finally {
			try {
				consulta.close();
				resultado.close();
				conexao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operações de consulta. Mensagem: " + e.getMessage());
			}
		}
		return venda;
	}

}



