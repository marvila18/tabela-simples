package loja.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A classe Conexao fornece métodos para estabelecer e fechar conexões com o
 * banco de dados.
 * 
 * @author Charles
 */
public class Conexao {
	/**
	 * Estabelece uma conexão com o banco de dados.
	 *
	 * @return a conexão estabelecida
	 * @throws ClassNotFoundException se o driver JDBC não for encontrado
	 * @throws SQLException           se ocorrer um erro de conexão com o banco de dados
	 */
	public static Connection conectaBanco() throws ClassNotFoundException, SQLException {
		Connection conexao = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/loja"; // URL do banco de dados
			String user = "root"; // nome do usuário do banco
			String password = ""; // senha do banco
			conexao = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException erro) {
			System.out.println("Driver nao encontrado: " + erro);
			throw erro;
		} catch (SQLException erro) {
			System.out.println("Erro de conexao ao banco de dados: " + erro.toString());
			throw erro;
		} catch (Exception erro) {
			System.out.println("Erro nao identificado: " + erro.toString());
		}
		return conexao;
	}

	/**
	 * Fecha uma conexão com o banco de dados.
	 *
	 * @param conexao a conexão a ser fechada
	 */
	public static void fechaConexao(Connection conexao) {
		try {
			conexao.close();
		} catch (Exception erro) {
			System.out.println("Erro ao fechar a conexão: " + erro.toString());
		}
	}
}
