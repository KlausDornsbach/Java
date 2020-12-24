package engine;

import java.util.Random;

import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.List;

public class Board {
	//slots graficos do tabuleiro
	/*int[][] campo_apostas;
	int[][] linhas_apostas;
	int[][] duzias_apostas;
	int[][] metade_apostas;
	int[][] fichas;
	int[][] hud_jogadores;
	int[][] hud_budget;
	int[][] passa_vez;*/
	Player[] players = new Player[4];
	//elementos do tabuleiro
	Random rand;
	List<Player> lst_players = new ArrayList<Player>();
	int pl;   //quantos players tem no jogo
	int cont_rodada;
	int budget;
	boolean acabou;
	
	public Board(int p) {
		pl=p;
		for(int i = 0; i < p; i++) {
	          players[i] = new Player(i);
	          lst_players.add(players[i]);
		}
		cont_rodada=0;
		budget = 1000;
		rand = new Random();
		acabou = false;
		//play();
	}
	
	public List<Player> getListaPlayers() {
		return lst_players;
	}
	
	public int getBudget() {
		return budget;
	}
	
	public void setBudget(int n) {
		budget = n;
	}
	
	public int getRodada(){
		return cont_rodada;
	}
	public int getPlayer(){
		return (cont_rodada)%pl;
	}
	
	public int getQuantPlayers() {
		return pl;
	}
	
	public void setRodada(int n) {
		cont_rodada = n;
	}
	
	public int sorteio() {
		return rand.nextInt(36);
	}
	
	/*public Player nextPlayer(Player currentPlayer) {
		Player player = new Player(10);
		if(currentPlayer.getId()) {
			
		}
		return player;
	}*/
}
