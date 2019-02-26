import Enums.EnumTiposToken
import jdk.nashorn.internal.runtime.regexp.joni.exception.SyntaxException
import spock.lang.Specification

class LexerTest extends Specification {
	
	void "analisar reconhece tudo"() {
		setup:
		String texto = """x=3;
variavel=2;
if(x>2)
	x=1.1;
else
	variavel=5;"""
		
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
	
	void "analisar não reconhece strings literais"() {
		setup:
		String texto = """x=3;
variavel="ok";
if(x>2)
	x=1.1;
else
	variavel=5;"""
		
		when:
		Lexer.analisar(texto)
		
		then:
		SyntaxException ex = thrown()
		assert ex.message, "Os seguintes padrões não são reconhecidos: " + [2: "\"ok\""]
	}
}
