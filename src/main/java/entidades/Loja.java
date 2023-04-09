package entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import enums.TipoLoja;

@Entity
public class Loja {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
	@Embedded
	private EnderecoLoja endereco;
	
	@OneToMany(mappedBy = "loja", cascade = CascadeType.ALL)
	private List<Produto> produtos;
	
	@Enumerated(EnumType.STRING)
	private TipoLoja tipoLoja;
	
	@ManyToOne
	private Pessoa pessoa;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public EnderecoLoja getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoLoja endereco) {
		this.endereco = endereco;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public TipoLoja getTipoLoja() {
		return tipoLoja;
	}

	public void setTipoLoja(TipoLoja tipoLoja) {
		this.tipoLoja = tipoLoja;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public void addProduto(Produto produto) {
		produtos.add(produto);
	}
	
	public void removeProduto(Produto produto) {
		produtos.remove(produto);
	}

}
