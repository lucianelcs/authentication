package com.escola.autenticacao.autenticacao.model.service.base;


import java.sql.SQLException;
import java.util.Map;

import com.escola.autenticacao.autenticacao.model.dao.base.BaseDAOLocator;
import com.escola.autenticacao.autenticacao.model.entity.User;
import com.escola.autenticacao.autenticacao.model.util.ConnectionProvider;

public abstract class BaseUserService<E> extends BaseService<User>{
	public BaseUserService(ConnectionProvider connectionProvider, BaseDAOLocator daoProvider) {
		super(connectionProvider, daoProvider);
	}

	public abstract E readByCriteria(Map<Integer, Object> criteria) throws SQLException;
}