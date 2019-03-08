import Modulos.Lexer

class Compiler {
	static Lexer lexer
	
	static void main(String[] args) {
		try {
			print("Caminho do arquivo: ")
			String caminhoArquivo = System.in.newReader().readLine()
			File arquivo = new File(caminhoArquivo)
			if (!arquivo.exists()) {
				throw new FileNotFoundException()
			}
			String codigoFonte = arquivo.text
			lexer.analiseLexica(codigoFonte)
			println("Tokens: " + lexer.tokens)
			println("Variáveis: " + lexer.variaveis)
		} catch (Exception ex) {
			System.out.println(ex)
		}
	}
}
