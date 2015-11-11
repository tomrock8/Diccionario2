public class Program {

	public static void main(String[] args) {
		Diccionario diccio = new DiccMiLista();
		if (args.length >= 1) {
			diccio.leeDiccionario(args[0]);
			diccio.visualiza();
		} else
			System.out.println("Forma uso: java p05 p05.dic");

	}

}
