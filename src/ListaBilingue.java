//DNI 15418068 GONZALEZ COBO, ANGEL

import java.util.Vector;

public class ListaBilingue {

	private class NodoLD {
		private String origen, destino;
		private NodoLD nextOrigen, nextDestino;

		public NodoLD(String origen, String destino) {
			this.origen = origen;
			this.destino = destino;
		}

		public String getOrigen() {
			return origen;
		}

		public String getDestino() {
			return destino;
		}

		public NodoLD getNextOrigen() {
			return nextOrigen;
		}

		public NodoLD getNextDestino() {
			return nextDestino;
		}

		public void setOrigen(String origen) {
			this.origen = origen;
		}

		public void setDestino(String destino) {
			this.destino = destino;
		}

		public void setNextOrigen(NodoLD nextOrigen) {
			this.nextOrigen = nextOrigen;
		}

		public void setNextDestino(NodoLD nextDestino) {
			this.nextDestino = nextDestino;
		}
	}

	private NodoLD diccO, diccD;
	
	public ListaBilingue() {
	}

	public void leeDiccionario(String f) {

	}

	public boolean inserta(String o, String d) {
		return false;
	}

	public boolean borraO(String s) {
		return false;
	}

	public boolean borraD(String s) {
		return false;
	}

	public String buscaO(String s) {
		return null;
	}

	public String buscaD(String s) {
		return null;
	}

	public int indiceO(String s) {
		return 0;
	}

	public int indiceD(String s) {
		return 0;
	}

	public void visualizaO() {

	}

	public void visualizaD() {

	}

	public Vector<String> getO(int i) {
		return null;
	}

	public Vector<String> getD(int i) {
		return null;
	}	
}