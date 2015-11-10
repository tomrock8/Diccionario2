import java.util.ArrayList;

public class Program {
	/**
	 * Crea una lista de objetos de tipo Palabra2, sin leerlos de fichero.
	 * 
	 * @return La lista creada, con un objeto en cada una de sus posiciones.
	 */
	public static ArrayList<Palabra2> creaLista() {
		ArrayList<Palabra2> lista = new ArrayList<Palabra2>();
		Palabra2 tmp = null;
		char[] idiomas = { 'F', 'E', 'P' };
		tmp = new Palabra2("lay", idiomas);
		tmp.setTrad("dejar", 'E');
		lista.add(tmp);
		tmp = new Palabra2("normal", idiomas);
		tmp.setTrad("normal", 'F');
		lista.add(tmp);
		tmp = new Palabra2("please", idiomas);
		tmp.setTrad("por favor", 'P');
		lista.add(tmp);
		tmp = new Palabra2("toy", idiomas);
		tmp.setTrad("jouet", 'F');
		lista.add(tmp);
		tmp = new Palabra2("wrong", idiomas);
		tmp.setTrad("errado", 'P');
		tmp.setTrad("equivocado", 'E');
		tmp.setTrad("faux", 'F');
		lista.add(tmp);
		char[] idiomas2 = { 'E', 'P', 'F' };
		tmp = new Palabra2("wrong", idiomas2);
		tmp.setTrad("desacertado", 'E');
		lista.add(tmp);
		return lista;
	}

	public static void main(String[] args) {
		Diccionario diccio = new DiccVector();
		if (args.length >= 1) {
			diccio.leeDiccionario(args[0]);
			diccio.visualiza(15);
			ArrayList<Palabra2> palabras = creaLista();
			Palabra2 actual = null;
			for (int i = 0; i < palabras.size(); i++) {
				actual = palabras.get(i);
				String culo = "Se inserta " + actual.getOrigen() + "? -> "
						+ diccio.inserta(actual);
				System.out.println(culo);
			}
			System.out
					.println("========== DICCIONARIO DESPUES DE INSERTAR ===============");
			diccio.visualiza(15);
		} else
			System.out.println("Forma uso: java p04 p04.dic");

	}
}
