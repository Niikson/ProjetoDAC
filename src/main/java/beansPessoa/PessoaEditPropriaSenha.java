package beansPessoa;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import entidades.Pessoa;

@Named
@ViewScoped
public class PessoaEditPropriaSenha extends PessoaEditSenha{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void init() {
		Pessoa usuarioLogado = getPessoaLogada();
		setPessoa(usuarioLogado);
	}

}
