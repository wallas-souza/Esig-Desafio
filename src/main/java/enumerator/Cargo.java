package enumerator;

public enum Cargo {
	
	ESTAGIARIO(1,"Estagiario"),
	TECNICO(2,"Tecnico"),
	ANALISTA(3,"Analista"),
	COORDENADOR(4,"Coordenador"),
	GERENTE(5,"Gerente");
	
	private String description;
	private Integer cod;
	
	Cargo(int cod, String description){
		this.cod = cod;
		this.description = description;
	}
	
	public int getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}

}
