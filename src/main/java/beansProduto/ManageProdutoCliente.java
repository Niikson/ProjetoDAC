package beansProduto;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entidades.Produto;
import filters.ProdutoFilter;
import managedBeans.AbstractBean;
import services.ProdutoService;
import services.ServiceDacException;

@Named
@ViewScoped
public class ManageProdutoCliente extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoService produtoService;

	private List<Produto> produtos;

	private ProdutoFilter produtoFilter;

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public ProdutoFilter getProdutoFilter() {
		return produtoFilter;
	}

	public void setProdutoFilter(ProdutoFilter produtoFilter) {
		this.produtoFilter = produtoFilter;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@PostConstruct
	public void init() {
		limpar();
		buscar();
	}

	public String buscar() {
		try {
//			getProdutoFilter().setId(this.getPessoaLogada().getId());
			produtos = produtoService.findBy(getProdutoFilter());
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String limpar() {
		this.produtoFilter = new ProdutoFilter();
		return null;
	}

}
