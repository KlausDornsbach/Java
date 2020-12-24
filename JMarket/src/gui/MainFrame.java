package gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import engine.Acao;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextPane;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//public GraphManager gm;                    //gm was transfered to the stock class

	private JPanel contentPane;
	private JTextField stockName;
	private List<Acao> acoes;
	private final int WINDOW_HEIGHT = 800;
	private final int WINDOW_WIDTH = 1280;
	boolean podeRodar = false;
	JButton btnGo;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, WINDOW_WIDTH, WINDOW_HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Color c = new Color(31, 31, 31);
		this.setBackground(c);
		contentPane.setBackground(c);
		acoes = new ArrayList<>();							//declare new list
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(31, 31, 31));
		panel.setBounds(363, 126, 534, 329);
	//	panel.setBorder(border);
		contentPane.add(panel);
		panel.setLayout(null);
		
		stockName = new JTextField();
		stockName.setBounds(23, 99, 116, 22);
		panel.add(stockName);
		stockName.setColumns(10);
		
		JLabel lbl1 = new JLabel("stock abreviation");
		lbl1.setForeground(new Color(0, 255, 102));
		lbl1.setBounds(23, 70, 111, 16);
		panel.add(lbl1);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(23, 172, 167, 81);
		panel.add(textPane);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setBackground(new Color(0, 255, 102));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Acao a = new Acao(stockName.getText());
				textPane.setText(textPane.getText()+"\n"+stockName.getText());
				acoes.add(a);
			}
		});
		btnAdd.setBounds(58, 134, 97, 25);
		panel.add(btnAdd);
		
		btnGo = new JButton("GO");
		btnGo.setBackground(new Color(0, 255, 102));
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				podeRodar = true;
				
				contentPane.removeAll();			//remove previous gui
				contentPane.revalidate();
				contentPane.repaint();
				/*int cont = 0;
				for(Acao a : acoes) {
					if(acoes.size()==2||acoes.size()==3) {
						GraphManager gm = new GraphManager(a.getDataBase(), 4);	//creating and setting graphic managers for stock list
						a.setGraphManager(gm);
					}else {
						GraphManager gm = new GraphManager(a.getDataBase(), acoes.size());	//creating and setting graphic managers for stock list
						a.setGraphManager(gm);
					}
					int varY = 0;
					if(cont>=2) varY = 1;
					a.getGraphManager().setLocation((WINDOW_WIDTH/2)*(cont%2), (WINDOW_HEIGHT/2)*varY);
					contentPane.add(a.getGraphManager());
					a.getGraphManager().setVisible(true);
				}*/
				//gm.setSize(1300, 1000);
		        //this.pack();
			}
		});
		btnGo.setBounds(314, 134, 97, 25);
		panel.add(btnGo);
		
		JLabel lbl0 = new JLabel("Stock Manager");
		lbl0.setBounds(511, 61, 237, 52);
		contentPane.add(lbl0);
		lbl0.setForeground(new Color(0, 255, 102));
		lbl0.setBackground(new Color(0, 204, 153));
		
		Font currentFont = lbl0.getFont();
		Font newFont = currentFont.deriveFont(currentFont.getSize() * 2.2F);
		lbl0.setFont(newFont);
		
		/*
		gm.setLocation(0, 0);
		//gm.setSize(1300, 1000);
		contentPane.add(gm);
        //this.pack();
		gm.setVisible(true);*/
	}
	public List<Acao> getAcoes(){
		return acoes;
	}
	public boolean getPodeRodar() {
		return podeRodar;
	}
	public JPanel getContentPane() {
		return contentPane;
	}
	public int getWindowWidth() {
		return WINDOW_WIDTH;
	}
	public int getWindowHeight() {
		return WINDOW_HEIGHT;
	}
	
}
