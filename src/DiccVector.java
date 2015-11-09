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
        String linea;
        // int npalabras; // numero de palabras introducidas en el vector.
        Palabra2 palabra;
        try {
            br = new BufferedReader(new FileReader(f));
            // numero de idiomas.
            linea = br.readLine();
            nlenguas = Integer.parseInt(linea);

            // vector de 3 posiciones
            lenguas = new Vector<Character>(nlenguas);

            // LINEA CON LAS INICIALES DE LAS TRADUCCIONES
            linea = br.readLine();
            splt = linea.split(" ");
            // - 0 -- 1 -- 2
            // ["E", "F", "P"]
            for (int i = 0; i < splt.length; i++) {
                lenguas.add(splt[i].charAt(0));
            }

            // LINEAS CON LAS PALABRAS
            linea = br.readLine();
            while (linea != null) {
                palabra = creaPalabra(linea, CVectorToCArray(lenguas));
                inserta(palabra);
                linea = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Conversor de la clase Vector<Character> a un array de Char
     * 
     * @param vector
     *            Vector a convertir
     * @return char[] resultado de convertir el vector
     */
    private char[] CVectorToCArray(Vector<Character> vector) {
        char[] array = new char[vector.size()];

        for (int i = 0; i < vector.size(); i++)
            array[i] = vector.get(i);

        return array;
    }

    /**
     * Metodo para tratar la linea completa y crear una palabra.
     * 
     * @param linea
     *            String con la linea leida del archivo.
     * @param lenguas
     *            Lenguas de traduccion de la linea.
     * @return Palabra resultante de la linea con las traducciones.
     */
    private Palabra2 creaPalabra(String linea, char[] lenguas) {
        Palabra2 p = null;
        String[] splt;

        splt = linea.split("[ ]*\\*[ ]*");
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

    @Override
    public boolean inserta(Palabra2 p) {
        boolean inserted, found;
        inserted = found = false;
        if (p != null) {
            if (p.getOrigen().equalsIgnoreCase("") == false) {
                for (int i = 0; i < dicc.size(); i++) {
                    if (dicc.get(i) != null) {
                        // COMPROBAR SI SON EXACTAMENTE IGUALES
                        if (p.toString().equals(dicc.get(i).toString()))
                            return false;

                        if (dicc.get(i).getOrigen().equalsIgnoreCase(p.getOrigen())) {
                            found = true;

                            dicc.get(i).modTrad(p);
                        }
                    }
                }
                // si la palabra no esta.
                if (found == false) {
                    // INSERTAMOS EN DICCORD
                    int last = 0; // para guardar la posicion donde he
                                  // insertado.
                    inserted = false;

                    if (dicc.size() != 0) {
                        for (int i = 0; i < dicc.size(); i++) {
                            if (!inserted) {
                                if (dicc.get(i) == null) {
                                    dicc.setElementAt(p, i); // lo inserto.
                                    last = i;
                                    inserted = true;
                                }
                            }
                        }

                        if (!inserted) {
                            dicc.add(p);
                            inserted = true;
                        }
                    } else {
                        if (!inserted) {
                            dicc.add(p);
                            inserted = true;
                        }
                    }

                    int k = last;
                    Palabra2 aux;
                    while (k > 0) {
                        if (dicc.get(k).getOrigen().compareToIgnoreCase(dicc.get(k - 1).getOrigen()) < 0) {
                            aux = dicc.get(k);

                            dicc.set(k - 1, dicc.get(k));
                            dicc.set(k - 1, aux);

                            k--;
                        }
                    }
                }
            }
        }
        return inserted;

    }

    @Override
    public boolean borra(String s) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int busca(String s) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String traduce(String s, char l) {
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub

    }

}
