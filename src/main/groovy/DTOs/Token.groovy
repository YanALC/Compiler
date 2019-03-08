package DTOs

import Enums.EnumTiposToken

class Token {
	EnumTiposToken tipo
	
	String valor = tipo.tipo
	
	Token(EnumTiposToken tipo, String valor) {
		this.tipo = tipo
		this.valor = valor
	}
	
	@Override
	String toString() {
		this.tipo.tipo + ': ' + valor
	}
}
