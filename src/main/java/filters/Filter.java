package filters;

import java.io.Serializable;

public interface Filter extends Serializable {
	
	default public void validate() {
		// Não fazer validação alguma por padrão.
	}

}
