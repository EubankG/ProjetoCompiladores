package principal;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LerArquivo {
	
	public void leitorDeArquivo(String path) throws IOException {
		
		String linha = "";
		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		
		while(true) {
			if(linha != null) {
				String [] arrayPalavras = linha.split(" ");
				
				for(String arrayPalavra : arrayPalavras) {
					
					String[] palavra = arrayPalavra.split("");
					
					for(String letra : palavra) {
						analiseLexica(letra);
					}
					
					System.out.println(arrayPalavra);
				}
			} else {
				break;
			}
			linha = buffRead.readLine();
		}
		
		buffRead.close();
	}

	private void analiseLexica(String letra) {
		
//		System.out.println(Alfabeto.FUNCOES.toString());
//		
//		letra.contains(Alfabeto.FUNCOES.toString());
		
		
	}
	
}
