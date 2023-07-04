package loja.classe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import loja.conexao.Conexao;

/**
 * Classe que representa um Cliente.
 */
public class Cliente {
	private int id;
	private String nome;
	private String email;
	private String telefone;
	private String endereco;

	/**
	 * Obtém o id do Cliente.
	 *
	 * @return O id do Cliente.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Define o id do Cliente.
	 *
	 * @param id O id do Cliente.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Obtém o nome do Cliente.
	 *
	 * @return O nome do Cliente.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Define o nome do Cliente.
	 *
	 * @param nome O nome do Cliente.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Obtém o e-mail do Cliente.
	 *
	 * @return O e-mail do Cliente.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Define o e-mail do Cliente.
	 *
	 * @param email O e-mail do Cliente.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Obtém o telefone do Cliente.
	 *
	 * @return O telefone do Cliente.
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * Define o telefone do Cliente.
	 *
	 * @param telefone O telefone do Cliente.
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * Obtém o endereço do Cliente.
	 *
	 * @return O endereço do Cliente.
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * Define o endereço do Cliente.
	 *
	 * @param endereco O endereço do Cliente.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * Cadastra um cliente.
	 *
	 * @param id       O id do cliente.
	 * @param nome     O nome do cliente.
	 * @param email    O email do cliente.
	 * @param telefone O telefone do cliente.
	 * @param endereco O endereço do cliente.
	 * @return true se o cadastro foi realizado com sucesso, false caso contrário.
	 * @throws SQLException se ocorrer um erro durante a execução da consulta SQL.
	 */
	public boolean cadastrarCliente(int id, String nome, String email, String telefone, String endereco)
			throws SQLException {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			String sql = "INSERT INTO clientes (id, nome, email, telefone, endereco) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, nome);
			ps.setString(3, email);
			ps.setString(4, telefone);
			ps.setString(5, endereco);
			int totalRegistrosAfetados = ps.executeUpdate();
			if (totalRegistrosAfetados == 0) {
				System.out.println("Não foi feito o cadastro!");
				return false;
			}
			System.out.println("Cadastro realizado!");
			return true;
		} catch (SQLException | ClassNotFoundException erro) {
			System.out.println("Erro ao cadastrar cliente: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	/**
	 * Consulta um cliente pelo ID.
	 *
	 * @param id O ID do cliente a ser consultado.
	 * @return true se a consulta foi realizada com sucesso, false caso contrário.
	 * @throws SQLException se ocorrer um erro durante a execução da consulta SQL.
	 * @throws ClassNotFoundException 
	 */
	public boolean consultarCliente(int id) throws SQLException, ClassNotFoundException {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			String sql = "SELECT * FROM clientes WHERE id=?";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("Cliente não cadastrado!");
				return false;
			} else {
				while (rs.next()) {
					this.id = rs.getInt("id");
					this.nome = rs.getString("nome");
					this.email = rs.getString("email");
					this.telefone = rs.getString("telefone");
					this.endereco = rs.getString("endereco");
				}
				return true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar cliente: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	/**
	 * Atualiza os dados de um cliente.
	 *
	 * @param id O ID do cliente.
	 * @param nome O nome do cliente.
	 * @param email O email do cliente.
	 * @param telefone O telefone do cliente.
	 * @param endereco O endereço do cliente.
	 * @return true se a atualização foi realizada com sucesso, false caso
	 *         contrário.
	 * @throws SQLException se ocorrer um erro durante a execução da consulta SQL.
	 * @throws ClassNotFoundException 
	 */
	public boolean atualizarCliente(int id, String nome, String email, String telefone, String endereco)
			throws SQLException, ClassNotFoundException {
		if (!consultarCliente(id))
			return false;
		else {
			Connection conexao = null;
			try {
				conexao = Conexao.conectaBanco();
				String sql = "UPDATE clientes SET nome=?, email=?, telefone=?, endereco=? WHERE id=?";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setString(1, nome);
				ps.setString(2, email);
				ps.setString(3, telefone);
				ps.setString(4, endereco);
				ps.setInt(5, id);
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("Não foi feita a atualização!");
				else
					System.out.println("Atualização realizada!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao atualizar cliente: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}

	/**
	 * Remove um cliente pelo id.
	 *
	 * @param id cliente O id do cliente a ser removido.
	 * @return true se a remoção foi realizada com sucesso, false caso contrário.
	 * @throws SQLException se ocorrer um erro durante a execução da consulta SQL.
	 * @throws ClassNotFoundException 
	 */
	public boolean removerCliente(int idCliente) throws SQLException, ClassNotFoundException {
		if (!consultarCliente(idCliente))
			return false;
		else {
			Connection conexao = null;
			try {
				conexao = Conexao.conectaBanco();
				String sql = "DELETE FROM clientes WHERE id=?";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setInt(1, idCliente);
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("Não foi feita a remoção!");
				else
					System.out.println("Remoção realizada!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao remover cliente: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}
}