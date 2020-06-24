package Triki;


public class Juego {

	private JugadorH h;
	private JugadorMinMax mM;
	private boolean terminal;
	private boolean turno;
	private  String[][] estado = new String[3][3];
	
	
	public static void main(String[]args) {
		JugadorH jH = new JugadorH("X");
		JugadorMinMax jM= new JugadorMinMax("O");
		Juego j1 = new Juego(jH,jM);
		j1.empezarJuego();		
	}
	
	
	public Juego(JugadorH h,JugadorMinMax mM) {
		this.h=h;
		this.mM=mM;
		for (int i = 0;i<estado.length;i++) {
			for(int j =0;j<estado[i].length;j++){
				estado[i][j]=null;

			}

		}

	}

	public void empezarJuego() {
		 terminal = false;
		 turno = true;
		 Test t = new Test();

		while(!terminal) {
				if(turno) {
					estado=h.jugarTurno(this);
				}
				else {
					estado=mM.jugarTurno(this);
				}
				print_estado(estado);
				t= testTerminal(estado);
				terminal=t.isB();
				turno=!turno;
				
				

		}
		if(t.getS()==null) {
			System.out.println("Empate");
		}
		else {
			String aux=(t.getS().compareTo("O")==0)?"Skynet":"Humano";
			System.out.println("Ganador: " + aux);
		}


	}

	public Test testTerminal(String[][] estado) {// verifica si el jugador ha ganado
		Test t = new Test();
		for (int i = 0;i<3;i++) {

			if (estado[i][0]==estado[i][1] && estado[i][1]==estado[i][2] && estado[i][0]!=null) {
				t.setS(estado[i][0]);
				t.setB(true);
				return t;
			}
			if (estado[0][i]==estado[1][i] && estado[1][i]==estado[2][i] && estado[0][i]!=null) {
				t.setS(estado[0][i]);
				t.setB(true);
				return t;
			}
		}	

		if (estado[1][1] != null && (( estado[0][0] == estado[1][1] && estado[1][1]== estado[2][2]) || (estado[0][2] == estado[1][1] && estado[1][1] == estado[2][0]))){

			t.setS(estado[1][1]);
			t.setB(true);
			return t;

		}

		for (int a = 0;a<3;a++) {
			for (int j = 0; j<3; j++) {
				if (estado[a][j] == null){
					t.setS(null);
					t.setB(false);
					return t;
				}
			}
		}

		t.setS(null);
		t.setB(true);
		return t;
	}

	public void print_estado(String[][] estado) {
		for (int i = 0;i<3;i++) {
			String linea = "";
			for (int j = 0; j<3;j++) {
				if (estado[i][j] != null) {
					linea = linea + estado[i][j] + " ";
				}
				else {
					linea = linea + "- ";
				}
				

			}
			
			System.out.println(linea);
		}
		System.out.println("*****");
	}

	public JugadorH getH() {
		return h;
	}

	public void setH(JugadorH h) {
		this.h = h;
	}

	public JugadorMinMax getmM() {
		return mM;
	}

	public void setmM(JugadorMinMax mM) {
		this.mM = mM;
	}

	public String[][] getEstado() {
		return estado;
	}

	public void setEstado(String[][] estado) {
		this.estado = estado;
	}
	
	public boolean isTerminal() {
		return terminal;
	}

	public void setTerminal(boolean terminal) {
		this.terminal = terminal;
	}

	public boolean isTurno() {
		return turno;
	}

	public void setTurno(boolean turno) {
		this.turno = turno;
	}



}
