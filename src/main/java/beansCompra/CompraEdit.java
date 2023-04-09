package beansCompra;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entidades.Compra;
import entidades.Produto;
import enums.TipoDeCompra;
import managedBeans.AbstractBean;
import managedBeans.EnderecoPaginas;
import services.CompraService;
import services.ServiceDacException;

@Named
@ViewScoped
public class CompraEdit extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CompraService compraService;

	private Compra compra;

	private Produto produto;

	public void init() {
		if (compra == null)
			compra = new Compra();
	}

	public String saveCompra() {
		try {
			if (isEdicaoDeCompra())
				compraService.update(compra);
			else {
				compra.setPessoa(getPessoaLogada());
				compra.setProduto(produto);
				compraService.save(compra);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Compra saved");

		return EnderecoPaginas.PAGINA_PRINCIPAL_COMPRA;
	}

	public Compra selecionar() {
		try {
			compra = compraService.getByID(compra.getId());
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String paginaPrincipal() {
		return EnderecoPaginas.PAGINA_PRINCIPAL_COMPRA;
	}

	public String criarCompra() {
		return EnderecoPaginas.PAGINA_COMPRA_CREATE;
	}

	public boolean isCompraSelecionado() {
		return compra != null;
	}
	
	public boolean eHCompra() {
		return compra != null && compra.getTipoDeCompra() == TipoDeCompra.COMPRA;
	}

	public boolean eHReserva() {
		return compra != null && compra.getTipoDeCompra() == TipoDeCompra.RESERVA;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public boolean isEdicaoDeCompra() {
		return compra.getId() != null;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
