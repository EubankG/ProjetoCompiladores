//package analisadores;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import enumerator.TipoToken;
//import principal.Token;
//
//public class AnalisadorSintatico {
//
//	private static final Map<String, Integer> variableTable = new HashMap<>();
//
//    private int currentTokenIndex = 0;
//
//    private List<Token> tokens;
//
//
//    public AnalisadorSintatico(List<Token> tokens) {
//
//        this.tokens = tokens;
//
//    }
//    
//    public void parse() {
//
//        // Lê o token de início e a expressão principal
//
//        getNextToken();
//
//        expressao();
//
//    }
//
//
//    private void expressao() {
//
//        Term();
//
//        ExpressionPrime();
//
//    }
//
//
//    private void ExpressionPrime() {
//
//        if (isCurrentTokenType(TipoToken.SOMA)) {
//
//            getNextToken();
//
//            Term();
//
//            ExpressionPrime();
//
//        }
//
//    }
//
//    private void Term() {
//
//        Factor();
//
//        TermPrime();
//
//    }
//    
//    private void TermPrime() {
//
//        if (isCurrentTokenType(TipoToken.ASTERISCO)) {
//
//            getNextToken();
//
//            Factor();
//
//            TermPrime();
//
//        }
//
//    }
//
//
//    private void Factor() {
//
//        if (isCurrentTokenType(TipoToken.ABRIR_PAREN)) {
//
//            getNextToken();
//
//            expressao();
//
//            if (!isCurrentTokenType(TipoToken.FECHAR_PAREN)) {
//
//                throw new RuntimeException("Esperado )");
//
//            }
//
//            getNextToken();
//
//        } else if (isCurrentTokenType(TipoToken.INTEIRO) || isCurrentTokenType(TipoToken.REAL)) {
//
//            // Pode ser necessário verificar a existência da variável e adicioná-la à tabela de variáveis, se necessário
//
//            getNextToken();
//
//        } else if (isCurrentTokenType(TipoToken.CHAVE)) {
//
//            String variableName = (String) getCurrentToken().getValor();
//
//            if (!variableTable.containsKey(variableName)) {
//
//                variableTable.put(variableName, 0);
//
//            }
//
//            getNextToken();
//
//        } else {
//
//            throw new RuntimeException("Erro no token atual");
//
//        }
//
//    }
//
//    private boolean isCurrentTokenType(TipoToken type) {
//
//        return getCurrentToken() != null && getCurrentToken().getTipo() == type;
//
//    }
//
//
//    private Token getCurrentToken() {
//
//        if (currentTokenIndex >= tokens.size()) {
//
//            return null;
//
//        }
//
//        return tokens.get(currentTokenIndex);
//
//    }
//
//
//    private Token getNextToken() {
//
//        if (currentTokenIndex >= tokens.size()) {
//
//            return null;
//
//        }
//
//        return tokens.get(currentTokenIndex++);
//
//    }
//	
//}



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