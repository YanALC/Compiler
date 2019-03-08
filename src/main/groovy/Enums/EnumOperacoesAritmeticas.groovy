package Enums

enum EnumOperacoesAritmeticas {
	SOMA('+'),
	SUB('-'),
	DIV('/'),
	MUL('*'),
	RESTO('%'),
	POT('^')
	
	String op
	
	EnumOperacoesAritmeticas(String op) {
		this.op = op
	}
	
	String toString() {
		return op
	}
	
	static EnumOperacoesAritmeticas obterOperacaoAritmetica(String op) {
		return values().find { op == it.op }
	}
}