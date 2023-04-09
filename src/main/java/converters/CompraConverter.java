package converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import entidades.Compra;
import services.CompraService;
import services.ServiceDacException;

@FacesConverter(forClass = Compra.class)
public class CompraConverter implements Converter<Compra>{
	
	@Inject
	private CompraService compra;

	@Override
	public Compra getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}

		try {
			int id = Integer.parseInt(value);
			return compra.getByID(id);
		} catch (ServiceDacException | NumberFormatException e) {
			String msgErroStr = String.format(
					"Erro de conversão! Não foi possível realizar a conversão da string '%s' para o tipo esperado.",
					value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Compra value) {
		if (!(value instanceof Compra)) {
			return null;
		}
		
		Integer id = ((Compra) value).getId();
		return (id != null) ? id.toString() : null;
	}

}
