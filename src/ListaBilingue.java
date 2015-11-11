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
/*
	public void visualizarAlicia(char car) {
		System.out.println("SALIDA");
		if (car == 'O')
			visualizarAliciaO();
		else
			visualizarAliciaD();
	}

	public void visualizarAliciaO() {
		NodoLD ptL, ptL2;
		boolean algo = false;
		ptL = diccO;
		while (ptL != null) {
			if (ptL.getNextOrigen() != null) {
				if (ptL.getOrigen().equalsIgnoreCase(
						ptL.getNextOrigen().getOrigen())) {
					System.out.print(ptL.getOrigen() + ":" + ptL.getDestino());
					algo = true;
					ptL2 = ptL.getNextOrigen();
					while (ptL2 != null
							&& ptL2.getOrigen().equalsIgnoreCase(
									ptL.getOrigen())) {
						System.out.print("," + ptL2.getDestino());
						ptL2 = ptL2.getNextOrigen();
					}
					System.out.println();
					ptL = ptL2;
				} else {
					ptL = ptL.getNextOrigen();
				}
			} else {
				ptL = ptL.getNextOrigen();
			}
		}
		if (algo == false) {
			System.out.println("No existe");
		}
	}

	public void visualizarAliciaD() {
		NodoLD ptL, ptL2;
		boolean algo;
		algo = false;
		ptL = diccD;
		while (ptL != null) {
			if (ptL.getNextDestino() != null) {
				if (ptL.getDestino().equalsIgnoreCase(
						ptL.getNextDestino().getDestino())) {
					algo = true;
					System.out.print(ptL.getDestino() + ":" + ptL.getOrigen());
					ptL2 = ptL.getNextDestino();
					while (ptL2 != null
							&& ptL2.getDestino().equalsIgnoreCase(
									ptL.getDestino())) {
						System.out.print("," + ptL2.getOrigen());
						ptL2 = ptL2.getNextDestino();
					}
					System.out.println();
					ptL = ptL2;
				} else {
					ptL = ptL.getNextDestino();
				}
			} else {
				ptL = ptL.getNextDestino();
			}
		}
		if (algo == false) {
			System.out.println("No existe");
		}
	}

	public boolean insertaModificado(String o, String d) {
		boolean insertado = false;
		String traduccion;
		NodoLD ptn;

		traduccion = buscaO(o);
		if (traduccion != null && traduccion.equalsIgnoreCase(d)) {
			insertado = false;
		} else {
			ptn = new NodoLD(o, d);
			insertaO(ptn);
			insertaD(ptn);
		}
		return insertado;
	}

	public void leeDiccionarioAlicia(String f) {
		FileReader fr = null;
		BufferedReader br = null;
		String[] trozos;
		String linea;
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			linea = br.readLine();
			while (linea != null) {
				trozos = linea.split("[ ]*\\*[ ]*");
				insertaModificado(trozos[0], trozos[1]);
				linea = br.readLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null)
					fr.close();
				if (br != null)
					br.close();
			} catch (IOException e) {
			}
		}
	}

}
*/