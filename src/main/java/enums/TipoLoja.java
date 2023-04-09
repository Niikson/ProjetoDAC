package enums;

public enum TipoLoja {

	LOJA("Loja"), BOX("Box");
	
	private String tipoLoja;
	
	private TipoLoja(String tipoLoja) {
		this.tipoLoja = tipoLoja;
	}
	
	public String getTipoLoja() {
		return tipoLoja;
	}

}
