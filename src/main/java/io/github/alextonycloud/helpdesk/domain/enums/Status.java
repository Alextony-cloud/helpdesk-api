package io.github.alextonycloud.helpdesk.domain.enums;

public enum Status {

	ADMIN(0, "BAIXA"), CLIENTE(1, "MEDIA"), TECNICO(0, "ALTA");
	
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
