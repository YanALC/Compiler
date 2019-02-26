import Enums.EnumTiposToken

class Lexer {
	
	static Set<Token> tokens = []
	static Set<Variavel> variaveis = []
	
	static void main(String[] args) {
		try {
			print("Caminho do arquivo: ")
			String caminhoArquivo = System.in.newReader().readLine()
			File arquivo = new File(caminhoArquivo)
			if (!arquivo.exists()) {
				throw new FileNotFoundException()
			}
			String codigoFonte = arquivo.text
			analisar(codigoFonte)
			println("Tokens: " + tokens)
			println("Variáveis: " + variaveis)
		} catch (Exception ex) {
			System.out.println(ex)
		}
	}
	
	static void analisar(String codigoFonte) {
		EnumTiposToken.values().each { EnumTiposToken enumTiposToken ->
			tokens.addAll(codigoFonte.findAll(enumTiposToken.regex).toSet().findResults { String valor ->
				valor && !tokens.find { it.valor == valor } ? new Token(enumTiposToken, valor) : null
			})
		}
		variaveis.addAll(tokens.findResults { return it.tipo == EnumTiposToken.ID ? new Variavel(it.valor) : null })
		tokens = tokens.sort { it.tipo.tipo }
		variaveis = variaveis.sort { it.id }
	}
	
}
