package filters;

public class ReportFilter implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String data;
	private String nomeUsuario;

	public ReportFilter() {

	}

	public boolean isEmpty() {
		if (data == null)
			return false;
		else if (nomeUsuario == null)
			return false;
		return true;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

}
