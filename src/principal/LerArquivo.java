package principal;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LerArquivo {
	
	public String leitorDeArquivo(String path) throws IOException {
		
		String linha = "";
		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		
		linha = buffRead.readLine();
		
		buffRead.close();
		
		return linha;
	}

}
