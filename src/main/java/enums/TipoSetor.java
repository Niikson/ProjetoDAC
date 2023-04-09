package enums;

public enum TipoSetor {

	VERMELHO("Vermelho"), AZUL("Azul"), LARANJA("Laranja"), VERDE("Verde"), AMARELO("Amarelo"), BRANCO("Branco");
	
	private String tipoSetor;
	
	private TipoSetor(String tipoSetor) {
		this.tipoSetor = tipoSetor;
	}
	
	public String getTipoSetor() {
		return tipoSetor;
	}

}
