package Triki;

import java.util.Random;

public class JugadorMinMax {

	private String sim;
	private String minSim;
	private String maxSim;
	Random rdn = new Random();
	public JugadorMinMax(String sim){
		this.maxSim=sim;
		if (sim =="X") {
			this.minSim="O";
		}
		else
		{
			this.minSim="X";
		}
	}
	
	public String[][] jugarTurno(Juego j) {
		Nodo raiz= new Nodo(j.getEstado(),null,true,maxSim,j,0);
		raiz.minMax(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY);
		return (raiz.getHijos().get(rdn.nextInt(raiz.getHijos().size()))).getEstado();//ESte return de acá yo no lo entendi
		
	}
}
