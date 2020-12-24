package engine;

import gui.GraphManager;

public class Acao {
	DataBase dataBase;
	String nome;
	GraphManager gm;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String a){
		nome = a;
	}
	
	public void setDataBase(DataBase db) {
		dataBase = db;
	}
	public DataBase getDataBase() {
		return dataBase;
	}
	
	public Acao(String n) {
		dataBase = new DataBase();
		nome = n;
	}
	
	public void setGraphManager(GraphManager g) {
		gm = g;
	}
	public GraphManager getGraphManager() {
		return gm;
	}
}	
