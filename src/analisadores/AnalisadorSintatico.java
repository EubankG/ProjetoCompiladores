
package analisadores;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enumerator.TipoToken;
import principal.Token;

public class AnalisadorSintatico {

	private static final Map<String, Integer> variableTable = new HashMap<>();

    private int currentTokenIndex = 0;

    private List<Token> tokens;


    public AnalisadorSintatico(List<Token> tokens) {

        this.tokens = tokens;

    }
    
    public void parse() {

        // Lê o token de início e a expressão principal

        getNextToken();

        expressao();

    }


    private void expressao() {

        Term();

        ExpressionPrime();

    }


    private void ExpressionPrime() {

        if (isCurrentTokenType(TipoToken.SOMA)) {

            getNextToken();

            Term();

            ExpressionPrime();

        }

    }

    private void Term() {

        String variableName = (String) getCurrentToken().getValor();

        if (!variableTable.containsKey(variableName)) {

            variableTable.put(variableName, 0);

        }

        getNextToken();

        TermPrime();

    }
    
    private void TermPrime() {

        if (isCurrentTokenType(TipoToken.ASTERISCO)) {

            getNextToken();

            String variableName = (String) getCurrentToken().getValor();

            if (!variableTable.containsKey(variableName)) {

                variableTable.put(variableName, 0);

            }

            getNextToken();

            TermPrime();

        }

    }

    private boolean isCurrentTokenType(TipoToken type) {

        return getCurrentToken() != null && getCurrentToken().getTipo() == type;

    }


    private Token getCurrentToken() {

        if (currentTokenIndex >= tokens.size()) {

            return null;

        }

        return tokens.get(currentTokenIndex);

    }


    private Token getNextToken() {

        if (currentTokenIndex >= tokens.size()) {

            return null;

        }

        return tokens.get(currentTokenIndex++);

    }
	
}