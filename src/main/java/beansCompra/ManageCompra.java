package beansCompra;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entidades.Compra;
import filters.CompraFilter;
import managedBeans.AbstractBean;
import managedBeans.EnderecoPaginas;
import services.CompraService;
import services.ServiceDacException;

@Named
@ViewScoped
public class ManageCompra extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CompraService compraService;

	private List<Compra> compras;

	private CompraFilter compraFilter;

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public CompraFilter getCompraFilter() {
		return compraFilter;
	}

	public void setCompraFilter(CompraFilter compraFilter) {
		this.compraFilter = compraFilter;
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
			getCompraFilter().setId(this.getPessoaLogada().getId());
			compras = compraService.findBy(getCompraFilter());
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String limpar() {
		this.compraFilter = new CompraFilter();
		return null;
	}
	
	public String delete(Compra compra) {
		try {
			compraService.delete(compra);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso("Compra '" + compra.getId() + "' deleted");
		
		return EnderecoPaginas.PAGINA_PRINCIPAL_COMPRA;
	}

}
