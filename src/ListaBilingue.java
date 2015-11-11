
//DNI 15418068 GONZALEZ COBO, ANGEL

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class ListaBilingue {

    private class NodoLD {
        private String origen, destino;
        private NodoLD nextOrigen, nextDestino;

        public NodoLD(String o, String d) {
            this.origen = o;
            this.destino = d;
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

        public void setDestino(String s) {
            destino = s;
        }

        public void setNextOrigen(NodoLD nOrigen) {
            nextOrigen = nOrigen;
        }

        public void setNextDestino(NodoLD nDestino) {
            nextDestino = nDestino;
        }
    }

    private NodoLD diccO, diccD;

    public ListaBilingue() {
        diccO = null;
        diccD = null;
    }

    public void leeDiccionario(String f) {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(f));
            String line = br.readLine();
            while (line != null) {
                String[] splt = line.split("[ ]*\\*[ ]*");
                String origen = splt[0];
                String destino = splt[1];

                inserta(origen, destino);

                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                br.close();
            } catch (IOException e) {
            }
        }
    }

    public boolean inserta(String o, String d) {
        NodoLD ndl = new NodoLD(o, d);
        boolean a, b;
        a = insertaOrigen(ndl);
        b = insertaDestino(ndl);

        return (a && b) ? true : false;
    }

    private boolean insertaOrigen(NodoLD ptN) {
        NodoLD next = diccO;
        NodoLD actual = null;

        while (next != null) {
            if (next.getOrigen().equalsIgnoreCase(ptN.getOrigen())) {
                if (actual == null) {
                    diccD = ptN;
                } else
                    actual.setNextOrigen(ptN);
            } else {
                if (next.getOrigen().compareToIgnoreCase(ptN.getOrigen()) <= 0) {
                    actual = next;
                    next = next.getNextOrigen();
                } else
                    return false;
            }
        }
        return false;
    }

    private boolean insertaDestino(NodoLD ptN) {
        NodoLD next = diccD;
        NodoLD actual = null;

        while (next != null) {
            if (next.getDestino().equalsIgnoreCase(ptN.getDestino())) {
                if (actual == null) {
                    diccD = ptN;
                } else
                    actual.setNextDestino(ptN);
            } else {
                if (next.getDestino().compareToIgnoreCase(ptN.getDestino()) <= 0) {
                    actual = next;
                    next = next.getNextDestino();
                } else
                    return false;
            }
        }
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
        NodoLD next = diccD;
        NodoLD actual;
        boolean action = false;

        while (next != null) {
            if (next.getNextOrigen() != null && next.getDestino().equalsIgnoreCase(next.getNextOrigen().getOrigen())) {

                action = true;
                System.out.print(next.getOrigen() + ":" + next.getDestino());
                actual = next.getNextOrigen();
                while (actual != null && actual.getOrigen().equalsIgnoreCase(next.getOrigen())) {
                    System.out.print("," + actual.getDestino());
                    actual = actual.getNextOrigen();
                }
                next = actual;
                System.out.println();
            } else {
                next = next.getNextOrigen();
            }
        }
        if (!action)
            System.out.println("No existe");
    }

    public void visualizaD() {
        NodoLD next = diccD;
        NodoLD actual;
        boolean action = false;

        while (next != null) {
            if (next.getNextDestino() != null
                    && next.getDestino().equalsIgnoreCase(next.getNextDestino().getDestino())) {

                action = true;
                System.out.print(next.getDestino() + ":" + next.getOrigen());
                actual = next.getNextDestino();
                while (actual != null && actual.getDestino().equalsIgnoreCase(next.getDestino())) {
                    System.out.print("," + actual.getOrigen());
                    actual = actual.getNextDestino();
                }
                next = actual;
                System.out.println();
            } else {
                next = next.getNextDestino();
            }
        }
        if (!action)
            System.out.println("No existe");
    }

    public Vector<String> getO(int i) {
        NodoLD next = diccO;
        int pos = 1;

        while (next != null) {
            if (pos == i) {
                Vector<String> aux = new Vector<String>();
                aux.add(next.getOrigen());
                aux.add(next.getDestino());

                return aux;
            } else {
                next = next.getNextOrigen();
                pos++;
            }
        }
        return null;
    }

    public Vector<String> getD(int i) {
        NodoLD next = diccD;
        int pos = 1;

        while (next != null) {
            if (pos == i) {
                Vector<String> aux = new Vector<String>();
                aux.add(next.getOrigen());
                aux.add(next.getDestino());

                return aux;
            } else {
                next = next.getNextDestino();
                pos++;
            }
        }
        return null;
    }
}