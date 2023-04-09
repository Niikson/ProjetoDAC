package enums;

public enum Genero {
	
	MASCULINO("Moda-Masculina"), FEMININO("Moda-Feminina"), INFANTIL("Moda-Infantil");
	
	private String genero;
	
	private Genero(String genero) {
		this.genero = genero;
	}
	
	public String getGenero() {
		return genero;
	}

}
