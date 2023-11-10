import java.io.IOException;

public class Teste {

	public static void main(String[] args) {
		LerArquivo la = new LerArquivo();

		String path = "C:/dev/ts.txt";

		try {
			la.leitorDeArquivo(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
