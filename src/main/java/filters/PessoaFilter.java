package filters;

import enums.Grupo;

public class PessoaFilter implements Filter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String login;
	private Grupo grupo;
	
	public PessoaFilter() {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	public boolean isEmpty() {
		if(nome!=null)
			return false;
		if(login!=null)
			return false;
		if(grupo!=null)
			return false;
		return true;
	}

}
