package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import enums.Grupo;

@Entity
public class Pessoa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String nome;

	@Column(unique = true)
	private String login;
	private String senha;

	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private List<Telefone> telefones;

	@Embedded
	private Endereco endereco;

	@Enumerated(EnumType.STRING)
	private Grupo grupo;
	
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private List<Report> report;

	// Atributos especificos de usuarios do grupo Cliente
	@Column(unique = true)
	private String email;

	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private List<Compra> historicoDeCompras;

	// Atributos especificos de usuarios do grupo Proprietario
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private List<Loja> lojas;

	public Pessoa() {

	}

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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Compra> getHistoricoDeCompras() {
		return historicoDeCompras;
	}

	public void setHistoricoDeCompras(List<Compra> historicoDeCompras) {
		this.historicoDeCompras = historicoDeCompras;
	}

	public List<Loja> getLojas() {
		return lojas;
	}

	public void setLojas(List<Loja> lojas) {
		this.lojas = lojas;
	}
	
	public void addCompra(Compra compra) {
		historicoDeCompras.add(compra);
	}
	
	public void removeCompra(Compra compra) {
		historicoDeCompras.remove(compra);
	}

	public void addLoja(Loja loja) {
		lojas.add(loja);
	}

	public void removeLoja(Loja loja) {
		lojas.remove(loja);
	}

	public void limparCamposEspecificos() {
		if (grupo == null) {
			return;
		}

		if (Grupo.PROPRIETARIO == grupo) {
			email = null;
			historicoDeCompras = null;
		} else if (Grupo.CLIENTE == grupo) {
			lojas = null;
		}
	}
	
	public List<Produto> getTodosProdutos(){
		List<Produto> produtos = new ArrayList<>();
		for (Loja loja : lojas) {
			for (Produto produto : loja.getProdutos()) {
				produtos.add(produto);
			}
		}
		return produtos;
	}

	public List<Report> getReport() {
		return report;
	}

	public void setReport(List<Report> report) {
		this.report = report;
	}

}
