package com.escola.autenticacao.autenticacao.model.dao;

import com.escola.autenticacao.autenticacao.model.dao.base.BaseDAOLocator;
import com.escola.autenticacao.autenticacao.model.entity.User;

public class DAOLocator extends BaseDAOLocator {

	@Override
	protected void loadMappings() {
		add(User.class, UserDAO.class);
	}
}