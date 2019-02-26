import Enums.EnumTiposToken
import jdk.nashorn.internal.runtime.regexp.joni.exception.SyntaxException

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
		try {
			EnumTiposToken.values().each { EnumTiposToken enumTiposToken ->
				tokens.addAll(codigoFonte.findAll(enumTiposToken.regex).toSet().findResults { String valor ->
					valor && !tokens.find { it.valor == valor } ? new Token(enumTiposToken, valor) : null
				})
			}
			
			String codigoNaoReconhecido = codigoFonte.unexpand()
			Lexer.tokens.valor.each {
				codigoNaoReconhecido = codigoNaoReconhecido.replace(it, "")
			}
			Integer linha = 0
			Map<Integer, String> padroesNaoReconhecidos = codigoNaoReconhecido.split(/\n/).toList()*.trim().collect {
				linha++
				return [linha, it]
			}.collectEntries()
			padroesNaoReconhecidos.removeAll { !it.value }
			if (padroesNaoReconhecidos) {
				throw new SyntaxException("Os seguintes padrões não são reconhecidos: " + padroesNaoReconhecidos)
			}
			
			variaveis.addAll(tokens.findResults { return it.tipo == EnumTiposToken.ID ? new Variavel(it.valor) : null })
			tokens = tokens.sort { it.tipo.tipo }
			variaveis = variaveis.sort { it.id }
		}
		catch (Exception ex) {
			throw ex
		}
	}
	
}
