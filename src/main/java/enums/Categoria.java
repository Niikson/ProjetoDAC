package enums;

public enum Categoria {

	CABECA("Cabeça"), TRONCO("Tronco"), MEMBROSSUPERIORES("Membros Superiores"), MEMBROSINFERIORES("Membros Inferiores");
	
	private String categoria;
	
	private Categoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getCategoria() {
		return categoria;
	}

}
