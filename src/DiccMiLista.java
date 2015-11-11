//DNI 15418068 GONZALEZ COBO, ANGEL

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class DiccMiLista implements Diccionario {

    private class NodoL {
        private Palabra2 pal;
        private NodoL next;

        public NodoL() {
            pal = null;
            next = null;
        }

        public NodoL(Palabra2 p) {
            this.pal = p;
            next = null;
        }

        public void cambiaNext(NodoL n) {
            next = n;
        }

        public void setPalabra2(Palabra2 p) {
            pal = p;
        }

        public NodoL getNext() {
            return next;
        }

        public Palabra2 getPalabra2() {
            return pal;
        }
    }

    private int nlenguas;
    private Vector<Character> lenguas;
    private NodoL dicc;

    public DiccMiLista() {
        nlenguas = -1;
        lenguas = new Vector<Character>();
        dicc = new NodoL();
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
     * Se comprueba que la informacion pasada por parametros tenga valor.
     * Despues se comprueba que el diccionario tenga elementos. Si no tiene se
     * inserta y termina. Si ya tiene elementos comprueba los elementos para que
     * no sea exactamente igual la palabra que hay con la que esta pasando. En
     * caso que se encuentre en el diccionario se llamara la funcion que
     * modifica todas las traducciones. En caso que no este se comprueba que la
     * palabra tenga un valor inferior en el alfabeto. Si es inferior se
     * agregara antes de la siguiente palabra. Si es mayor se insertara despues.
     */
    @Override
    public boolean inserta(Palabra2 p) {

        if (p != null && !p.getOrigen().equalsIgnoreCase("")) {

            // TIENEN QUE SER LAS MISMAS LENGUAS EN EL MISMO ORDEN
            for (int i = 0; i < lenguas.size(); i++) {
                if (p.getLenguas()[i] != lenguas.get(i)) {
                    return false;
                }
            }

            NodoL next = dicc;
            NodoL actual = null;

            // PRIMER ELEMENTO
            if (next.getPalabra2() == null) {
                next.setPalabra2(p);
                return true;
            }

            while (next != null) {
                if (next.getPalabra2() != null) {

                    // COMPROBAR SI SON EXACTAMENTE IGUALES
                    if (p.toString().equalsIgnoreCase(next.getPalabra2().getOrigen()))
                        return false;

                    // COMPROBAR SI ESTA EN EL DICCIONARIO
                    if (next.getPalabra2().getOrigen().equalsIgnoreCase(p.getOrigen())) {
                        Palabra2 palabra = next.getPalabra2();
                        boolean action = palabra.modTrad(p);
                        return action;
                    } else {
                        if (next.getPalabra2().getOrigen().compareToIgnoreCase(p.getOrigen()) > 0) {
                            // SE INSERTA
                            NodoL aux = new NodoL(p);
                            if (actual == null) {
                                dicc = aux;
                            } else {
                                actual.cambiaNext(aux);
                            }
                            aux.cambiaNext(next);
                            return true;
                        } else {
                            actual = next;
                            next = next.getNext();

                            // ULTIMA POSICION
                            if (next == null) {
                                NodoL aux = new NodoL(p);
                                actual.cambiaNext(aux);
                            }
                        }

                    }
                }
            }
        }

        return false;

    }

    /**
     * Se guarda la informacion del primer nodo (A) por un lado, y uno vacio por
     * otro (B). Servira para ir comprobando el nodo A con la palabra a borrar.
     * El nodo B ira un elemento por detras. De manera que una vez que A se
     * encuentre con el que tiene que borrar, B apuntara al siguiente de A,
     * haciendo que el elemento a borrar sea desechado.
     */
    @Override
    public boolean borra(String s) {
        if (s != null) {
            NodoL next = dicc;
            NodoL actual = null;

            while (next != null) {
                int compare = next.getPalabra2().getOrigen().compareTo(s);
                if (compare == 0) {
                    // NEXT ES LA PALABRA A BORRAR
                    actual.cambiaNext(next.getNext());
                } else {
                    // SE HA PASADO. FIN.
                    return false;
                }

                // NEXT ES ALFABETICAMENTE MENOR Y HAY QUE SEGUIR BUSCANDO
                actual = next;
                next = next.getNext();
            }
        }
        return false;
    }

    /**
     * Busqueda secuencial del arbol completo. Se va comprobando cada elemento,
     * y si no es el siguiente, se adelanta y se aumenta el contador de
     * comparaciones. Se utiliza el valor sintactico de este, de manera que si
     * el valor que se busca, esta despues de la palabra que se ha comprobado
     * significa que no va a estar y termina la busqueda.
     */
    @Override
    public int busca(String s) {
        int number = 0;

        if (s != null) {
            NodoL next = dicc;

            while (next != null) {

                int compare = next.getPalabra2().getOrigen().compareTo(s);
                if (compare < 0) {
                    next = next.getNext();
                    number++;
                } else {
                    if (compare == 0) {
                        return number;
                    } else {
                        return number * (-1);
                    }
                }
            }
        }
        return number * (-1);
    }

    @Override
    public String traduce(String s, char l) {
        if (s != null) {
            NodoL next = dicc;

            while (next != null) {
                if (next.getPalabra2().getOrigen().equalsIgnoreCase(s)) {
                    return next.getPalabra2().getTraduccion(l);
                } else {
                    if (next.getPalabra2().getOrigen().compareToIgnoreCase(s) > 0) {
                        return null;
                    } else {
                        next = next.getNext();
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void visualiza() {
        NodoL next = dicc;

        while (next != null && next.getPalabra2() != null) {
            next.getPalabra2().escribeInfo();
            next = next.getNext();
        }
    }

    @Override
    public void visualiza(int j) {
        if (j >= nElementos()) {
            visualiza();
        } else {
            NodoL next = dicc;

            for (int i = 0; i < j; i++) {
                next.getPalabra2().escribeInfo();
                next = next.getNext();
            }
        }
    }

    @Override
    public void visualiza(int j, char l) {
        if (j >= nElementos()) {
            visualiza();
        } else {
            NodoL next = dicc;

            for (int i = 0; i < j; i++) {
                next.getPalabra2().escribeInfo(l);
                next = next.getNext();
            }
        }
    }

    /**
     * Metodo para obtener el numero de elementos que tiene el diccionario. Util
     * para comprobaciones.
     * 
     * @return int con el numero de elementos que tiene el arbol.
     */
    private int nElementos() {
        NodoL next = dicc;
        int number = 0;

        while (next != null) {
            number++;
            next = next.getNext();
        }

        return number;
    }
}
