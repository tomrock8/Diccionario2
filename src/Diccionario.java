//DNI 15418068 GONZALEZ COBO, ANGEL

import java.util.Vector;

public interface Diccionario {

	/**
	 * Lee un archivo de diccionario y lo inserta al diccionario local.
	 * 
	 * @param f
	 *            String con el archivo a leer.
	 */
	public void leeDiccionario(String f);

	/**
	 * Inserta una nueva palabra en el diccionario. Si el diccionario no traduce
	 * a las mismas lenguas y en el mismo orden que el diccionario, la palabra
	 * no se inserta. Si ya existe esa palabra se inserta la traduccion a la
	 * lengua correspondiente si no existia ya. En caso que existiese, se
	 * sustituira la traduccion antigua por la nueva.
	 * 
	 * @param p
	 *            Palabra a insertar en el diccionario.
	 * @return true si se ha insertado la palabra o la traduccion.
	 */
	public boolean inserta(Palabra2 p);

	/**
	 * Borra la palabra cuyo origen coincida con el texto.
	 * 
	 * @param s
	 *            Origen de la palabra a borrar.
	 * @return true en caso que borre la palabra.
	 */
	public boolean borra(String s);

	/**
	 * Busca entre las cadenas de origen de cada palabra del diccionario.
	 * 
	 * @param s
	 *            String con la cadena de origen de la palabra a buscar.
	 * @return int con el numero de comparaciones que ha realizado al
	 *         encontrarla. Si no existe la palabra el numero de comparaciones
	 *         sera negativo.
	 */
	public int busca(String s);

	/**
	 * Busca entre las cadenas de origen la que coincida con el texto que se
	 * pasa. Devuelve la traduccion de la lengua que se pasa.
	 * 
	 * @param s
	 *            Texto con la palabra de origen a traducir.
	 * @param l
	 *            Letra del idioma a traducir.
	 * @return String con la cadena de la traduccion. En caso que no se
	 *         encuentre devolvera null
	 */
	public String traduce(String s, char l);

	/**
	 * Muestra todas las palabras del diccionario con todas las traducciones con
	 * el formato de la clase Palabra2.
	 */
	public void visualiza();

	/**
	 * Muestra solo las primeras j lineas del diccionario con el formato
	 * indicado en la clase Palabra2.
	 * 
	 * @param j
	 *            Numero de palabras a mostrar.
	 */
	public void visualiza(int j);

	/**
	 * Muestra solo las primeras j lineas del diccionario con la traduccion de
	 * la lengua l. Si no existe la lengua se mostrara solo el origen.
	 * 
	 * @param j
	 *            Numero de palabras a mostrar
	 * @param l
	 *            Lengua a mostrar
	 */
	public void visualiza(int j, char l);

	/**
	 * Conversor de la clase Vector<Character> a un array de Char
	 * 
	 * @param vector
	 *            Vector a convertir
	 * @return char[] resultado de convertir el vector
	 */
	public static char[] CVectorToCArray(Vector<Character> vector) {
		char[] array = new char[vector.size()];

		for (int i = 0; i < vector.size(); i++)
			array[i] = vector.get(i);

		return array;
	}

	/**
	 * Metodo para tratar la linea completa y formar la palabra. Sistema para
	 * repetir codigo innecesario en las distintas clases y accesible desde las
	 * implementaciones.
	 * 
	 * @param linea
	 *            String con la linea leida del archivo.
	 * @param lenguas
	 *            Lenguas de traduccion de la linea.
	 * @return Palabra resultante de la linea con las traducciones.
	 */
	public static Palabra2 formaPalabra(char[] lenguas, String linea) {
		Palabra2 p = null;
		String[] splt = linea.split("[ ]*\\*[ ]*");
		String origen = splt[0];

		p = new Palabra2(origen, lenguas);
		for (int i = 1; i < splt.length; i++) {
			if (splt[i].equalsIgnoreCase("") == false) {
				String palabra = splt[i];
				char l = lenguas[i - 1];

				p.setTrad(palabra, l);
			}
		}
		return p;
	}
}
