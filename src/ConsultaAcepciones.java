//DNI 15418068 GONZALEZ COBO, ANGEL

public class ConsultaAcepciones {
    public static void main(String[] args) {
        if (args.length == 2) {
            ListaBilingue lb = new ListaBilingue();
            lb.leeDiccionario(args[0]);
            System.out.println("SALIDA");
            switch (args[1].charAt(0)) {
            case 'O':
                lb.visualizaO();
                break;
            case 'D':
                lb.visualizaD();
                break;
            }
        }
    }
}
