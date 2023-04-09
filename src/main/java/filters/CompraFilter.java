package filters;

import enums.TipoDeCompra;

public class CompraFilter implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String data;
	private String nomeProduto;
	private TipoDeCompra tipoDeCompra;
	private String nomeLoja;
	private Integer id;

	public CompraFilter() {

	}

	public boolean isEmpty() {
		if (data != null)
			return false;
		else if (nomeProduto != null)
			return false;
		else if (tipoDeCompra != null)
			return false;
		else if (nomeLoja != null)
			return false;
		return true;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public TipoDeCompra getTipoDeCompra() {
		return tipoDeCompra;
	}

	public void setTipoDeCompra(TipoDeCompra tipoDeCompra) {
		this.tipoDeCompra = tipoDeCompra;
	}

	public String getNomeLoja() {
		return nomeLoja;
	}

	public void setNomeLoja(String nomeLoja) {
		this.nomeLoja = nomeLoja;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
