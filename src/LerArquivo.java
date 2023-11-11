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
						validarLetra(letra);
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

	private void validarLetra(String letra) {
		
		
		
		
	}
	
}
