package enums;

public enum TipoDeCompra {

	COMPRA("Compra"), RESERVA("Reserva");
	
	private String tipoDeCompra;
	
	private TipoDeCompra(String tipoDeCompra) {
		this.tipoDeCompra = tipoDeCompra;
	}
	
	public String getTipoDeCompra() {
		return tipoDeCompra;
	}

}
