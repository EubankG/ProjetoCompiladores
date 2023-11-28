
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

        proximoToken();

        expressao();

    }


    private void expressao() {

        verificar();

        ExpressionPrime();

    }


    private void ExpressionPrime() {

        if (tokenAtual(TipoToken.SOMA)) {

            proximoToken();

            verificar();

            ExpressionPrime();

        }

    }

    private void verificar() {

        String variableName = (String) getCurrentToken().getValor();

        if (!variableTable.containsKey(variableName)) {

            variableTable.put(variableName, 0);

        }

        proximoToken();

        TermPrime();

    }
    
    private void TermPrime() {

        if (tokenAtual(TipoToken.ASTERISCO)) {

            proximoToken();

            String variableName = (String) getCurrentToken().getValor();

            if (!variableTable.containsKey(variableName)) {

                variableTable.put(variableName, 0);

            }

            proximoToken();

            TermPrime();

        }

    }

    private boolean tokenAtual(TipoToken type) {

        return getCurrentToken() != null && getCurrentToken().getTipo() == type;

    }


    private Token getCurrentToken() {

        if (currentTokenIndex >= tokens.size()) {

            return null;

        }

        return tokens.get(currentTokenIndex);

    }


    private Token proximoToken() {

        if (currentTokenIndex >= tokens.size()) {

            return null;

        }

        return tokens.get(currentTokenIndex++);

    }
	
}