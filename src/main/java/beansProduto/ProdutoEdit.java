package beansProduto;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entidades.Loja;
import entidades.Produto;
import managedBeans.AbstractBean;
import managedBeans.EnderecoPaginas;
import services.ProdutoService;
import services.ServiceDacException;

@Named
@ViewScoped
public class ProdutoEdit extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoService produtoService;

	private Produto produto;

	private Loja loja;

	public void init() {
		if (produto == null)
			produto = new Produto();
	}

	public String saveProduto() {
		try {
			if (isEdicaoDeProduto())
				produtoService.update(produto);
			else {
				produto.setLoja(loja);
				produtoService.save(produto);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Produto '" + produto.getNome() + "' saved");

		return EnderecoPaginas.PAGINA_PRINCIPAL_PRODUTO;
	}

	public Produto selecionar() {
		try {
			produto = produtoService.getByID(produto.getId());
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String paginaPrincipal() {
		return EnderecoPaginas.PAGINA_PRINCIPAL_PRODUTO;
	}

	public String criarProduto() {
		return EnderecoPaginas.PAGINA_PRODUTO_CREATE;
	}

	public boolean isProdutoSelecionado() {
		return produto != null;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public boolean isEdicaoDeProduto() {
		return produto.getId() != null;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

}
