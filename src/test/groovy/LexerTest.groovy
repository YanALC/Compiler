import Enums.EnumTiposToken
import spock.lang.Specification

class LexerTest extends Specification {
	
	void analisar() {
		setup:
		String texto = """x=3;
variavel="ok";
if(x>2)
	x=1.1;
else
	variavel="deu ruim";"""
		
		when:
		Lexer.analisar(texto)
		
		then:
		assert Lexer.tokens, [
				new Token(EnumTiposToken.IF, "if"),
				new Token(EnumTiposToken.ELSE, "else"),
				new Token(EnumTiposToken.ATRIB, "="),
				new Token(EnumTiposToken.NUM, "1.1"),
				new Token(EnumTiposToken.NUM, "2"),
				new Token(EnumTiposToken.NUM, "3"),
				new Token(EnumTiposToken.OPR_REL, ">"),
				new Token(EnumTiposToken.DELIM, "("),
				new Token(EnumTiposToken.DELIM, ")"),
				new Token(EnumTiposToken.DELIM, ";"),
				new Token(EnumTiposToken.ID, "x"),
				new Token(EnumTiposToken.ID, "variavel")
		]
		
		assert Lexer.variaveis, [
				new Variavel("x"),
				new Variavel("variavel")
		]
	}
}
