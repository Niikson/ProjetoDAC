package converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import entidades.Telefone;
import services.ServiceDacException;
import services.TelefoneService;

@FacesConverter(forClass = Telefone.class)
public class TelefoneConverter implements Converter<Telefone>{
	
	@Inject
	private TelefoneService telefone;

	@Override
	public Telefone getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}

		try {
			int id = Integer.parseInt(value);
			return telefone.getByID(id);
		} catch (ServiceDacException | NumberFormatException e) {
			String msgErroStr = String.format(
					"Erro de conversão! Não foi possível realizar a conversão da string '%s' para o tipo esperado.",
					value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Telefone value) {
		if (!(value instanceof Telefone)) {
			return null;
		}
		
		Integer id = ((Telefone) value).getId();
		return (id != null) ? id.toString() : null;
	}

}
