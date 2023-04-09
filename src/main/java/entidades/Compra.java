package entidades;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import enums.TipoDeCompra;

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String data;

	@ManyToOne
	private Produto produto;
	
	private Integer quantidade;
	private Double total;

	@Enumerated(EnumType.STRING)
	private TipoDeCompra tipoDeCompra;

	// Atributos especificos de compra do tipo compra
	private Integer numeroCartao;
	private String nomeTitular;

	// Atributos especificos de compra do tipo reserva
	private String dataRetirada;

	@ManyToOne
	private Pessoa pessoa;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public TipoDeCompra getTipoDeCompra() {
		return tipoDeCompra;
	}

	public void setTipoDeCompra(TipoDeCompra tipoDeCompra) {
		this.tipoDeCompra = tipoDeCompra;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(Integer numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getNomeTitular() {
		return nomeTitular;
	}

	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
	}

	public String getDataRetirada() {
		return dataRetirada;
	}

	public void setDataRetirada(String dataRetirada) {
		this.dataRetirada = dataRetirada;
	}
	
	public void limparCamposEspecificos() {
		if (tipoDeCompra == null) {
			return;
		}

		if (TipoDeCompra.COMPRA == tipoDeCompra) {
			dataRetirada = null;
		} else if (TipoDeCompra.RESERVA == tipoDeCompra) {
			numeroCartao = null;
			nomeTitular = null;
		}
	}

}
