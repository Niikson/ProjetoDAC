package managedBeans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import entidades.Pessoa;

@Named
@RequestScoped
public class InformacoesPessoa extends AbstractBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Pessoa informacaoPessoa;
	
	@PostConstruct
	public void init() {
		this.informacaoPessoa = getPessoaLogada();
	}

	public Pessoa getInformacaoPessoa() {
		return informacaoPessoa;
	}

	public void setInformacaoPessoa(Pessoa informacaoPessoa) {
		this.informacaoPessoa = informacaoPessoa;
	}

}
