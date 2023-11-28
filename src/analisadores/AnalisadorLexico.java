
package analisadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.RuntimeErrorException;

import enumerator.TipoToken;
import principal.Token;

public class AnalisadorLexico {

    //REGEX ANTIGA
//    private static final Pattern PATTERN = Pattern.compile(
//            "\\s+|(?i)INTEIRO|REAL|(?<INTEIRO>[-+]?[0-9]+)|(?<REAL>[-+]?[0-9]*\\.[0-9]+)|(?<OPERADOR>[-+*/()])");

	 private static final Pattern PATTERN = Pattern.compile(
	            "(?<TIPO>INTEIRO|REAL)|(?<VARIAVEL>[a-zA-Z]+[\\d]*)|(?<IGUAL>[=])|(?<INTEIRO>[^ \\.a-zA-Z+~*;=/()-]+)|(?<REAL>[^ a-zA-Z()/*+~;=-]+)|(?<OPERADOR>[=/+*()-])|(?<FIM>[;$])");
	 
    private final String input;
    private final Matcher matcher;

    public AnalisadorLexico(String input) {
        this.input = input;
        this.matcher = PATTERN.matcher(input);
    }

    public Token nextToken() {

        isLetra();
        validarPontoVirgula();
        
        while (matcher.find()) {

        	if (matcher.group("TIPO") != null) {
        		return new Token(TipoToken.TIPO, matcher.group("TIPO"));

        	} else if (matcher.group("INTEIRO") != null) {
        		return new Token(TipoToken.INTEIRO, matcher.group("INTEIRO"));

        	} else if (matcher.group("REAL") != null) {
        		return new Token(TipoToken.REAL, matcher.group("REAL"));

        	} else if (matcher.group("IGUAL") != null) {
        		return new Token(TipoToken.IGUAL, matcher.group("IGUAL"));

        	} else if (matcher.group("VARIAVEL") != null) {
        		return new Token(TipoToken.VARIAVEL, matcher.group("VARIAVEL"));

        	} else if (matcher.group("OPERADOR") != null) {
        		String operador = matcher.group("OPERADOR");
        		return switch (operador) {
        		case "+" -> new Token(TipoToken.SOMA, operador);
        		case "-" -> new Token(TipoToken.SUBTRACAO, operador);
        		case "*" -> new Token(TipoToken.ASTERISCO, operador);
        		case "/" -> new Token(TipoToken.DIVISAO, operador);
        		case "(" -> new Token(TipoToken.ABRIR_PAREN, operador);
        		case ")" -> new Token(TipoToken.FECHAR_PAREN, operador);
        		default -> new Token(TipoToken.ERRO, operador);
        		};
        	} else if (matcher.group("FIM") != null) {
        		return new Token(TipoToken.FIM, ";");
        	}
        }

        return new Token(TipoToken.FIM, "");
    }

	private void validarPontoVirgula() {
		int tamanho = input.length();
        String finalPalavra = input.substring(tamanho - 1);
        if(!finalPalavra.equals(";")) {
        	throw new RuntimeException("Nao possui ponto virgula");
        }
	}

    private void isLetra() {

            int lugar = input.indexOf("=");

            String valores = input.substring(lugar);

            char[] c = valores.toCharArray();
            boolean isletra = false;

            for (int i = 0; i < c.length; i++) {
            	
                // verifica se o char não é um dígito
                if (Character.isLetter(c[i])) {
                    isletra = true;
                    break;
                }
            }

            if (isletra) {
                throw new RuntimeErrorException(null, "Caracter invalido");
            }
            
    }

}

