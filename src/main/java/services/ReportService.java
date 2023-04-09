package services;

import java.util.List;

import entidades.Report;
import filters.ReportFilter;

public interface ReportService {

	List<Report> findBy(ReportFilter filter) throws ServiceDacException;

	void save(Report report) throws ServiceDacException;

	void update(Report report) throws ServiceDacException;

	void delete(Report report) throws ServiceDacException;

	Report getByID(Integer id) throws ServiceDacException;

	List<Report> getAll() throws ServiceDacException;

}
