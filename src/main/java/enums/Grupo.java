package enums;

public enum Grupo {
	
	PROPRIETARIO("Proprietario"), CLIENTE("Cliente");
	
	private String nome;
	
	private Grupo(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

}
