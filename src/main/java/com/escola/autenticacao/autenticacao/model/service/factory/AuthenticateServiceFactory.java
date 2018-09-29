package com.escola.autenticacao.autenticacao.model.service.factory;

import com.escola.autenticacao.autenticacao.model.service.AuthenticateService;

public class AuthenticateServiceFactory {
	public AuthenticateService create() {
		return new AuthenticateService();
	}
}
