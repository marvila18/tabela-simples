package loja.telas;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import loja.classe.Cliente;

/**
 * Classe responsável por criar a janela de cadastro, consulta, atualização e
 * exclusão de clientes.
 * 
 * @author Charles
 */

public class JanelaClientes {

	/**
	 * Cria a janela cliente.
	 *
	 * @return a janela de cliente criada
	 */
	public static JFrame criarJanelaCliente() {

		// Define a janelaClientes
		JFrame janelaCliente = new JFrame("Cliente"); // Janela Normal
		janelaCliente.setResizable(false); // A janela não poderá ter o tamanho ajustado
		janelaCliente.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaCliente.setSize(400, 300); // Define tamanho da janela

		// Define o layout da janela
		Container caixa = janelaCliente.getContentPane();
		caixa.setLayout(null);

		// Define os labels dos campos
		JLabel labelid = new JLabel("Código Cliente: ");
		JLabel labelnome = new JLabel("Nome: ");
		JLabel labelemail = new JLabel("E-mail : ");
		JLabel labeltelefone = new JLabel("Telefone : ");
		JLabel labelendereco = new JLabel("Endereço : ");

		// Posiciona os labels na janela
		labelid.setBounds(40, 40, 100, 20); // coluna, linha, largura, tamanho
		labelnome.setBounds(40, 70, 100, 20); // coluna, linha, largura, tamanho
		labelemail.setBounds(40, 100, 100, 20); // coluna, linha, largura, tamanho
		labeltelefone.setBounds(40, 130, 100, 20); // coluna, linha, largura, tamanho
		labelendereco.setBounds(40, 160, 100, 20); // coluna, linha, largura, tamanho

		// Define os input box
		JTextField jTextid = new JTextField();
		JTextField jTextnome = new JTextField();
		JTextField jTextemail = new JTextField();
		JTextField jTextlabeltelefone = new JTextField();
		JTextField jTextlabelendereco = new JTextField();

		// Define se os campos estão habilitados ou não no início
		jTextid.setEnabled(true);
		jTextnome.setEnabled(true);
		jTextemail.setEnabled(true);
		jTextlabeltelefone.setEnabled(true);
		jTextlabelendereco.setEnabled(true);

		// Posiciona os input box
		jTextid.setBounds(150, 40, 230, 20); // coluna, linha, largura, tamanho
		jTextnome.setBounds(150, 70, 230, 20); // coluna, linha, largura, tamanho
		jTextemail.setBounds(150, 100, 230, 20); // coluna, linha, largura, tamanho
		jTextlabeltelefone.setBounds(150, 130, 230, 20); // coluna, linha, largura, tamanho
		jTextlabelendereco.setBounds(150, 160, 230, 20); // coluna, linha, largura, tamanho

		// Adiciona os rótulos e os input box na janela
		janelaCliente.add(labelid);
		janelaCliente.add(labelnome);
		janelaCliente.add(labelemail);
		janelaCliente.add(labeltelefone);
		janelaCliente.add(labelendereco);
		janelaCliente.add(jTextid);
		janelaCliente.add(jTextnome);
		janelaCliente.add(jTextemail);
		janelaCliente.add(jTextlabeltelefone);
		janelaCliente.add(jTextlabelendereco);

		// Define botões e a localização deles na janela
		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(40, 200, 100, 20); // coluna, linha, largura, tamanho
		janelaCliente.add(botaoConsultar);

		JButton botaoAtualizar = new JButton("Atualizar");
		botaoAtualizar.setBounds(40, 230, 100, 20); // coluna, linha, largura, tamanho
		janelaCliente.add(botaoAtualizar);
		botaoAtualizar.setEnabled(true);

		JButton botaoCadastrar = new JButton("Cadastrar");
		botaoCadastrar.setBounds(160, 200, 100, 20); // coluna, linha, largura, tamanho
		janelaCliente.add(botaoCadastrar);
		botaoCadastrar.setEnabled(true);

		JButton botaoExcluir = new JButton("Excluir");
		botaoExcluir.setBounds(160, 230, 100, 20); // coluna, linha, largura, tamanho
		janelaCliente.add(botaoExcluir);
		botaoExcluir.setEnabled(true);

		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(280, 200, 100, 20); // coluna, linha, largura, tamanho
		janelaCliente.add(botaoLimpar);

		Cliente cliente = new Cliente();

		/**
		 * Ação executada ao clicar no botão "Consultar".
		 */
		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!cliente.consultarCliente(Integer.parseInt(jTextid.getText()))) {
						JOptionPane.showMessageDialog(janelaCliente, "Cliente não encontrado, tente novamente.");
						botaoCadastrar.setEnabled(true);
					} else {
						jTextnome.setText(cliente.getNome());
						jTextemail.setText(cliente.getEmail());
						jTextlabeltelefone.setText(cliente.getTelefone());
						jTextlabelendereco.setText(cliente.getEndereco());

						jTextnome.setEnabled(true);
						jTextemail.setEnabled(true);
						jTextlabeltelefone.setEnabled(true);
						jTextlabelendereco.setEnabled(true);

						botaoCadastrar.setEnabled(true);
						botaoAtualizar.setEnabled(true);
						JOptionPane.showMessageDialog(janelaCliente, "Cliente encontrado!");
						botaoAtualizar.setEnabled(true);
					}
				} catch (Exception e2) {
					// TODO: Trate qualquer exceção que possa ocorrer ao consultar cliente.
				}
			}
		});

		/**
		 * Ação executada ao clicar no botão "Atualizar".
		 */

		botaoAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (JOptionPane.showConfirmDialog(janelaCliente, "Tem Certeza?") == 0) {
						if (!cliente.atualizarCliente(Integer.parseInt(jTextid.getText()), jTextnome.getText(),
								jTextemail.getText(), jTextlabeltelefone.getText(), jTextlabelendereco.getText())) {
							JOptionPane.showMessageDialog(janelaCliente, "Não foi possível atualizar o cliente");
						} else {
							JOptionPane.showMessageDialog(janelaCliente, "Atualização realizada");
						}
					}
				} catch (Exception e2) {
					// TODO: Trate qualquer exceção que possa ocorrer ao atualizar cliente.
				}
			}
		});

		/**
		 * Ação executada ao clicar no botão "Cadastrar".
		 */

		botaoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (JOptionPane.showConfirmDialog(janelaCliente, "Tem Certeza?") == 0) {
						if (!cliente.consultarCliente(Integer.parseInt(jTextid.getText()))) {
							cliente.cadastrarCliente(Integer.parseInt(jTextid.getText()), jTextnome.getText(),
									jTextemail.getText(), jTextlabeltelefone.getText(), jTextlabelendereco.getText());
							JOptionPane.showMessageDialog(janelaCliente, "Cadastro realizado com sucesso!");
						} else {
							JOptionPane.showMessageDialog(janelaCliente, "Cliente já cadastrada");
							botaoCadastrar.setEnabled(true);
						}
					}
				} catch (Exception e2) {
					// TODO: Trate qualquer exceção que possa ocorrer ao cadastrar cliente
				}
			}
		});

		/**
		 * Ação executada ao clicar no botão "Excluir".
		 */
		botaoExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (JOptionPane.showConfirmDialog(janelaCliente, "Tem Certeza?") == 0) {
						int id = Integer.parseInt(jTextid.getText());
						cliente.removerCliente(id);
						JOptionPane.showMessageDialog(janelaCliente, "Cadastro excluído com sucesso!");
					}
				} catch (Exception e2) {
					// TODO: Trate qualquer exceção que possa ocorrer ao excluir cliente
				}
			}
		});

		/**
		 * Ação executada ao clicar no botão "Limpar".
		 */
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextid.setText(""); // Limpar campo
				jTextnome.setText(""); // Limpar campo
				jTextemail.setText(""); // Limpar campo
				jTextlabeltelefone.setText(""); // Limpar campo
				jTextlabelendereco.setText(""); // Limpar campo

				jTextid.requestFocus(); // Colocar o foco em um campo
			}
		});
		
		

		return janelaCliente;
	}
}
