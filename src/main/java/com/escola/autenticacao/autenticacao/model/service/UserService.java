package com.escola.autenticacao.autenticacao.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.escola.autenticacao.autenticacao.model.dao.UserDAO;
import com.escola.autenticacao.autenticacao.model.dao.base.BaseDAOLocator;
import com.escola.autenticacao.autenticacao.model.entity.User;
import com.escola.autenticacao.autenticacao.model.service.base.BaseUserService;
import com.escola.autenticacao.autenticacao.model.util.ConnectionProvider;

public class UserService extends BaseUserService<User> {
	private static Log logger = LogFactory.getLog(UserService.class.getName());
	
	public UserService(ConnectionProvider connectionProvider, BaseDAOLocator daoProvider) {
		super(connectionProvider, daoProvider);
	}

	@Override
	public User readByCriteria(Map<Integer, Object> criteria) throws SQLException {
		User user = null;
		try (Connection conn = getConnectionProvider().getConnection()) {
			UserDAO dao = (UserDAO) getDAOLocator().get(User.class);
			try {
				user = dao.readByCriteria(conn, criteria);
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				logger.error("Error reading the user in the database");
				throw new SQLException();
			}
		} catch (Exception e) {
			logger.error("Error connecting to database");
			throw new SQLException();
		}
		return user;
	}
}
