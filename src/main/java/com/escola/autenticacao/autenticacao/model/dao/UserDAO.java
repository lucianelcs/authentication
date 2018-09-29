package com.escola.autenticacao.autenticacao.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.escola.autenticacao.autenticacao.model.criteria.UserCriteria;
import com.escola.autenticacao.autenticacao.model.dao.base.BaseDAO;
import com.escola.autenticacao.autenticacao.model.entity.User;


public class UserDAO extends BaseDAO<User> {
	public User readByCriteria(Connection conn, Map<Integer, Object> criteria) throws SQLException {
		String sql = "SELECT * FROM user u WHERE 1=1";
		List<Object> paramList = new ArrayList<>();
		User usuario = new User();
		sql = applyCriteria(criteria, sql, paramList);
		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			setCriteriaParameters(statement, paramList);
			ResultSet resultSet = statement.executeQuery();
			usuario = extractEntity(resultSet);
		} catch (SQLException e) {
			throw e;
		}
		return usuario;
	}
	
	@Override
	protected List<User> extractEntityList(ResultSet resultSet) throws SQLException {
		Map<Long, User> userMap = new HashMap<>();
		List<User> userList = new ArrayList<>();
		if (resultSet != null) {
			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				User user = userMap.get(id);
				if (user == null) {
					user = new User();
					setUser(user, resultSet, id);
					userMap.put(id, user);
					userList.add(user);
				}
			}
		}
		return userList;
	}

	@Override
	protected User extractEntity(ResultSet resultSet) throws SQLException {
		List<User> list = this.extractEntityList(resultSet);
		User user = null;
		if (list != null && !list.isEmpty()) {
			user = list.get(0);
		}
		return user;
	}

	@Override
	protected String applyCriteria(Map<Integer, Object> criteria, String sql, List<Object> paramList) {
		if (criteria != null && !criteria.isEmpty()) {
			Integer key = UserCriteria.USER_EQUAL;
			if (criteria.containsKey(key)) {
				sql += " AND username LIKE ?";
				paramList.add(criteria.get(key));
			}
			
			key = UserCriteria.PASSWORD_EQUAL;
			if (criteria.containsKey(key)) {
				sql += " AND password LIKE ?";
				paramList.add(criteria.get(key));
			}
		}
		return sql;
	}
	
	private void setUser(User user, ResultSet resultSet, Long id) throws SQLException {
		user.setId(id);
		user.setPassword(resultSet.getString("password"));
		user.setUsername(resultSet.getString("username"));
	}

}
