package filters;

import enums.Categoria;
import enums.Genero;

public class ProdutoFilter implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private String tamanho;
	private Double preco;
	private Categoria categoria;
	private Genero genero;
	private String nomeLoja;
	private Integer id;

	public ProdutoFilter() {

	}

	public boolean isEmpty() {
		if (nome != null)
			return false;
		else if (tamanho != null)
			return false;
		else if (preco != null)
			return false;
		else if (categoria != null)
			return false;
		else if (genero != null)
			return false;
		else if(nomeLoja != null)
			return false;
		return true;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
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
