package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class TelaInicial extends JFrame {

	JComboBox comboBox;
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaInicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewGame = new JButton("new game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
				Table t = new Table(comboBox.getSelectedIndex()+1);//pega o numero de players da combobox e passa pra proxima classe
				t.setVisible(true);
			}
		});
		
		
		//fim da aplicacao de botoes
		String[] i = {"um", "dois", "tres", "quatro"};//vetor pro comboBox
		btnNewGame.setBounds(163, 186, 97, 25);
		contentPane.add(btnNewGame);
		
		JLabel lblNumberOfPlayers = new JLabel("number of players:");
		lblNumberOfPlayers.setBounds(64, 96, 196, 16);
		contentPane.add(lblNumberOfPlayers);
		
		comboBox = new JComboBox(i);
		comboBox.setEnabled(true);
		comboBox.setBounds(63, 121, 107, 22);
		contentPane.add(comboBox);
		
		JLabel lblJogoDaRoleta = new JLabel("Jogo da Roleta");
		lblJogoDaRoleta.setBounds(172, 13, 88, 16);
		contentPane.add(lblJogoDaRoleta);
	}
}
