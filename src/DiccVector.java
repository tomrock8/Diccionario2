//DNI 15418068 GONZALEZ COBO, ANGEL

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class DiccVector implements Diccionario {

	private int nlenguas;
	private Vector<Character> lenguas;
	private Vector<Palabra2> dicc;

	public DiccVector() {
		nlenguas = -1;
		lenguas = new Vector<Character>();
		dicc = new Vector<Palabra2>();
	}

	@Override
	public void leeDiccionario(String f) {
		BufferedReader br = null;
		String[] splt;

		Palabra2 palabra;
		try {
			br = new BufferedReader(new FileReader(f));
			String linea = br.readLine(); // 1º LINEA - NUMERO LENGUAS
			nlenguas = Integer.parseInt(linea);

			// 2º LINEA - INICIALES DE LAS TRADUCCIONES
			// - 0 -- 1 -- 2
			// ["E", "F", "P"]
			lenguas = new Vector<Character>(nlenguas);
			linea = br.readLine();
			splt = linea.split(" ");
			
			for (int i = 0; i < splt.length; i++) {
				lenguas.add(splt[i].charAt(0));
			}

			// LINEAS CON LAS PALABRAS
			linea = br.readLine();
			while (linea != null) {
				palabra = Diccionario.formaPalabra(
						Diccionario.CVectorToCArray(lenguas), linea);
				inserta(palabra);
				linea = br.readLine();
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public boolean inserta(Palabra2 p) {
		boolean inserted = false;
		if (p != null) {
			if (p.getOrigen().equalsIgnoreCase("") == false) {
				for (int i = 0; i < dicc.size(); i++) {
					if (dicc.get(i) != null) {
						// COMPROBAR SI SON EXACTAMENTE IGUALES
						if (p.toString().equals(dicc.get(i).toString()))
							return false;

						if (dicc.get(i).getOrigen()
								.equalsIgnoreCase(p.getOrigen())) {
							dicc.get(i).modTrad(p);
							return true;
						}
					}
				}

				// NO ESTA
				int posicion = 0;
				if (dicc.size() != 0) {
					for (int i = 0; i < dicc.size(); i++) {
						if (!inserted) {
							if (dicc.get(i) == null) {
								dicc.setElementAt(p, i); // lo inserto.
								posicion = i;
								inserted = true;
							}
						}
					}

					if (!inserted) {
						dicc.add(p);
						inserted = true;
					}
				} else {
					// PRIMERA POSICION
					if (!inserted) {
						dicc.add(p);
						inserted = true;
					}
				}

				int k = posicion;
				while (k > 0) {
					if (dicc.get(k).getOrigen()
							.compareToIgnoreCase(dicc.get(k - 1).getOrigen()) < 0) {
						Palabra2 aux = dicc.get(k);

						dicc.set(k - 1, dicc.get(k));
						dicc.set(k - 1, aux);

						k--;
					}
				}
			}
		}

		return inserted;
	}

	@Override
	public boolean borra(String s) {
		for (int i = 0; i < dicc.size(); i++) {
			if (s.equalsIgnoreCase(dicc.get(i).getOrigen())) {
				dicc.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public int busca(String s) {
		int comparaciones = 0;

		for (Palabra2 p : dicc) {
			comparaciones++;
			if (p.getOrigen().equals(s)) {
				return comparaciones;
			}
		}

		return comparaciones * (-1);
	}

	@Override
	public String traduce(String s, char l) {
		for (Palabra2 p : dicc) {
			if (p.getOrigen().equals(s)) {
				return p.getTraduccion(l);
			}
		}
		return null;
	}

	@Override
	public void visualiza() {
		for (Palabra2 p : dicc)
			p.escribeInfo();
	}

	@Override
	public void visualiza(int j) {
		if (j < dicc.size()) {
			for (int i = 0; i < j; i++) {
				dicc.get(i).escribeInfo();
			}
		} else {
			visualiza();
		}
	}

	@Override
	public void visualiza(int j, char l) {
		if (j > dicc.size())
			j = dicc.size();

		for (int i = 0; i < j; i++) {
			dicc.get(i).escribeInfo(l);
		}
	}

}
