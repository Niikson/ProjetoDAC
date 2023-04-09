package beansReport;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entidades.Report;
import filters.ReportFilter;
import managedBeans.AbstractBean;
import managedBeans.EnderecoPaginas;
import services.ReportService;
import services.ServiceDacException;

@Named
@ViewScoped
public class ManageReport extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ReportService reportService;

	private List<Report> reports;

	private ReportFilter reportFilter;

	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public ReportFilter getReportFilter() {
		return reportFilter;
	}

	public void setReportFilter(ReportFilter reportFilter) {
		this.reportFilter = reportFilter;
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
			reports = reportService.findBy(getReportFilter());
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String limpar() {
		this.reportFilter = new ReportFilter();
		return null;
	}

	public String delete(Report report) {
		try {
			reportService.delete(report);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Report '" + report.getId() + "' deleted");

		return EnderecoPaginas.PAGINA_PRINCIPAL_REPORT;
	}

}
