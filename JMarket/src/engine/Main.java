package engine;

import gui.GraphManager;
import gui.MainFrame;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;

public class Main {
	static DataManagement dm;
	static FileManagement fm;					//declaring file manager
	static APIConnector apiConnector;			//declaring api connector
	static List<Acao> acoes;				//declare stock list
	static MainFrame mf;
	static DataBase db;
	static Color bgColor = new Color(0, 0, 0);
	
	public static void main(String[] args) {
		mf = new MainFrame();
		mf.setBackground(bgColor);
		mf.setLocation(100, 100);
		mf.setVisible(true);
		acoes = new ArrayList<>();				//instantiating stocks
		fm = new FileManagement();
		dm = new DataManagement();
		apiConnector = new APIConnector();
		
		while(mf.getPodeRodar() == false) {
			System.out.println();
		}
		rodar();
		//System.out.println(a.getDataBase().getBollingerInferior()[0]);
	}
	    //System.out.println(System.getProperty("user.dir"));
		/*
		for(int i = 0; i<100; i++) {
			System.out.println(fm.getDataBase().getClose()[i]);
			System.out.println(fm.getDataBase().getTimeStamp()[i]);
		}
		System.out.println("------------------------");
		System.out.println(fm.getDataBase().getMediaMovel()[0]);
		System.out.println(fm.getDataBase().getDesvioPadrao()[0]);
		System.out.println(fm.getDataBase().getBollingerSuperior()[0]);
		System.out.println(fm.getDataBase().getBollingerInferior()[0]);
		System.out.println("------------------------");
		System.out.println(fm.getDataBase().getMediaMovel()[79]);
		System.out.println(fm.getDataBase().getDesvioPadrao()[79]);
		System.out.println(fm.getDataBase().getBollingerSuperior()[79]);
		System.out.println(fm.getDataBase().getBollingerInferior()[79]);*/
		/*} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {};*/
	
	
	public static void rodar() {  	//method populates the arrays of stocks, then loops the requisition from api every 5 minutes
		try {
			acoes = mf.getAcoes();	//we get the stocks required by the user in the main frame
			for(Acao acao: acoes) {	//iterating stocks in the list
				apiConnector.getFileFromAPI(acao.getNome());		//update datafile
				fm.openFile(acao.getNome(), acao.getDataBase());	//fill stock's database							
			//	dm.avaliarAcao(acao);
			}
			createGraphicManager();	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(true) {
			try {
				Thread.sleep(300000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}	
			for(Acao acao: acoes) {
				apiConnector.getFileFromAPI(acao.getNome());		//update date file
				try {
					fm.openFile(acao.getNome(), acao.getDataBase());//refill stock's database
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				dm.avaliarAcao(acao);
			}
			createGraphicManager();
		}
	}
	public static void createGraphicManager() {			//creates and updates the graphic manager
		int cont = 0;
		for(Acao a : acoes) {
			if(acoes.size()==2||acoes.size()==3||acoes.size()==4) {
				GraphManager gm = new GraphManager(a, 2);	//creating and setting graphic managers for stock list
				a.setGraphManager(gm);
			}else {
				GraphManager gm = new GraphManager(a, 1);	//creating and setting graphic managers for stock list
				a.setGraphManager(gm);
			}
			int varY = 0;
			if(cont>=2) {
				varY = 1;
			}
			a.getGraphManager().setLocation((mf.getWindowWidth()/2)*(cont%2), (mf.getWindowHeight()/2)*varY);
		
			mf.getContentPane().add(a.getGraphManager());							//adds graph manager to the content pane
			a.getGraphManager().setVisible(true);
			cont++;
		}
	}
}