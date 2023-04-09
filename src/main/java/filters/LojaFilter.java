package filters;

import entidades.EnderecoLoja;
import enums.TipoLoja;

public class LojaFilter implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private TipoLoja tipoLoja;
	private EnderecoLoja endereco;
	private Integer id;

	public LojaFilter() {

	}

	public boolean isEmpty() {
		if (nome != null)
			return false;
		else if (tipoLoja != null)
			return false;
		return true;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoLoja getTipoLoja() {
		return tipoLoja;
	}

	public void setTipoLoja(TipoLoja tipoLoja) {
		this.tipoLoja = tipoLoja;
	}

	public EnderecoLoja getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoLoja endereco) {
		this.endereco = endereco;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
