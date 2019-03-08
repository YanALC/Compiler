package Modulos

import DTOs.Token
import DTOs.Variavel
import Enums.EnumTiposToken
import jdk.nashorn.internal.runtime.regexp.joni.exception.SyntaxException

class Lexer {
	
	Set<Token> tokens = []
	Set<Variavel> variaveis = []
	
	void analiseLexica(String codigoFonte) {
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
