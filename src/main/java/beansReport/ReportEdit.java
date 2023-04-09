package beansReport;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entidades.Pessoa;
import entidades.Report;
import managedBeans.AbstractBean;
import managedBeans.EnderecoPaginas;
import services.ReportService;
import services.ServiceDacException;

@Named
@ViewScoped
public class ReportEdit extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ReportService reportService;

	private Report report;
	
	private Pessoa pessoa;

	public void init() {
		if (report == null)
			report = new Report();
	}
	
	public String saveReport() {
		try {
			if (isEdicaoDeReport())
				reportService.update(report);
			else {
				report.setPessoa(getPessoaLogada());
				reportService.save(report);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Report saved");

		return EnderecoPaginas.PAGINA_PRINCIPAL;
	}

	public Report selecionar() {
		try {
			report = reportService.getByID(report.getId());
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String paginaPrincipal() {
		return EnderecoPaginas.PAGINA_PRINCIPAL_REPORT;
	}

	public String criarReport() {
		return EnderecoPaginas.PAGINA_REPORT_CREATE;
	}

	public boolean isReportSelecionado() {
		return report != null;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public boolean isEdicaoDeReport() {
		return report.getId() != null;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
