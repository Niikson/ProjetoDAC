package converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import entidades.Loja;
import services.LojaService;
import services.ServiceDacException;

@FacesConverter(forClass = Loja.class)
public class LojaConverter implements Converter<Loja>{
	
	@Inject
	private LojaService loja;

	@Override
	public Loja getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}

		try {
			int id = Integer.parseInt(value);
			return loja.getByID(id);
		} catch (ServiceDacException | NumberFormatException e) {
			String msgErroStr = String.format(
					"Erro de conversão! Não foi possível realizar a conversão da string '%s' para o tipo esperado.",
					value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Loja value) {
		if (!(value instanceof Loja)) {
			return null;
		}
		
		Integer id = ((Loja) value).getId();
		return (id != null) ? id.toString() : null;
	}

}
