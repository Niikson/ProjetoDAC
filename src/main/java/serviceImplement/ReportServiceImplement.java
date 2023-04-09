package serviceImplement;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import dao.ReportDAO;
import entidades.Report;
import filters.ReportFilter;
import services.ReportService;
import services.ServiceDacException;
import util.TransacionalCdi;

public class ReportServiceImplement implements Serializable, ReportService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ReportDAO reportDAO;

	@Override
	public List<Report> findBy(ReportFilter filter) throws ServiceDacException {
		try {
			return reportDAO.findBy(filter);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void save(Report report) throws ServiceDacException {
		try {
			report.setData(pegarHora());
			reportDAO.save(report);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void update(Report report) throws ServiceDacException {
		try {
			reportDAO.update(report);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void delete(Report report) throws ServiceDacException {
		try {
			reportDAO.delete(report);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public Report getByID(Integer id) throws ServiceDacException {
		try {
			return reportDAO.getByID(id);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public List<Report> getAll() throws ServiceDacException {
		try {
			return reportDAO.getAll();
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}
	
	public String pegarHora() {
		Date data_hora = new Date(System.currentTimeMillis());
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String str=fmt.format(data_hora);
		return str;
	}

}
