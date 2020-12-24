package engine;

public interface DataMInterface {
	public double calculaDesvioPadrao(double[] vector); //metodo para saber a largura das bandas de bollinger
	public double[] calculaMediaMovel(double[] vector);	//vetor vai ter length definida pelo usuario
	public double calculaMedia(double[] vector);		//calcula media simples de um vetor
}
