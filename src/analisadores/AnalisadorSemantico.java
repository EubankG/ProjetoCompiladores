package analisadores;
import java.util.List;

import enumerator.TipoToken;
import principal.Token;

public class AnalisadorSemantico {

	private List<Token> tokens;

    private int currentIndex = 0;

    private Token currentToken;
    
    public AnalisadorSemantico(List<Token> tokens) {

        this.tokens = tokens;

        currentToken = tokens.get(currentIndex);

    }
    
    public double analyze() {

        return parseExpression();

    }


    private double parseExpression() {

        double result = parseTerm();

        while (currentToken.getTipo() == TipoToken.SOMA) {

            getNextToken();

            result += parseTerm();

        }

        return result;

    }


    private double parseTerm() {

        double result = parseFactor();

        while (currentToken.getTipo() == TipoToken.ASTERISCO) {

            getNextToken();

            result *= parseFactor();

        }

        return result;

    }


    private double parseFactor() {

        double result;

        if (currentToken.getTipo() == TipoToken.INTEIRO || currentToken.getTipo() == TipoToken.REAL) {

            result = Double.parseDouble(currentToken.getValor());

            getNextToken();

        } else if (currentToken.getTipo() == TipoToken.CHAVE) {

            throw new RuntimeException("Variáveis não são suportadas.");

        } else if (currentToken.getTipo() == TipoToken.ABRIR_PAREN) {

            getNextToken();

            result = parseExpression();

            if (currentToken.getTipo() != TipoToken.FECHAR_PAREN) {

                throw new RuntimeException("Expectado ')', encontrado: " + currentToken);

            }

            getNextToken();

        } else {

            throw new RuntimeException("Caractere inválido: " + currentToken);

        }

        return result;

    }


    private void getNextToken() {

        currentIndex++;

        if (currentIndex < tokens.size()) {

            currentToken = tokens.get(currentIndex);

        } else {

            currentToken = null;

        }

    }
    
	
}
