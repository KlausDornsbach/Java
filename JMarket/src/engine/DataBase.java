package engine;

public class DataBase {
	String[] timeStamp = new String[100];
	String[] close = new String[100];

	double[] mediaMovel = new double[80];
	double[] desvioPadrao = new double[80];
	double[] bollingerSuperior = new double[80];
	double[] bollingerInferior = new double[80];

	
	public void addTimeStamp(int a, String b) {
		timeStamp[a]=b;
	}
	public void addClose(int a, String b) {
		close[a]=b;
	}
	public String[] getTimeStamp(){
		return timeStamp;
	}
	public String[] getClose(){
		return close;
	}
	
	public double[] getMediaMovel() {
		return mediaMovel;
	}
	public void setMediaMovel(double[] mediaMovel) {
		this.mediaMovel = mediaMovel;
	}
	public void addMediaMovel(int a, double b) {
		mediaMovel[a]=b;
	}
	
	public double[] getDesvioPadrao() {
		return desvioPadrao;
	}
	public void setDesvioPadrao(double[] desvioPadrao) {
		this.desvioPadrao = desvioPadrao;
	}
	public void addDesvioPadrao(int a, double b) {
		desvioPadrao[a]=b;
	}
	
	public double[] getBollingerSuperior() {
		return bollingerSuperior;
	}
	public void setBollingerSuperior(double[] bollingerSuperior) {
		this.bollingerSuperior = bollingerSuperior;
	}
	public void addBollingerSuperior(int a, double b) {
		bollingerSuperior[a]=b;
	}
	
	public double[] getBollingerInferior() {
		return bollingerInferior;
	}
	public void setBollingerInferior(double[] bollingerInferior) {
		this.bollingerInferior = bollingerInferior;
	}
	public void addBollingerInferior(int a, double b) {
		bollingerInferior[a]=b;
	}
	
	///////////////////////////////////////////////////////////////////////////////
	
	public double getMenorBlnf() {		//returns lowest inferior bollinger band
		double menor = 999999;
		for(int i = 0; i<80; i++) {
			if(bollingerInferior[i]<menor) {
				menor = bollingerInferior[i];
			}
		}
		return menor;
		
	}
	public double getMaiorBlSup() {		//returns highest superior bollinger band
		double maior = -999999;
		for(int i = 0; i<80; i++) {
			if(bollingerSuperior[i]>maior) {
				maior = bollingerSuperior[i];
			}
			//System.out.println(bollingerSuperior[i]);
		}
		return maior;
	}
	
	public double getVarianciaMedia() {
		double maior = -99999;
		double menor = 99999;
		for(int i=0; i<80; i++) {
			if(mediaMovel[i]<menor) {
				menor = mediaMovel[i];
			}
			if(mediaMovel[i]>maior) {
				maior = mediaMovel[i];
			}
		}
		return maior - menor;
	}
	
	public double getMenorMedia() {
		double menor = 99999;
		for(int i=0; i<80; i++) {
			if(mediaMovel[i]<menor) {
				menor = mediaMovel[i];
			}
		}
		return menor;
	}
}
