package Enums

enum EnumTiposToken {
	OPR_LOG('operadorLogico', /(and)|(or)|(not)/),
	IF('if'),
	ELSE('else'),
	WHILE('while'),
	ATRIB('atribuicao', /=/),
	NUM('numero', /-?\d+(\.\d+)?/),
	OPR_AR('operadorAritmetico', /(\+)|(-)|(\/)|(\*)|(^)|(%)]/),
	OPR_REL('operadorRelacional', /(>=)|(<=)|(==)|(!=)|(>)|(<)/),
	DELIM('delimitador', /[\(\),;]/),
	ID('id', /(?!")[A-z][\w\d]*(?!"| |\w)/)
	
	String tipo
	String regex
	
	EnumTiposToken(String tipo, String regex) {
		this.tipo = tipo
		this.regex = regex
	}
	
	EnumTiposToken(String tipo) {
		this.tipo = tipo
		this.regex = tipo
	}
	
	static EnumTiposToken obterTipo(String valor) {
		values().find { valor.matches(it.regex) }
	}
	
}