package loja.telas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

/**
 * Classe que representa a janela principal do programa.
 * @author Charles
 */
public class janelaPrincipal {

	/**
	 * Método responsável por apresentar o menu da janela principal.
	 */
	public static void apresentarMenu() {
		// configura janela principal
		JFrame janelaPrincipal = new JFrame("Janela Principal");
		janelaPrincipal.setTitle("Janela Principal");
		janelaPrincipal.setResizable(false);
		janelaPrincipal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		janelaPrincipal.setSize(400, 300);

		// Cria uma barra de menu para a janela principal
		JMenuBar menuBar = new JMenuBar();

		// Adiciona a barra de menu ao frame
		janelaPrincipal.setJMenuBar(menuBar);

		// Define e adiciona menu na barra de menu

		JMenu menuCliente = new JMenu("Cliente");

		menuBar.add(menuCliente);

		JMenuItem mCliente = new JMenuItem("Cliente ");

		menuCliente.add(mCliente);

		JFrame janelaClientes = JanelaClientes.criarJanelaCliente();

		mCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				janelaClientes.setVisible(true);
			}
		});

		janelaPrincipal.setVisible(true);

	}

	/**
	 * Método principal que inicia a aplicação.
	 *
	 * @param args Argumentos da linha de comando.
	 */
	public static void main(String[] args) {
		apresentarMenu();
	}
}