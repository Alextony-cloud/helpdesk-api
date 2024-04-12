package io.github.alextonycloud.helpdesk.domain.enums;

public enum Status {

	BAIXA(0, "BAIXA"), MEDIA(1, "MEDIA"), ALTA(0, "ALTA");
	
	private Integer codigo;
	private String descricao;
	
	private Status(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Status toEnum(Integer cod) {
		if(cod == null) return null;
		
		for(Status x : Status.values()) {
			if(cod.equals(x.getCodigo())) return x;
		}
		throw new IllegalArgumentException("Prioridade inválida");
	}
}
