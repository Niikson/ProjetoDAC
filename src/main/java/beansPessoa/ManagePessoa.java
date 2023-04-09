package beansPessoa;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entidades.Pessoa;
import filters.PessoaFilter;
import managedBeans.AbstractBean;
import managedBeans.EnderecoPaginas;
import services.PessoaService;
import services.ServiceDacException;

@Named
@ViewScoped
public class ManagePessoa extends AbstractBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PessoaService pessoaService;
	
	private List<Pessoa> pessoas;
	
	private PessoaFilter pessoaFilter;
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public PessoaFilter getPessoaFilter() {
		return pessoaFilter;
	}

	public void setPessoaFilter(PessoaFilter pessoaFilter) {
		this.pessoaFilter = pessoaFilter;
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
			pessoas = pessoaService.findBy(getPessoaFilter());
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String limpar() {
		this.pessoaFilter = new PessoaFilter();
		return null;
	}

	public String delete(Pessoa pessoa) {
		try {
			pessoaService.delete(pessoa);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso("Pessoa '" + pessoa.getNome() + "' deleted");
		
		return EnderecoPaginas.PAGINA_PRINCIPAL_PESSOA;
	}

}
