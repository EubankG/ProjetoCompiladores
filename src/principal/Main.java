package principal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import analisadores.AnalisadorLexico;
import analisadores.AnalisadorSemantico;
import analisadores.AnalisadorSintatico;
import enumerator.TipoToken;

public class Main {
	
	public static void main(String[] args) {
		
		String input = lerArquivo();
		
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
        System.out.println(semantico.verificar());
	}

	private static String lerArquivo() {
		LerArquivo arquivo = new LerArquivo();
		//INSIRA O CAMINHO DO TXT COTENDO O CODIGO JAV0
		String path = "/Users/menoci/dev/teste.txt";
		
		String input = "";
		try {
			input = arquivo.leitorArquivo(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}

}