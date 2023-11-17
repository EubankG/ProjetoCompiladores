
package analisadores;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import enumerator.TipoToken;
import principal.Token;

public class AnalisadorLexico {

	private static final Pattern PATTERN = Pattern.compile(
			"\\s+|(?i)INTEIRO|REAL|(?<INTEIRO>[-+]?[0-9]+)|(?<REAL>[-+]?[0-9]*\\.[0-9]+)|(?<OPERADOR>[-+*/()])");

	
    private final String input;
    private final Matcher matcher;

    public AnalisadorLexico(String input) {
        this.input = input;
        this.matcher = PATTERN.matcher(input);
    }
    
    public Token nextToken() {

        while (matcher.find()) {

            if (matcher.group("INTEIRO") != null) {

                return new Token(TipoToken.INTEIRO, matcher.group("INTEIRO"));

            } else if (matcher.group("REAL") != null) {

                return new Token(TipoToken.REAL, matcher.group("REAL"));

            } else if (matcher.group("OPERADOR") != null) {

                String operador = matcher.group("OPERADOR");

                switch (operador) {

                    case "+":

                        return new Token(TipoToken.SOMA, operador);

                    case "-":

                        return new Token(TipoToken.SUBTRACAO, operador);

                    case "*":

                        return new Token(TipoToken.ASTERISCO, operador);

                    case "/":

                        return new Token(TipoToken.DIVISAO, operador);

                    case "(":
                        return new Token(TipoToken.ABRIR_PAREN, operador);

                    case ")":

                        return new Token(TipoToken.FECHAR_PAREN, operador);

                    default:

                        return new Token(TipoToken.ERRO, operador);

                }

            } else if (matcher.group("INTEIRO") != null) {

                return new Token(TipoToken.CHAVE, "INTEIRO");

            } else if (matcher.group("REAL") != null) {

                return new Token(TipoToken.CHAVE, "REAL");

            }

        }
        
        return new Token(TipoToken.FIM, "");
        
    }
    
}

