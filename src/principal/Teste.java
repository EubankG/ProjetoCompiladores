package principal;
import java.util.ArrayList;
import java.util.List;

import analisadores.AnalisadorLexico;
import analisadores.AnalisadorSemantico;
import analisadores.AnalisadorSintatico;
import enumerator.TipoToken;

public class Teste {
	
	public static void main(String[] args) {
		
		String input = "INTEIRO x = 2 + 2 * 2;";
        AnalisadorLexico analisador = new AnalisadorLexico(input);
        List<Token> tokens = new ArrayList<Token>();

        Token token;
        do {
            token = analisador.nextToken();
            System.out.println(token);
            
            tokens.add(token);
        } while (token.getTipo() != TipoToken.FIM);
		
       
        //VALIDOU OS TOKENS
        AnalisadorSintatico parse = new AnalisadorSintatico(tokens);
        parse.parse();
        
        
        //REALIZAR TAREFAS
        AnalisadorSemantico semantico = new AnalisadorSemantico(tokens);
        System.out.println(semantico.analizar());
    
	}

}
