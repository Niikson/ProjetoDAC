package beansTelefone;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entidades.Telefone;
import filters.TelefoneFilter;
import managedBeans.AbstractBean;
import managedBeans.EnderecoPaginas;
import services.ServiceDacException;
import services.TelefoneService;

@Named
@ViewScoped
public class ManageTelefone extends AbstractBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private TelefoneService telefoneService;
	
	private List<Telefone> telefones;
	
	private TelefoneFilter telefoneFilter;

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public TelefoneFilter getTelefoneFilter() {
		return telefoneFilter;
	}

	public void setTelefoneFilter(TelefoneFilter telefoneFilter) {
		this.telefoneFilter = telefoneFilter;
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
			getTelefoneFilter().setId(this.getPessoaLogada().getId());
			telefones = telefoneService.findBy(getTelefoneFilter());
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}


	public String limpar() {
		this.telefoneFilter = new TelefoneFilter();
		return null;
	}
	
	public String delete(Telefone telefone) {
		try {
			telefoneService.delete(telefone);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso("Telefone '" + telefone.getNumero() + "' deleted");
		
		return EnderecoPaginas.PAGINA_PRINCIPAL_TELEFONE;
	}

}
