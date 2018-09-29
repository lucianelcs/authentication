package com.escola.autenticacao.autenticacao.model.service.factory;

import com.escola.autenticacao.autenticacao.model.constants.DatabaseConstant;
import com.escola.autenticacao.autenticacao.model.dao.DAOLocator;
import com.escola.autenticacao.autenticacao.model.service.UserService;
import com.escola.autenticacao.autenticacao.model.util.ConnectionProvider;
import com.escola.autenticacao.autenticacao.model.util.DBConfig;

public class UserServiceFactory {
	public UserService create() {
		ConnectionProvider connectionProvider = new ConnectionProvider(new DBConfig(DatabaseConstant.DATASOURCE_NAME, DatabaseConstant.DB_ENDPOINT, DatabaseConstant.DB_USER, DatabaseConstant.DB_PASSWORD));
		return new UserService(connectionProvider, new DAOLocator());
	}
}