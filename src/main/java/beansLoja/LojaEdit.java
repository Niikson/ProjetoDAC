package beansLoja;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entidades.EnderecoLoja;
import entidades.Loja;
import managedBeans.AbstractBean;
import managedBeans.EnderecoPaginas;
import services.LojaService;
import services.ServiceDacException;

@Named
@ViewScoped
public class LojaEdit extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private LojaService lojaService;

	private Loja loja;

	public void init() {
		if (loja == null) {
			loja = new Loja();
			loja.setEndereco(new EnderecoLoja());
		}
		else {
			if(loja.getEndereco() == null)
				loja.setEndereco(new EnderecoLoja());
		}
	}

	public String saveLoja() {
		try {
			if (isEdicaoDeLoja())
				lojaService.update(loja);
			else {
				loja.setPessoa(getPessoaLogada());
				lojaService.save(loja);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Loja '" + loja.getNome() + "' saved");

		return EnderecoPaginas.PAGINA_PRINCIPAL_LOJA;
	}

	public Loja selecionar() {
		try {
			loja = lojaService.getByID(loja.getId());
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}
	
	public void salvarEndereco() {
		reportarMensagemDeSucesso(String.format("Endereço '%s' is saved.", loja.getEndereco().getRua()));
	}

	public String paginaPrincipal() {
		return EnderecoPaginas.PAGINA_PRINCIPAL_LOJA;
	}

	public String criarLoja() {
		return EnderecoPaginas.PAGINA_LOJA_CREATE;
	}

	public boolean isLojaSelecionado() {
		return loja != null;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public boolean isEdicaoDeLoja() {
		return loja.getId() != null;
	}

}
