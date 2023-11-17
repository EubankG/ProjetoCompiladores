package analisadores;
//import java.util.List;
//
//import enumerator.TipoToken;
//import principal.Token;
//
//public class AnalisadorSemantico {
//
//	private List<Token> tokens;
//
//    private int indexAtual = 0;
//
//    private Token tokenAtual;
//    
//    public AnalisadorSemantico(List<Token> tokens) {
//
//        this.tokens = tokens;
//
//        tokenAtual = tokens.get(indexAtual);
//
//    }
//    
//    public double analizar() {
//
//        return converterExpressao();
//
//    }
//
//    private double converterExpressao() {
//
//        double result = parser();
//
//        while (tokenAtual.getTipo() == TipoToken.SOMA) {
//
//            proximoToken();
//
//            result += parser();
//
//        }
//
//        return result;
//
//    }
//
//
//    private double parser() {
//
//        double result = parserFactor();
//
//        while (tokenAtual.getTipo() == TipoToken.ASTERISCO) {
//
//            proximoToken();
//
//            result *= parserFactor();
//
//        }
//
//        return result;
//
//    }
//
//
//    private double parserFactor() {
//
//        double result;
//
//        if (tokenAtual.getTipo() == TipoToken.INTEIRO || tokenAtual.getTipo() == TipoToken.REAL) {
//
//            result = Double.parseDouble(tokenAtual.getValor());
//
//            proximoToken();
//
//        } else if (tokenAtual.getTipo() == TipoToken.CHAVE) {
//
//            throw new RuntimeException("Variáveis não são suportadas.");
//
//        } else if (tokenAtual.getTipo() == TipoToken.ABRIR_PAREN) {
//
//            proximoToken();
//
//            result = converterExpressao();
//
//            if (tokenAtual.getTipo() != TipoToken.FECHAR_PAREN) {
//
//                throw new RuntimeException("Expectado ')', encontrado: " + tokenAtual);
//
//            }
//
//            proximoToken();
//
//        } else {
//
//            throw new RuntimeException("Caractere inválido: " + tokenAtual);
//
//        }
//
//        return result;
//
//    }
//
//
//    private void proximoToken() {
//
//        indexAtual++;
//
//        if (indexAtual < tokens.size()) {
//
//            tokenAtual = tokens.get(indexAtual);
//
//        } else {
//
//            tokenAtual = null;
//
//        }
//
//    }
//    
//	
//}

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enumerator.TipoToken;
import principal.Token;

public class AnalisadorSemantico {

    private List<Token> tokens;

    private int indexAtual = 0;

    private Token tokenAtual;

    private Map<String, Double> tabelaDeSimbolos = new HashMap<>();

    public AnalisadorSemantico(List<Token> tokens) {

        this.tokens = tokens;

        tokenAtual = tokens.get(indexAtual);

    }

    public double analizar() {

        return converterExpressao();

    }

    private double converterExpressao() {

        double result = parser();

        while (tokenAtual.getTipo() == TipoToken.OPERADOR) {

            if (tokenAtual.getValor().equals("+")) {

                proximoToken();

                result += parser();

            } else if (tokenAtual.getValor().equals("-")) {

                proximoToken();

                result -= parser();

            } else {

                throw new RuntimeException("Operador desconhecido: " + tokenAtual);

            }

        }

        return result;

    }

    private double parser() {

        double result = parserFactor();

        while (tokenAtual.getTipo() == TipoToken.OPERADOR) {

            if (tokenAtual.getValor().equals("*")) {

                proximoToken();

                result *= parserFactor();

            } else if (tokenAtual.getValor().equals("/")) {

                proximoToken();

                result /= parserFactor();

            } else {

                throw new RuntimeException("Operador desconhecido: " + tokenAtual);

            }

        }

        return result;

    }

    private double parserFactor() {

        double result = 0.0d;
        TipoToken tipo = null;
        boolean isPrimeiro = true;
        
        for(Token token : tokens) {
        	 if ((token.getTipo() == TipoToken.INTEIRO || token.getTipo() == TipoToken.REAL) ||
        		 (token.getTipo() == TipoToken.SOMA || token.getTipo() == TipoToken.SUBTRACAO || 
        		  token.getTipo() == TipoToken.ASTERISCO || token.getTipo() == TipoToken.DIVISAO) ) {

        		 if(token.getTipo() == TipoToken.INTEIRO || token.getTipo() == TipoToken.REAL) {
        			 
        			 if(isPrimeiro) {
        				 result = Double.parseDouble(token.getValor());
        				 isPrimeiro = false;
        			 }
        			 
        			 if(tipo == TipoToken.SOMA) {
        				 result += Double.parseDouble(token.getValor());
        			 } else if(tipo == TipoToken.SUBTRACAO) {
        				 result -= Double.parseDouble(token.getValor());
        			 } else if(tipo == TipoToken.ASTERISCO) {
        				 result *= Double.parseDouble(token.getValor());
        			 } else if(tipo == TipoToken.DIVISAO) {
        				 result /= Double.parseDouble(token.getValor());
        			 }
        			 
        		 }
        		 
        		 switch (token.getTipo()) {
                 case SOMA:
                	 tipo = token.getTipo();
                	 break;
                 case SUBTRACAO:
                	 tipo = token.getTipo();
                	 break;
                 case ASTERISCO:
                	 tipo = token.getTipo();
                 case DIVISAO:
                	 tipo = token.getTipo();
				default:
					break;
        		 }
        		 
             } else if (token.getTipo() == TipoToken.ABRIR_PAREN) {


                 result = converterExpressao();

                 if (token.getTipo() != TipoToken.FECHAR_PAREN) {

                     throw new RuntimeException("Expectado ')', encontrado: " + tokenAtual);

                 }


             } /*else if {

                 throw new RuntimeException("Caractere inválido: " + tokenAtual);

             }*/
        }

        return result;

    }

    private void proximoToken() {

        indexAtual++;

        if (indexAtual < tokens.size()) {

            tokenAtual = tokens.get(indexAtual);

        } else {

            tokenAtual = null;

        }

    }

}