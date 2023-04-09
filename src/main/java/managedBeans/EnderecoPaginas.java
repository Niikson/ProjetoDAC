package managedBeans;

public final class EnderecoPaginas {
	
	public EnderecoPaginas() {
		throw new UnsupportedOperationException("Esta classe não deve ser instanciada!");
	}
	
	private static final String REDIRECT_TRUE = "?faces-redirect=true";
	
	public static final String PAGINA_PRINCIPAL = "/paginas/protegido/index.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_PRINCIPAL_PRODUTO = "/paginas/protegido/proprietario/produto/index.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_PRODUTO_CREATE= "/paginas/protegido/proprietario/produto/produto_create.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_PRINCIPAL_LOJA = "/paginas/protegido/proprietario/loja/index.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_LOJA_CREATE = "/paginas/protegido/proprietario/loja/loja_create.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_PRINCIPAL_PESSOA = "/paginas/publico/index.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_PESSOA_CREATE = "/paginas/publico/usuario_create.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_PRINCIPAL_COMPRA = "/paginas/protegido/cliente/compra/index.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_COMPRA_CREATE = "/paginas/protegido/cliente/compra/compra_create.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_PRINCIPAL_TELEFONE = "/paginas/protegido/telefone_index.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_TELEFONE_CREATE = "/paginas/protegido/telefone_edit.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_PRINCIPAL_REPORT = "/paginas/protegido/admin/report/index.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_REPORT_CREATE = "/paginas/protegido/cliente/report/report_create.xhtml" + REDIRECT_TRUE;

}
