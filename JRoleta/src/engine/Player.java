package engine;

import java.util.ArrayList;
import java.util.List;

public class Player {
	int fichas;
	int cont_rodadas_sem_jogar;
	List <Aposta> apostas;
	int id;
	
	public Player(int id) {//construtor de jogador
		this.id = id;
		fichas = 100;
		cont_rodadas_sem_jogar=0;
		apostas = new ArrayList<>();
	}

	public void addAposta(Aposta a) {//adiciona aposta
		if(0 <= fichas - a.getValor()) {
			fichas-= a.getValor();
			apostas.add(a);
		}
	}
	
	//getters e setters
	
	public int getFichas() {
		return fichas;
	}

	public void setFichas(int fichas) {
		this.fichas = fichas;
	}

	public int getCont_rodadas_sem_jogar() {
		return cont_rodadas_sem_jogar;
	}

	public void setCont_rodadas_sem_jogar(int cont_rodadas_sem_jogar) {
		this.cont_rodadas_sem_jogar = cont_rodadas_sem_jogar;
	}

	public List<Aposta> getApostas() {
		return apostas;
	}

	public void setApostas(List<Aposta> apostas) {
		this.apostas = apostas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
