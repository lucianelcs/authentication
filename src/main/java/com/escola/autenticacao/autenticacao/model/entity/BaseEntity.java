package com.escola.autenticacao.autenticacao.model.entity;

public abstract class BaseEntity {
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
