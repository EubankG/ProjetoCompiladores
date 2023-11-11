import java.io.IOException;

public class Teste {

	public static void main(String[] args) {
		LerArquivo la = new LerArquivo();

		String path = "/Users/menoci/dev/teste.txt";

		try {
			
			
			la.leitorDeArquivo(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
