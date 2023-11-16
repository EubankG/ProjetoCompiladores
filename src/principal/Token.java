package principal;

import enumerator.TipoToken;

public class Token {

	 private final TipoToken tipo;
	    private final String valor;

	    public Token(TipoToken type, String value) {
	        this.tipo = type;
	        this.valor = value;
	    }

	    public TipoToken getTipo() {
	        return tipo;
	    }

	    public String getValor() {
	        return valor;
	    }

	    @Override
	    public String toString() {
	        return "Token{" +
	                "tipo=" + tipo +
	                ", valor='" + valor + '\'' +
	                '}';
	    }
	
}
