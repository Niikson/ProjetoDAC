package beansLoja;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entidades.Loja;
import filters.LojaFilter;
import managedBeans.AbstractBean;
import managedBeans.EnderecoPaginas;
import services.LojaService;
import services.ServiceDacException;

@Named
@ViewScoped
public class ManageLoja extends AbstractBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private LojaService lojaService;
	
	private List<Loja> lojas;
	
	private LojaFilter lojaFilter;

	public List<Loja> getLojas() {
		return lojas;
	}

	public void setLojas(List<Loja> lojas) {
		this.lojas = lojas;
	}

	public LojaFilter getLojaFilter() {
		return lojaFilter;
	}

	public void setLojaFilter(LojaFilter lojaFilter) {
		this.lojaFilter = lojaFilter;
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
			getLojaFilter().setId(this.getPessoaLogada().getId());
			lojas = lojaService.findBy(getLojaFilter());
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}


	public String limpar() {
		this.lojaFilter = new LojaFilter();
		return null;
	}
	
	public String delete(Loja loja) {
		try {
			lojaService.delete(loja);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso("Loja '" + loja.getNome() + "' deleted");
		
		return EnderecoPaginas.PAGINA_PRINCIPAL_LOJA;
	}

}
