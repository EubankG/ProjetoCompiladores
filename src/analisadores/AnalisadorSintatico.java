
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

        proximoToken();

        expressao();

    }


    private void expressao() {

        verificar();

        verificarExpressao();

    }


    private void verificarExpressao() {

        if (tokenAtual(TipoToken.SOMA)) {

            proximoToken();

            verificar();

            verificarExpressao();

        }

    }

    private void verificar() {

        String variableName = (String) getCurrentToken().getValor();

        if (!variableTable.containsKey(variableName)) {

            variableTable.put(variableName, 0);

        }

        proximoToken();

        verificarParen();

    }
    
    private void verificarParen() {

        if (tokenAtual(TipoToken.ASTERISCO)) {

            proximoToken();

            String variableName = (String) getCurrentToken().getValor();

            if (!variableTable.containsKey(variableName)) {

                variableTable.put(variableName, 0);

            }

            proximoToken();

            verificarParen();

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