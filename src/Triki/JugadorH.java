package Triki;

import java.util.Arrays;
import java.util.Scanner;


public class JugadorH {
private String sim;

public String getSim() {
	return sim;
}

public void setSim(String sim) {
	this.sim = sim;
}

public JugadorH(String sim) {
	this.sim=sim;
}

public String[][] jugarTurno(Juego j){
	
	Scanner sc = new Scanner(System.in);
	System.out.println("Ingrese Fila");
	int x = sc.nextInt();
	System.out.println("Ingrese Col");
	int y = sc.nextInt();
	boolean posicion=true;
	while(posicion) {
		if (x<=2 && y<=2 && j.getEstado()[x][y]==null) {
			posicion=false;
		}
		else {
			System.out.println("Posicion invalida, por favor intente nuevamente");
			System.out.println("Ingrese Fila");
			 x = sc.nextInt();
			System.out.println("Ingrese Col");
			 y = sc.nextInt();
		}
	}
	
	if(j.getEstado()[x][y]==null) {
	String[][] copy = cloneArray(j.getEstado());
	copy[x][y]=sim;
	return copy;
	}
	else {
		System.out.println("Posicion Invalida");
		
		return null;
		
	}
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
