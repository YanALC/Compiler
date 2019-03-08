package DTOs

class Variavel {
	String id
	String valor = '0'
	
	Variavel(String id) {
		this.id = id
	}
	
	Variavel(String id, String valor) {
		this.id = id
		this.valor = valor
	}
	
	@Override
	String toString() {
		this.id + ' = ' + this.valor
	}
}
