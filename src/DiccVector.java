
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

    /*
     * Se crea un buffer para leer el archivo que se pasa por parametro. El
     * formato del archivo sera: Linea 1 el numero de idiomas de traduccion. La
     * linea 2 contiene las iniciales del idioma a traducir. Seguidamente las
     * lineas con las palabras. Se abre un buffer que coge la primera linea y la
     * trata como numero. La segunda linea la separa por espacios y guarda las
     * letras. Despues se forman las palabras a partir de la linea del archivo y
     * se inserta en el diccionario. Por ultimo se cierra el buffer.
     */
    @Override
    public void leeDiccionario(String f) {
        BufferedReader br = null;
        String[] splt;

        Palabra2 palabra;
        try {
            br = new BufferedReader(new FileReader(f));
            String linea = br.readLine(); // LINEA 1 - NUMERO LENGUAS
            nlenguas = Integer.parseInt(linea);

            // LINEA 2 - INICIALES DE LAS TRADUCCIONES
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
                palabra = DiccVector.formaPalabra(DiccVector.CVectorToCArray(lenguas), linea);
                inserta(palabra);
                linea = br.readLine();
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /*
     * Primero se comprueba si el parametro contiene valor. Despues se comprueba
     * que el origen de la palabra tenga valor. Se recorre el diccionario de
     * manera secuencial comparando los valores de las palabras de origen para
     * encontrar la misma palabra. Se comprueba que no sea exactamente la
     * palabra y la que ya esta almacenada. Si ya esta en el diccionario llama
     * una funcion donde se le pasan todas las traducciones y las modifica.
     * 
     * Si no esta en el diccionario se busca el primer hueco libre en caso que
     * el diccionario ya tenga elementos y se almacena. En caso que este vacio
     * se agrega en la primera posicion.
     * 
     * Posteriormente se ordena el diccionario con el metodo de la burbuja desde
     * el final del diccionario hasta el principio.
     */
    @Override
    public boolean inserta(Palabra2 p) {
        boolean action = false;

        if (p != null && !p.getOrigen().equalsIgnoreCase("")) {

            // TIENEN QUE SER LAS MISMAS LENGUAS EN EL MISMO ORDEN
            for (int i = 0; i < lenguas.size(); i++) {
                if (p.getLenguas()[i] != lenguas.get(i)) {
                    return false;
                }
            }

            if (p.getOrigen().equalsIgnoreCase("") == false) {
                for (int i = 0; i < dicc.size(); i++) {
                    if (dicc.get(i) != null && !action) {

                        // COMPROBAR SI SON EXACTAMENTE IGUALES
                        if (p.toString().equalsIgnoreCase(dicc.get(i).toString()))
                            return false;

                        // COMPROBAR SI EXISTE LA PALABRA
                        if (dicc.get(i).getOrigen().equalsIgnoreCase(p.getOrigen())) {
                            dicc.get(i).modTrad(p);
                            action = true;
                        }
                    }
                }

                if (!action) {
                    // NO EXISTE LA PALABRA
                    if (dicc.size() > 0) {
                        for (int i = 0; i < dicc.size(); i++) {
                            if (!action) {
                                if (dicc.get(i) == null) {
                                    dicc.set(i, p);
                                    action = true;
                                }
                            }
                        }

                        if (!action) {
                            dicc.add(p);
                            action = true;
                        }

                    } else {
                        // PRIMERA POSICION DEL DICCIONARIO
                        if (!action) {
                            dicc.add(p);
                            action = true;
                        }
                    }
                }

                // ORDENACION - BURBUJA
                for (int i = dicc.size() - 1; i > 0; i--) {
                    if (dicc.get(i).getOrigen().compareToIgnoreCase(dicc.get(i - 1).getOrigen()) < 0) {
                        Palabra2 aux = dicc.get(i);
                        dicc.set(i, dicc.get(i - 1));
                        dicc.set(i - 1, aux);
                    }
                }
            }
        }

        return action;
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

    /*
     * Busca secuencial de la palabra en el diccionario. Ya que el diccionario
     * esta ordenado comprueba que la siguiente palabra no sea mayor. Si es
     * menor sigue buscando. Si es mayor, significa que ya no va a estar y
     * devuelve el numero negativo de comparaciones.
     */
    @Override
    public int busca(String s) {
        int comparaciones = 0;

        for (Palabra2 p : dicc) {
            if (p != null) {
                int compare = p.getOrigen().compareTo(s);
                if (compare == 0) {
                    return comparaciones + 1;
                } else {
                    if (compare > 0) {
                        comparaciones++;
                    } else {
                        return comparaciones * (-1);
                    }
                }
            }
        }

        return comparaciones * (-1);
    }

    @Override
    public String traduce(String s, char l) {
        for (Palabra2 p : dicc) {
            if (p.getOrigen().equalsIgnoreCase(s)) {
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
