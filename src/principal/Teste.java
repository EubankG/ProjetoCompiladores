package principal;
import java.util.ArrayList;
import java.util.List;

import analisadores.Lexer;
import analisadores.Parser;
import analisadores.AnalisadorSemantico;
import enumerator.TipoToken;

public class Teste {
	
	public static void main(String[] args) {
		
//		String input = "INTEIRO x = 10; INTEIRO y = 20;";
		String input = "INTEIRO x = (10 + 5);";
        Lexer lexer = new Lexer(input);
        Token token;
        
//        Integer inteiro = 0;
//        Double real = 0.0d;

        List<Token> tokens = new ArrayList<Token>();
        
        do {
            token = lexer.nextToken();
            
//            if(token.getTipo() == TokenType.INTEIRO && !token.getValor().isEmpty()) {
//            	inteiro = inteiro + Integer.valueOf(token.getValor());
//            }
            
            System.out.println(token);
            
            tokens.add(token);
            
        } while (token.getTipo() != TipoToken.FIM);
        
        
        //VALIDOU OS TOKENS
        Parser parse = new Parser(tokens);
        parse.parse();
        
        AnalisadorSemantico semantico = new AnalisadorSemantico(tokens);
        System.out.println(semantico.analyze());
        
        
    
	}

}
