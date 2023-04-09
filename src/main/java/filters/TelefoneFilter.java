package filters;

public class TelefoneFilter implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer DDD;
	private Integer numero;
	private Integer id;

	public TelefoneFilter() {

	}

	public boolean isEmpty() {
		if (DDD != null)
			return false;
		else if (numero != null)
			return false;
		return true;
	}

	public Integer getDDD() {
		return DDD;
	}

	public void setDDD(Integer dDD) {
		DDD = dDD;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
