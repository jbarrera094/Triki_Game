package Triki;

import java.util.ArrayList;
import java.util.Arrays;

public class Nodo {
	private int nodos= 1;
	private String[][] estado = new String[3][3];
	private Nodo padre;
	private boolean isMax;
	private String maxSim;
	private String minSim;
	private Juego juego;
	private int profunidad = 0 ;
	private String sim;
	ArrayList<Nodo> hijos = new ArrayList<Nodo>();
	
	public Nodo(String[][] e, Nodo p, boolean m, String sM, Juego j, int profundida) {
		
		this.estado=e;
		this.padre=p;
		this.isMax=m;
		this.maxSim=sM;
		this.juego=j;
		this.profunidad=profundida;
		if (maxSim=="X") {
			this.minSim="O";
		}
		else {
			this.minSim="X";
		}
		if(this.isMax) {
			this.sim=maxSim;
			
		}
		else {
			this.sim=minSim;
		}
		
		
	}
	
	public int utilidad(String ganador) {
		int utilidad = 0;
		
		if (ganador!=null) {
			
			if(ganador==this.maxSim) {
				utilidad=100;
			}
			if(ganador==this.minSim) {
				utilidad=-100;
			}
			if (this.isMax) {
				
				utilidad = utilidad + this.profunidad;
			}
			else{
				utilidad= utilidad- this.profunidad;
			}
		}
		return utilidad;
		
	}
	
	
	public Puntaje minMax(float alpha, float beta) {
		Puntaje p = new Puntaje();
		Test t = juego.testTerminal(estado);
		juego.setTerminal(t.isB());
		float mejorHijo;
		if (juego.isTerminal()) {
			float utlilidad = utilidad(t.getS());
			p.setPuntaje(utlilidad);
			p.setState(false);
			return p;
		}
		else {
			
			if(isMax) {
				mejorHijo=Float.NEGATIVE_INFINITY;
			}
			else {
				mejorHijo=Float.POSITIVE_INFINITY;
			}
				for (int i = 0;i<3;i++) {
					for(int j = 0 ; j<3;j++) {
						if (estado[i][j]==null) {
							String[][] copy = cloneArray(estado);
							copy[i][j]=sim;
							Nodo hijo = new Nodo(copy,this,!isMax,maxSim,juego,profunidad+1);
							nodos=nodos+1;
							p = hijo.minMax(alpha,beta);
							if(p.isState()) {
								continue;
							}
							if(isMax) {
								alpha=Math.max(alpha, p.getPuntaje());
							}
							else {
								beta=Math.min(beta, p.getPuntaje());
							}
							if (alpha>beta) {
								p.setPuntaje(0);
								p.setState(true);
								return p;
							}
							if((isMax && p.getPuntaje()>mejorHijo) ||!isMax && p.getPuntaje()<mejorHijo) {
								hijos.clear();;
								hijos.add(hijo);
								mejorHijo=p.getPuntaje();
							}
							if(p.getPuntaje()==mejorHijo) {
								hijos.add(hijo);
							}
							
						}
					}
				}
				
				p.setPuntaje(mejorHijo);
				p.setState(false);
				return p;
			}
		
		
		
		
		
	}

	public int getNodos() {
		return nodos;
	}

	public void setNodos(int nodos) {
		this.nodos = nodos;
	}

	public String[][] getEstado() {
		return estado;
	}

	public void setEstado(String[][] estado) {
		this.estado = estado;
	}

	public Nodo getPadre() {
		return padre;
	}

	public void setPadre(Nodo padre) {
		this.padre = padre;
	}

	public boolean isMax() {
		return isMax;
	}

	public void setMax(boolean isMax) {
		this.isMax = isMax;
	}

	public String getMaxSim() {
		return maxSim;
	}

	public void setMaxSim(String maxSim) {
		this.maxSim = maxSim;
	}

	public String getMinSim() {
		return minSim;
	}

	public void setMinSim(String minSim) {
		this.minSim = minSim;
	}

	public Juego getJuego() {
		return juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}

	public int getProfunidad() {
		return profunidad;
	}

	public void setProfunidad(int profunidad) {
		this.profunidad = profunidad;
	}

	public String getSim() {
		return sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}

	public ArrayList<Nodo> getHijos() {
		return hijos;
	}

	public void setHijos(ArrayList<Nodo> hijos) {
		this.hijos = hijos;
	}
	
	public static String[][] cloneArray(String[][] src) {
	    int length = src.length;
	    String[][] target = new String[length][src[0].length];
	    for (int i = 0; i < length; i++) {
	        System.arraycopy(src[i], 0, target[i], 0, src[i].length);
	    }
	    return target;
	}
	
	
	
	
}
