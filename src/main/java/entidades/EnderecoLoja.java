package entidades;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import enums.TipoSetor;

@Embeddable
public class EnderecoLoja {

	private String rua;
	private Integer numero;
	
	@Enumerated(EnumType.STRING)
	private TipoSetor setor;
	
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public TipoSetor getSetor() {
		return setor;
	}
	public void setSetor(TipoSetor setor) {
		this.setor = setor;
	}

}
