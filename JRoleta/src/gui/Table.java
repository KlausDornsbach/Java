package gui;

import engine.*;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Table extends JFrame {
	public Board brd;
	public Player currentPlayer;
	public int currentFicha;
	int sorteado;
	int totPlayersFalidos = 0;
	public boolean doSomething = true;
	Player lastPlayer = new Player(4);
	public List<Player> falidos;
	Aposta apo;
	int[] vermelhos = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
	
	private final int x = 50;
	private final int y = 12;
	private final int WINDOW_HEIGHT = (14*x)+(5*y);
	private final int WINDOW_WIDTH =  (22*x)+(11*y);
	private JPanel contentPane;
	
	//slots graficos do tabuleiro
	JButton[][] campo_apostas = new JButton[3][12];//1 com 1 ficha
	JButton[][] campo_apostas_rectangle = new JButton[3][11];//apostar em 2 com 1 ficha
	JButton[][] campo_apostas_rectangle2= new JButton[2][12];
	JButton[][] campo_apostas_square = new JButton[2][11];//apostar em 4 com 1 ficha
	
	JButton[] linhas_apostas = new JButton[3];
	JButton[] duzia_apostas = new JButton[3];
	JButton[] aposta_especial = new JButton[5];
	JButton[] metade_apostas= new JButton[6];
	JButton[] fichas= new JButton[5];
	JLabel[][] hud_jogadores= new JLabel[2][4];
	JLabel hud_budget= new JLabel();
	JButton passa_vez= new JButton();

	//paineis
	JPanel panelA;
	JPanel panelB;
	JPanel panelC;
	JPanel panelD;
	JPanel panelE;
	JPanel panelF;
	JPanel panelG;
	JPanel panelH;
	JPanel panelI;
		
	/**
	 * Create the frame.
	 */
	public Table(int n) {
		currentFicha = 5;
		sorteado = 0;
		brd = new Board(n);										//crio um board com n players
		currentPlayer = brd.getListaPlayers().get(0);			//player atual é  o primeiro da arraylist
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
		setBounds(100, 100, WINDOW_WIDTH, WINDOW_HEIGHT);		
		contentPane = new JPanel();			
		contentPane.setBackground(new Color(34, 139, 34));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		falidos = new ArrayList<>();
		
		//panel onde vai o tabuleiro numerico
		panelA = new JPanel();
		panelA.setBounds(8*x, 2*x, 12*x+11*y, 3*x+2*y);
		contentPane.add(panelA);
		panelA.setLayout(null);
		
		panelB = new JPanel();//panel apostas em linha
		panelB.setBounds((20*x)+(11*y), 2*x, x, 3*x + 2*y);
		contentPane.add(panelB);
		panelB.setLayout(null);
		
		panelC = new JPanel();//panel de 12 em 12
		panelC.setBounds(8*x, (5*x) + (2*y), 12*x+11*y+(-y), 2*x);
		contentPane.add(panelC);
		panelC.setLayout(null);
		
		panelD = new JPanel();//painel apostas 1/2
		panelD.setBounds(8*x, (7*x) + (2*y), 12*x+11*y+(-y), 2*x);
		contentPane.add(panelD);
		panelD.setLayout(null);
		
		panelE = new JPanel();//painel apostas especiais
		panelE.setBounds(8*x, (9*x) + (2*y), 10*x + 9*y, x);
		contentPane.add(panelE);
		panelE.setLayout(null);
		
		panelF = new JPanel();//painel fichas
		panelF.setBounds(2*x, 2*x, x, 5*x);
		contentPane.add(panelF);
		panelF.setLayout(null);
		
		panelG = new JPanel();//hud jogadores
		panelG.setBounds(8*x, 10*x+5*y, 8*x + 7*y, 2*x);
		contentPane.add(panelG);
		panelG.setLayout(null);
		
		panelH = new JPanel();//budget
		panelH.setBounds(2*x, (7*x) + (2*y), 2*x, x);
		contentPane.add(panelH);
		panelH.setLayout(null);
		
		panelI = new JPanel();//prox jogador
		panelI.setBounds(2*x, 10*x+5*y, 2*x, x);
		contentPane.add(panelI);
		panelI.setLayout(null);
		
		/*JButton btn = new JButton("New button");//declaracao de botao generico
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btn.setBounds(198, 100, 97, 25);
		contentPane.add(btn);*/
		
		//criando botoes
		for(int i = 0; i<3; i++) {
			for(int j = 0 ; j<12; j++) {
				//btn principal
				final int k = ((((j+1)*3)-i)%37);
				final int k2  = ((((j+2)*3)-i)%37);
				final int k3 = k-1;
				final int k4 = k2-1;
				int[] num = {k, k2};
				int[] num2 = {k, k3};
				int[] num3 = {k, k2, k3, k4};
				
				
				campo_apostas[i][j] = new JButton(Integer.toString(k));//criacao dos botoes principais do tabuleiro
				campo_apostas[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(checkValue()) {
							Aposta a = new Aposta(0, k, currentFicha);
							currentPlayer.addAposta(a);
							System.out.println(currentPlayer.getFichas());
							brd.setBudget(brd.getBudget()+a.getValor());
							atualizar();
							currentPlayer.setCont_rodadas_sem_jogar(0);
							//System.out.println(a.getValor() + " " + a.getIndiceAposta());
						}else {
							JOptionPane.showMessageDialog(null, "você não tem bufunfa o suficiente pra essa operaçao", "erro", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				});
				campo_apostas[i][j].setBounds((j*x) + (j*y), (i*x) + (i*y), x, x);
				/*int count = 0;
				for(int h = 0; h<vermelhos.length; i++) {//metodo pra pintar os quadrados de vermelho ou preto
					if(Integer.parseInt(campo_apostas[i][j].getText()) == vermelhos[h]) {
						campo_apostas[i][j].setBackground(Color.RED);
						count++;
					}
				}
				count = 0;
				if(count>0) {
					campo_apostas[i][j].setBackground(Color.GRAY);
				}*/
				//campo_apostas[i][j].setBackground(Color.RED);
				panelA.add(campo_apostas[i][j]);
				System.out.println(i + " " + j);;
				
				//btn secundario
				if(j<11) {
					campo_apostas_rectangle[i][j] = new JButton();
					campo_apostas_rectangle[i][j].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {	
							if(checkValue()) {
								Aposta d = new Aposta(num, currentFicha);
								currentPlayer.addAposta(d);
								brd.setBudget(brd.getBudget()+d.getValor());
							//	Aposta a = new Aposta(0, k, currentFicha);
							//	Aposta b = new Aposta(0, k2, currentFicha);
							//	currentPlayer.addAposta(a);
							//	currentPlayer.addAposta(b);
							//	brd.setBudget(brd.getBudget()+a.getValor());
							//	brd.setBudget(brd.getBudget()+b.getValor());
								atualizar();
								currentPlayer.setCont_rodadas_sem_jogar(0);
								System.out.println(k+ " " +k2);
							}else {
								JOptionPane.showMessageDialog(null, "você não tem bufunfa o suficiente pra essa operaçao", "erro", JOptionPane.INFORMATION_MESSAGE);
							}
						}
					});
					campo_apostas_rectangle[i][j].setBounds(x + j*x +(y*j), (x+y)*i, y, x);
					panelA.add(campo_apostas_rectangle[i][j]);
				}
				//btn terciario
				if(i<2) {
					campo_apostas_rectangle2[i][j] = new JButton();
					campo_apostas_rectangle2[i][j].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {		
							if(checkValue()) {
								Aposta d = new Aposta(num2, currentFicha);
								currentPlayer.addAposta(d);
								brd.setBudget(brd.getBudget()+d.getValor());
								/*
								Aposta a = new Aposta(0, k, currentFicha);
								Aposta b = new Aposta(0, k3, currentFicha);
								currentPlayer.addAposta(a);
								currentPlayer.addAposta(b);
								brd.setBudget(brd.getBudget()+a.getValor());
								brd.setBudget(brd.getBudget()+b.getValor());
								*/
								atualizar();
								currentPlayer.setCont_rodadas_sem_jogar(0);
								System.out.println(k+ " " +k3);
								
							}else {
								JOptionPane.showMessageDialog(null, "você não tem bufunfa o suficiente pra essa operaçao", "erro", JOptionPane.INFORMATION_MESSAGE);
							}
						}
					});
					campo_apostas_rectangle2[i][j].setBounds((y+x)*j, x+((x+y)*i), x, y);
					panelA.add(campo_apostas_rectangle2[i][j]);
				}
				//btn quaternario
				if(i<2 && j<11) {
					campo_apostas_square[i][j] = new JButton();
					campo_apostas_square[i][j].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {	
							if(checkValue()) {
								Aposta d = new Aposta(num3, currentFicha);
								currentPlayer.addAposta(d);
								brd.setBudget(brd.getBudget()+d.getValor());
								/*Aposta a = new Aposta(0, k, currentFicha);
								Aposta b = new Aposta(0, k2, currentFicha);
								Aposta c = new Aposta(0, k3, currentFicha);
								Aposta d = new Aposta(0, k4, currentFicha);
								currentPlayer.addAposta(a);
								currentPlayer.addAposta(b);
								currentPlayer.addAposta(c);
								currentPlayer.addAposta(d);
								brd.setBudget(brd.getBudget()+a.getValor());
								brd.setBudget(brd.getBudget()+b.getValor());
								brd.setBudget(brd.getBudget()+c.getValor());
								brd.setBudget(brd.getBudget()+d.getValor());
								*/
								atualizar();
								currentPlayer.setCont_rodadas_sem_jogar(0);
								//System.out.println(k+" "+k2+" "+k3+" "+k4);
								System.out.println(currentPlayer.getId()+" "+brd.getPlayer());
								
							}else {
								JOptionPane.showMessageDialog(null, "você não tem bufunfa o suficiente pra essa operaçao", "erro", JOptionPane.INFORMATION_MESSAGE);
							}
						}
					});
					campo_apostas_square[i][j].setBounds(x+((y+x)*j), x+((x+y)*i), y, y);
					panelA.add(campo_apostas_square[i][j]);
				}
			}
		}
		
		//hud jogadores
		for (int k=0; k<2; k++) {
			for(int j=0; j<brd.getQuantPlayers(); j++) {	
				hud_jogadores[k][j] = new JLabel("P "+j);
				hud_jogadores[k][j].setOpaque(true);
				hud_jogadores[k][j].setBounds((int) (((j*4*x)+(j*3.5*y))/2), x*k,(int) (((4*x)+(3.5*y))/2), x);
				if(k==1) {
					hud_jogadores[k][j].setText(Integer.toString(brd.getListaPlayers().get(j).getFichas()));
				}
				panelG.add(hud_jogadores[k][j]);
			}
		}
		hud_jogadores[0][0].setForeground(Color.GREEN);
		//criando hud budget
		hud_budget = new JLabel(Integer.toString(brd.getBudget()));
		hud_budget.setBounds(0, 0, 2*x, x);
		hud_budget.setOpaque(true);
		panelH.add(hud_budget);
		//
		//criando botado de proximo
		//
		//
		//
		//
		//
		passa_vez = new JButton("proximo");
		if(brd.getQuantPlayers()==1) {
			passa_vez.setText("girar roleta");
		}
		passa_vez.setBounds(0, 0, 2*x, x);
		passa_vez.setOpaque(true);				
		passa_vez.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atualizar();
				checaFalidos();
				if(currentPlayer.getCont_rodadas_sem_jogar()>=3) {
					JOptionPane.showMessageDialog(null, "voce nao pode pular mais rodadas", "info", JOptionPane.INFORMATION_MESSAGE);
					atualizar();
					doSomething = false;
				}
				/*if(totPlayersFalidos == (brd.getQuantPlayers()-1)) {
					temUltimo = true;
					System.out.println("aaaaaaaaaa");
				}*/
				if(doSomething) {
					/*if(temUltimo) {
						passa_vez.setText("girar roleta");
					}*/
					hud_jogadores[0][brd.getPlayer()].setForeground(Color.BLACK);
					brd.setRodada(brd.getRodada()+1);
				
					if(currentPlayer.getApostas().isEmpty()) {
						currentPlayer.setCont_rodadas_sem_jogar(currentPlayer.getCont_rodadas_sem_jogar()+1);
					}else {
						currentPlayer.setCont_rodadas_sem_jogar(0);
					}
					if(passa_vez.getText()=="girar roleta") {
						ultimoPlayer = 0;
						giraRoleta();
						JOptionPane.showMessageDialog(null, Integer.toString(sorteado), "info", JOptionPane.INFORMATION_MESSAGE);
						checaFalidos();
						if(brd.getBudget()<=0) {
							JOptionPane.showMessageDialog(null, "acabou o dinheiro da mesa, parabens player " +checaMelhorPlayer()+
									" voce finalizou com o maior numero de fichas", "info", JOptionPane.INFORMATION_MESSAGE);
						}
						for(Player p : brd.getListaPlayers()) {
							p.getApostas().clear();
						}
						proximoPlayer();
					}else {
						proximoPlayer();
					}
					atualizar();
				}
				System.out.println(ultimoPlayer);
				doSomething = true;
				atualizar();
			}
		});
	
		panelI.add(passa_vez);
		currentPlayer.setCont_rodadas_sem_jogar(0);
		//
		///
		
		//
		//
		
		//VETORES
		
		//linhas
		linhas_apostas[0] = new JButton("L1");
		linhas_apostas[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				Aposta a = new Aposta(1, 0, currentFicha);
				currentPlayer.addAposta(a);
				System.out.println(currentPlayer.getFichas());
				brd.setBudget(brd.getBudget()+a.getValor());
				currentPlayer.setCont_rodadas_sem_jogar(0);
				atualizar();
	 		}
		});
		linhas_apostas[0].setBounds(0, 0, x, x);
		panelB.add(linhas_apostas[0]);
		//
		linhas_apostas[1] = new JButton("L2");
		linhas_apostas[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Aposta a = new Aposta(1, 1, currentFicha);
				System.out.println(currentPlayer.getFichas());
				currentPlayer.addAposta(a);
				System.out.println(currentPlayer.getFichas());
				brd.setBudget(brd.getBudget()+a.getValor());
				currentPlayer.setCont_rodadas_sem_jogar(0);
				atualizar();
 			}
		});
		linhas_apostas[1].setBounds(0, 1*(x+y), x, x);
		panelB.add(linhas_apostas[1]);
		//
		linhas_apostas[2] = new JButton("L3");
		linhas_apostas[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				Aposta a = new Aposta(1, 2, currentFicha);
				System.out.println(currentPlayer.getFichas());
				currentPlayer.addAposta(a);
				System.out.println(currentPlayer.getFichas());
				brd.setBudget(brd.getBudget()+a.getValor());
				currentPlayer.setCont_rodadas_sem_jogar(0);
				atualizar();
 			}
		});
		linhas_apostas[2].setBounds(0, 2*(x+y), x, x);
		panelB.add(linhas_apostas[2]);
		//////////////////////////////////////
		//duzias
		duzia_apostas[0] = new JButton("1-12");
		duzia_apostas[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Aposta a = new Aposta(2, 0, currentFicha);
				currentPlayer.addAposta(a);
				System.out.println(currentPlayer.getFichas());
				brd.setBudget(brd.getBudget()+a.getValor());
				currentPlayer.setCont_rodadas_sem_jogar(0);
				atualizar();
			}
		});
		duzia_apostas[0].setBounds((int) ((0*4*x)+(0*3.5*y)), 0, (int) ((4*x)+(3.5*y)), 2*x);
		panelC.add(duzia_apostas[0]);
		//
		duzia_apostas[1] = new JButton("13-25");
		duzia_apostas[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Aposta a = new Aposta(2, 1, currentFicha);
				currentPlayer.addAposta(a);
				System.out.println(currentPlayer.getFichas());
				brd.setBudget(brd.getBudget()+a.getValor());
				currentPlayer.setCont_rodadas_sem_jogar(0);
				atualizar();
			}
		});
		duzia_apostas[1].setBounds((int) ((1*4*x)+(1*3.5*y)), 0, (int) ((4*x)+(3.5*y)), 2*x);
		panelC.add(duzia_apostas[1]);
		//
		duzia_apostas[2] = new JButton("26-38");
		duzia_apostas[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Aposta a = new Aposta(2, 2, currentFicha);
				currentPlayer.addAposta(a);
				System.out.println(currentPlayer.getFichas());
				brd.setBudget(brd.getBudget()+a.getValor());
				currentPlayer.setCont_rodadas_sem_jogar(0);
				atualizar();
			}
		});
		duzia_apostas[2].setBounds((int) ((2*4*x)+(2*3.5*y)), 0, (int) ((4*x)+(3.5*y)), 2*x);
		panelC.add(duzia_apostas[2]);
		////////////////////////////////////
		//  1/2
		metade_apostas[0] = new JButton("1-18");
		metade_apostas[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Aposta a = new Aposta(3, 0, currentFicha);
				currentPlayer.addAposta(a);
				System.out.println(currentPlayer.getFichas());
				brd.setBudget(brd.getBudget()+a.getValor());
				currentPlayer.setCont_rodadas_sem_jogar(0);
				atualizar();
			}
		});
		metade_apostas[0].setBounds((int) (((0*4*x)+(0*3.5*y))/2), 0,(int) (((4*x)+(3.5*y))/2), 2*x);
		panelD.add(metade_apostas[0]);
		//
		metade_apostas[1] = new JButton("even");
		metade_apostas[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Aposta a = new Aposta(3, 1, currentFicha);
				currentPlayer.addAposta(a);
				System.out.println(currentPlayer.getFichas());
				brd.setBudget(brd.getBudget()+a.getValor());
				currentPlayer.setCont_rodadas_sem_jogar(0);
				atualizar();
			}
		});
		metade_apostas[1].setBounds((int) (((1*4*x)+(1*3.5*y))/2), 0,(int) (((4*x)+(3.5*y))/2), 2*x);
		panelD.add(metade_apostas[1]);
		//
		metade_apostas[2] = new JButton("red");
		metade_apostas[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Aposta a = new Aposta(3, 2, currentFicha);
				currentPlayer.addAposta(a);
				System.out.println(currentPlayer.getFichas());
				brd.setBudget(brd.getBudget()+a.getValor());
				currentPlayer.setCont_rodadas_sem_jogar(0);
				atualizar();
			}
		});
		metade_apostas[2].setBounds((int) (((2*4*x)+(2*3.5*y))/2), 0,(int) (((4*x)+(3.5*y))/2), 2*x);
		panelD.add(metade_apostas[2]);
		//
		metade_apostas[3] = new JButton("black");
		metade_apostas[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Aposta a = new Aposta(3, 3, currentFicha);
				currentPlayer.addAposta(a);
				System.out.println(currentPlayer.getFichas());
				brd.setBudget(brd.getBudget()+a.getValor());
				currentPlayer.setCont_rodadas_sem_jogar(0);
				atualizar();
			}
		});
		metade_apostas[3].setBounds((int) (((3*4*x)+(3*3.5*y))/2), 0,(int) (((4*x)+(3.5*y))/2), 2*x);
		panelD.add(metade_apostas[3]);
		//
		metade_apostas[4] = new JButton("odd");
		metade_apostas[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Aposta a = new Aposta(3, 4, currentFicha);
				currentPlayer.addAposta(a);
				System.out.println(currentPlayer.getFichas());
				brd.setBudget(brd.getBudget()+a.getValor());
				currentPlayer.setCont_rodadas_sem_jogar(0);
				atualizar();
			}
		});
		metade_apostas[4].setBounds((int) (((4*4*x)+(4*3.5*y))/2), 0,(int) (((4*x)+(3.5*y))/2), 2*x);
		panelD.add(metade_apostas[4]);
		//
		metade_apostas[5] = new JButton("19-36");
		metade_apostas[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Aposta a = new Aposta(3, 5, currentFicha);
				currentPlayer.addAposta(a);
				System.out.println(currentPlayer.getFichas());
				brd.setBudget(brd.getBudget()+a.getValor());
				currentPlayer.setCont_rodadas_sem_jogar(0);
				atualizar();
			}
		});
		metade_apostas[5].setBounds((int) (((5*4*x)+(5*3.5*y))/2), 0,(int) (((4*x)+(3.5*y))/2), 2*x);
		panelD.add(metade_apostas[5]);
		/////////////////////////////////////////////////////
		//especiais
		aposta_especial[0] = new JButton("vdz");
		aposta_especial[0].addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
				Aposta a = new Aposta(4, 0, currentFicha);
				currentPlayer.addAposta(a);
				System.out.println(currentPlayer.getFichas());			
				brd.setBudget(brd.getBudget()+a.getValor());
				currentPlayer.setCont_rodadas_sem_jogar(0);
				atualizar();
	 		}
		});
		aposta_especial[0].setBounds((int) (((0*4*x)+(0*3.5*y))/2), 0,(int) (((4*x)+(3.5*y))/2), x);
		panelE.add(aposta_especial[0]);
		//
		aposta_especial[1] = new JButton("tier");
		aposta_especial[1].addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
				Aposta a = new Aposta(4, 1, currentFicha);
				currentPlayer.addAposta(a);
				System.out.println(currentPlayer.getFichas());			
				brd.setBudget(brd.getBudget()+a.getValor());
				currentPlayer.setCont_rodadas_sem_jogar(0);
				atualizar();
	 		}
		});
		aposta_especial[1].setBounds((int) (((1*4*x)+(1*3.5*y))/2), 0,(int) (((4*x)+(3.5*y))/2), x);
		panelE.add(aposta_especial[1]);
		//
		aposta_especial[2] = new JButton("orph");
		aposta_especial[2].addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
				Aposta a = new Aposta(4, 2, currentFicha);
				currentPlayer.addAposta(a);
				System.out.println(currentPlayer.getFichas());			
				brd.setBudget(brd.getBudget()+a.getValor());
				currentPlayer.setCont_rodadas_sem_jogar(0);
				atualizar();
	 		}
		});
		aposta_especial[2].setBounds((int) (((2*4*x)+(2*3.5*y))/2), 0,(int) (((4*x)+(3.5*y))/2), x);
		panelE.add(aposta_especial[2]);
		//
		aposta_especial[3] = new JButton();
		aposta_especial[3].addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			Aposta a = new Aposta(4, 3, currentFicha);
				currentPlayer.addAposta(a);
				System.out.println(currentPlayer.getFichas());			
				brd.setBudget(brd.getBudget()+a.getValor());
				currentPlayer.setCont_rodadas_sem_jogar(0);
				atualizar();
	 		}
		});
		aposta_especial[3].setBounds((int) (((3*4*x)+(3*3.5*y))/2), 0,(int) (((4*x)+(3.5*y))/2), x);
		panelE.add(aposta_especial[3]);
		//
		aposta_especial[4] = new JButton();
		aposta_especial[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Aposta a = new Aposta(4, 4, currentFicha);
				currentPlayer.addAposta(a);
				System.out.println(currentPlayer.getFichas());			
				brd.setBudget(brd.getBudget()+a.getValor());
				currentPlayer.setCont_rodadas_sem_jogar(0);
				atualizar();
	 		}
		});
		aposta_especial[4].setBounds((int) (((4*4*x)+(4*3.5*y))/2), 0,(int) (((4*x)+(3.5*y))/2), x);
		panelE.add(aposta_especial[4]);
		/////////////////////////////////////
		//fichas
		fichas[0] = new JButton("5");
		fichas[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentFicha = 5;
 			}
		});
		fichas[0].setBounds(0, x*0, x, x);
		panelF.add(fichas[0]);
		//
		fichas[1] = new JButton("10");
		fichas[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentFicha = 10;
 			}
		});
		fichas[1].setBounds(0, x*1, x, x);
		panelF.add(fichas[1]);
		//
		fichas[2] = new JButton("15");
		fichas[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentFicha = 15;
 			}
		});
		fichas[2].setBounds(0, x*2, x, x);
		panelF.add(fichas[2]);
		//
		fichas[3] = new JButton("25");
		fichas[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentFicha = 25;
 			}
		});
		fichas[3].setBounds(0, x*3, x, x);
		panelF.add(fichas[3]);
		//
		fichas[4] = new JButton("100");
		fichas[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentFicha = 100;
 			}
		});
		fichas[4].setBounds(0, x*4, x, x);
		panelF.add(fichas[4]);
	}
		
	
	public void atualizar() {
		hud_budget.setOpaque(true);
		hud_budget.setText(Integer.toString(brd.getBudget()));										 	//atualizo o budget da mesa
		hud_jogadores[1][brd.getPlayer()].setText(Integer.toString(currentPlayer.getFichas()));		//atualizo o hud players
		hud_jogadores[0][brd.getPlayer()].setForeground(Color.GREEN);
		if(currentPlayer.getId()== def().getId()) {//analisar(brd.getListaPlayers().get(brd.getQuantPlayers()-1))
			passa_vez.setText("girar roleta");
		}else {
			passa_vez.setText("proximo");
		}
	}
	public void giraRoleta() {								//gire roleta
		sorteado=brd.sorteio();
		for(Player p : brd.getListaPlayers()) {
			int playerPot= 0;
			for(Aposta a : p.getApostas()) {
				if(a.getIndiceAposta()==-1) {
					a.checaAposta2(sorteado);
					playerPot+= a.getPot();
				}else {
					a.checaAposta(sorteado);
					playerPot += a.getPot();
				}
			}
			p.setFichas(p.getFichas()+playerPot);			//adiciono as fihas conseguidas na rodada
			brd.setBudget(brd.getBudget()-playerPot);
			hud_jogadores[1][p.getId()].setText(Integer.toString(p.getFichas()));
		}
	}
	public void checaFalidos() {
		totPlayersFalidos = 0;
		for(Player p : brd.getListaPlayers()) {
			if(p.getFichas()==-1) {
				hud_jogadores[0][p.getId()].setOpaque(true);
				hud_jogadores[0][p.getId()].setForeground(Color.RED);
				totPlayersFalidos += 1;
			}
			if(p.getFichas()==0) {
				System.out.println("kkkkkk");
				hud_jogadores[0][p.getId()].setOpaque(true);
				hud_jogadores[0][p.getId()].setForeground(Color.RED);
				hud_jogadores[1][p.getId()].setText("0");
				falidos.add(p);
				p.setFichas(-1);
			}
			if(p.getFichas()>0) {
				lastPlayer = p;
			}
		}
		if(totPlayersFalidos == brd.getQuantPlayers()) {
			JOptionPane.showMessageDialog(null, "acabou o jogo, não há mais players com fichas", "aviso", JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			dispose();
			TelaInicial f = new TelaInicial();
			f.setVisible(true);
		}
	}
	
	public boolean checkValue() {							//checo se o jogador tem dinheiro o suficiente
		if(currentFicha>currentPlayer.getFichas()) {
			return false;
		}else {
			return true;
		}
	}
	public String checaMelhorPlayer() {
		Player best = new Player(12);
		for(Player p : brd.getListaPlayers()) {
			if(p.getFichas()>best.getFichas()) {
				best = p;
			}
		}
		return Integer.toString(best.getId());
	}
	int ultimoPlayer = 0;
	public void proximoPlayer() {
		currentPlayer = brd.getListaPlayers().get(brd.getPlayer());
		if(currentPlayer.getFichas()==-1) {
			brd.setRodada(brd.getRodada()+1);
			ultimoPlayer++;
			proximoPlayer();
		}
	}
	/*public int analisar(Player p) { //metodo pra descobrir o player mais a direita com fichas 
		Player e = brd.getListaPlayers().get(p.getId());
		if(e.getId()> 0) {
			e = brd.getListaPlayers().get(p.getId()-1);
		}
		if(p.getId()==0) {
			if(p.getFichas()>0) {
				return p.getId();
			}else {
				JOptionPane.showMessageDialog(null, "acabou o jogo, não há mais players com fichas", "aviso", JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
				dispose();
				TelaInicial f = new TelaInicial();
				f.setVisible(true);
			}
		}
		if(p.getFichas()>0) {
			return p.getId();
		}
		if(p.getFichas()<=0) {
			analisar(e);
		}
		return -1;
	}*/
	public Player def() {
		Player maiorPlayer = brd.getListaPlayers().get(0);
		for(Player p : brd.getListaPlayers()) {
			if(!falidos.contains(p)) {
				if(maiorPlayer.getId()<p.getId()) {
					maiorPlayer = p;
				}
			}
		}
		return maiorPlayer;
	}
}
