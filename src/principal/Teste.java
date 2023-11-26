package principal;
import java.util.ArrayList;
import java.util.List;

import analisadores.AnalisadorLexico;
import analisadores.AnalisadorSemantico;
import analisadores.AnalisadorSintatico;
import enumerator.TipoToken;

public class Teste {
	
	public static void main(String[] args) {
		String input = "INTEIRO teste = 2 + 22 * 2 + 2 - 1 ;";
        List<Token> tokens = new ArrayList<Token>();

        Token token = null;
        do {
            String[] palavras = input.split(" ");
            for(String palavra : palavras){

                AnalisadorLexico analisador = new AnalisadorLexico(palavra);

                token = analisador.nextToken();
                System.out.println(token);

                tokens.add(token);
            }
        } while (token.getTipo() != TipoToken.FIM);
		
       
        //VALIDOU OS TOKENS
        try {
            AnalisadorSintatico parse = new AnalisadorSintatico(tokens);
            parse.parse();
        }catch (Exception e){

        }
        
        //REALIZAR TAREFAS
        try {
            AnalisadorSemantico semantico = new AnalisadorSemantico(tokens);
            System.out.println(semantico.analizar());
        }catch (Exception e){

        }
	}

}