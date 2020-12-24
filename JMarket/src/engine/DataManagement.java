package engine;
import java.lang.Math;

public class DataManagement {
	public int parse;										//quantas parcelas serao avaliadas na media movel
	
	public double calculaMedia(double[] vector) {	
		double t = 0;
		for(int i = 0; i<vector.length; i++) {
			t+=vector[i];
		}
		return t/vector.length;
	}
	
	public double calculaDesvioPadrao(double[] vector) {	//calculamos o desvio padrao: usado de parametro no calculo da largura das bandas de bollinger
		double mediaSimples = calculaMedia(vector);			//chamo a media simples do vetor
		double somatorioVariancia = 0;						//variavel para somar a diferença entre valor E media simples
		
		for(int i = 0; i<vector.length; i++) {				
			somatorioVariancia += Math.pow(vector[i]-mediaSimples, 2); 
		}
		double v = somatorioVariancia/vector.length;		//variavel auxiliar
		return Math.sqrt(v);								//valor de retorno
	}
	/*
	public double[] calculaMediaMovel(double[] vector) {
		double[] v = new double[1];
		for(int i = 0; i<vector.length; i++) {
		}
		return v;
	}*/
	
	public double calculaBollingerInferior(double mediaM, double desvioP) {
		return mediaM - 2*desvioP;
	}
	public double calculaBollingerSuperior(double mediaM, double desvioP) {
		return mediaM + 2*desvioP;
	}
	
	
	public void avaliarAcao(Acao a) {														//avaliamos se é proveitoso comprar ou vender a acao
		if(a.getDataBase().getBollingerSuperior()[0]<Double.parseDouble(a.getDataBase().getClose()[0])) {
			System.out.println("venda "+a.getNome());
		}
		if(a.getDataBase().getBollingerInferior()[0]>Double.parseDouble(a.getDataBase().getClose()[0])) {
			System.out.println("compre "+a.getNome());
		}
		if(a.getDataBase().getBollingerSuperior()[0]>Double.parseDouble(a.getDataBase().getClose()[0]) && 
				a.getDataBase().getBollingerInferior()[0]<Double.parseDouble(a.getDataBase().getClose()[0])) {
			System.out.println("... "+a.getNome());
		}
		System.out.println("ultimo bollinger superior: "+a.getDataBase().getBollingerSuperior()[0]+"\nultimo boolinger inferior: "
		+a.getDataBase().getBollingerInferior()[0]+"\nultima media movel "+a.getDataBase().getMediaMovel()[0] + "\nultimo close "+Double.parseDouble(a.getDataBase().getClose()[0]));
		System.out.println("------------------------------------------------");
	}
}
