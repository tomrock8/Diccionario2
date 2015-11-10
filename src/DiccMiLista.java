//DNI 15418068 GONZALEZ COBO, ANGEL

public class DiccMiLista implements Diccionario{

    private class NodoL{
        private Palabra2 pal;
        private NodoL next;
        
        public NodoL(){
            pal = null;
            next = null;
        }
        
        public NodoL(Palabra2 p){
            this.pal = p;
            next = null;
        }
        
        public void cambiaNext(NodoL n){
            next = n;
        }
        
        public void setParabra2(Palabra2 p){
            pal = p;
        }
        
        public NodoL getNext(){
            return next;
        }
        
        public Palabra2 getPalabra2(){
            return pal;
        }
    }
    

	@Override
	public void leeDiccionario(String f) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean inserta(Palabra2 p) {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visualiza(int j) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visualiza(int j, char l) {
		// TODO Auto-generated method stub
		
	}
    
    //
    

}
