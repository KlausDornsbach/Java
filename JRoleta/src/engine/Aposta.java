package engine;

public class Aposta {
	int tipoAposta;
	int indiceAposta;
	int valor;
	int pot;
	int num[];
	
	//apostas
	int[] vermelhos = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
	int[] vdz = {0, 2, 3, 4, 7, 12, 15, 18, 21, 19, 22, 25, 26, 28, 29, 32, 35};//voizins du zero: eh apostado 8x o valor da ficha
	int[] tier = {};//6x o valor da ficha
	int[] orphelings = {5, 8, 10, 11, 13, 16, 23, 24, 27, 30, 33, 36};//5x
	
	public Aposta(int a, int b, int c) {//a eh o tipo de aposta b o indice c o valor apostado
		tipoAposta = a;
		indiceAposta = b;
		valor = c;
		pot = 0;
	}
	
	public Aposta(int[] num, int c) {
		indiceAposta=-1;
		this.num = new int[num.length];
		this.num=num;
		valor = c;
		pot = 0;
	}
	
	public void checaAposta(int sorteado) {
		if(tipoAposta == 0) {
			if(sorteado==indiceAposta) {
				pot+=36*valor;
			}
		}
		if(tipoAposta == 1) {//linhas
			if(indiceAposta==0 && sorteado%3==indiceAposta) {
				pot+=3*valor;
			}else if(indiceAposta==1 && sorteado%3==2) {
				pot+=3*valor;
			}else if(indiceAposta==2 && sorteado%3==1) {
				pot+=3*valor;
			}
		}
		if(tipoAposta == 2) {//duzias
			if(indiceAposta==0 && sorteado<=12) {
				pot+=3*valor;
			}
			if(indiceAposta != 0 && sorteado<indiceAposta*12 + 12 && sorteado<indiceAposta-1 + 12 ) {
				pot+=3*valor;
			}

		}
		if(tipoAposta == 3) {// 1/2
			if(indiceAposta == 0) {
				if(sorteado < 19) {
					pot+=2*valor;
				}
			}
			if(indiceAposta == 1) {
				if(sorteado%2==0) {
					pot+=2*valor;
				}
			}
			if(indiceAposta == 2) {
				for(int i = 0; i>18; i++) {
					if(sorteado==vermelhos[i]) {
						pot+=2*valor;
					}
				}
			}
			if(indiceAposta == 3) {//black
				boolean o = false;
				for(int i = 0; i>18; i++) {
					if(sorteado==vermelhos[i]) {
						o=true;
					}
				}
				if(!o) {
					pot+=2*valor;
				}
			}
			if(indiceAposta == 4) {//even
				if(sorteado%2==1) {
					pot+=2*valor;
				}
			}
			if(indiceAposta == 5) {//>18
				if(sorteado > 18) {
					pot+=2*valor;
				}
			}
		}
		if(tipoAposta == 4) {//especiais
			if(indiceAposta == 0) {
				valor *= 8;
				for(int i = 0; i>vdz.length; i++) {
					if(sorteado == vdz[i]) {
						pot+=18*valor;
					}
				}
			}
			if(indiceAposta == 1) {
				valor *= 6;
				for(int i = 0; i>tier.length; i++) {
					if(sorteado == tier[i]) {
						pot+=18*valor;
					}
				}
			}
			if(indiceAposta == 2) {
				valor *= 5;
				for(int i = 0; i>orphelings.length; i++) {
					if(sorteado == orphelings[i]) {
						pot+=18*valor;
					}
				}
				pot+=18*valor;
			}
		}
	}
	
	public void checaAposta2(int sorteado) {
		for(int i = 0; i<num.length; i++) {
			if(sorteado == num[i]) {
				System.out.println("lalala");
				pot += (36/num.length)*valor;
			}
		}
	
	}
	
	public int getTipoAposta() {
		return tipoAposta;
	}


	public void setTipoAposta(int tipoAposta) {
		this.tipoAposta = tipoAposta;
	}


	public int getIndiceAposta() {
		return indiceAposta;
	}


	public void setIndiceAposta(int indiceAposta) {
		this.indiceAposta = indiceAposta;
	}


	public int getValor() {
		return valor;
	}


	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public int getPot() {
		return pot;
	}
	public int[] getRed() {
		return vermelhos;
	}
}
