package com.escola.autenticacao.autenticacao.model.service.base;

import com.escola.autenticacao.autenticacao.model.dao.base.BaseDAOLocator;
import com.escola.autenticacao.autenticacao.model.util.ConnectionProvider;

public abstract class BaseService<E> {
	
	private ConnectionProvider connectionProvider;
	private BaseDAOLocator daoLocator;
	
	public BaseService(ConnectionProvider connectionProvider) {
		this.connectionProvider = connectionProvider;
	}

	public BaseService(ConnectionProvider connectionProvider, BaseDAOLocator daoProvider) {
		this.connectionProvider = connectionProvider;
		this.daoLocator = daoProvider;
	}
		
	public ConnectionProvider getConnectionProvider() {
		return connectionProvider;
	}

	public BaseDAOLocator getDAOLocator() {
		return daoLocator;
	}
}