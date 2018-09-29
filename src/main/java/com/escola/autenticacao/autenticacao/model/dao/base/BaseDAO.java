package com.escola.autenticacao.autenticacao.model.dao.base;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import com.escola.autenticacao.autenticacao.model.entity.BaseEntity;

public abstract class BaseDAO<E extends BaseEntity> {

	public abstract E readByCriteria(Connection conn, Map<Integer, Object> criteria) throws SQLException;

	protected void setCriteriaParameters(PreparedStatement statement, List<Object> paramList) throws SQLException {
		int i = 0;

		for (Object param : paramList) {
			boolean ready = false;
			if ((!ready) && param instanceof String) {
				ready = true;
				statement.setString(++i, (String) param);
			}

			if ((!ready) && param instanceof Long) {
				ready = true;
				statement.setLong(++i, (Long) param);
			}

			if ((!ready) && param instanceof Integer) {
				ready = true;
				statement.setInt(++i, (Integer) param);
			}

			if ((!ready) && param instanceof Boolean) {
				ready = true;
				statement.setBoolean(++i, (Boolean) param);
			}

			if ((!ready) && param.getClass().isArray()) {
				Array array = statement.getConnection().createArrayOf("BIGINT", (Long[]) param);
				ready = true;
				statement.setArray(++i, array);
			}
		}
	}

	protected void setLongParameter(PreparedStatement statement, int i, Long param) throws SQLException {
		if (param != null) {
			statement.setLong(i, param);
		} else {
			statement.setNull(i, Types.BIGINT);
		}
	}

	protected void setIntegerParameter(PreparedStatement statement, int i, Integer param) throws SQLException {
		if (param != null) {
			statement.setInt(i, param);
		} else {
			statement.setNull(i, Types.INTEGER);
		}
	}

	protected void setStringParameter(PreparedStatement statement, int i, String param) throws SQLException {
		if (param != null) {
			statement.setString(i, param);
		} else {
			statement.setNull(i, Types.VARCHAR);
		}
	}

	protected void setBooleanParameter(PreparedStatement statement, int i, Boolean param) throws SQLException {
		if (param != null) {
			statement.setBoolean(i, param);
		} else {
			statement.setNull(i, Types.BOOLEAN);
		}
	}

	protected abstract E extractEntity(ResultSet resultSet) throws SQLException;

	protected abstract String applyCriteria(Map<Integer, Object> criteria, String sql, List<Object> paramList);
	
	protected abstract List<E> extractEntityList(ResultSet var1) throws SQLException;
}