package com.escola.autenticacao.autenticacao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.escola.autenticacao.autenticacao.model.dao.DAOLocator;
import com.escola.autenticacao.autenticacao.model.dao.UserDAO;
import com.escola.autenticacao.autenticacao.model.entity.User;
import com.escola.autenticacao.autenticacao.model.service.UserService;
import com.escola.autenticacao.autenticacao.model.util.ConnectionProvider;

@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("unused")
public class UserTest {
	private Connection mockConnectionForSuccess() throws SQLException {
		Connection connectionMock = Mockito.mock(Connection.class);
		Mockito.doNothing().when(connectionMock).commit();
		return connectionMock;
	}

	private Connection mockConnectionForFail() throws SQLException {
		Connection connectionMock = Mockito.mock(Connection.class);
		Mockito.doNothing().when(connectionMock).rollback();
		return connectionMock;
	}

	private ConnectionProvider mockConnectionProvider(Connection connectionMock) throws Exception {
		ConnectionProvider connectioProviderMock = Mockito.mock(ConnectionProvider.class);
		Mockito.doReturn(connectionMock).when(connectioProviderMock).getConnection();
		return connectioProviderMock;
	}

	private UserDAO mockDAOForSuccess() throws SQLException {
		UserDAO daoMock = Mockito.mock(UserDAO.class);
		Mockito.doReturn(new User()).when(daoMock).readByCriteria(Mockito.any(), Mockito.any());
		return daoMock;
	}

	private UserDAO mockDAOForFail() throws SQLException {
		UserDAO daoMock = Mockito.mock(UserDAO.class);
		Mockito.doThrow(SQLException.class).when(daoMock).readByCriteria(Mockito.any(), Mockito.any());
		return daoMock;
	}

	private void verifyConnectionUsageOnSuccess(Connection connectionMock) throws SQLException {
		Mockito.verify(connectionMock, Mockito.times(1)).commit();
		Mockito.verify(connectionMock, Mockito.times(0)).rollback();
		Mockito.verify(connectionMock, Mockito.times(1)).close();
	}

	private void verifyConnectionUsageOnFail(Connection connectionMock) throws SQLException {
		Mockito.verify(connectionMock, Mockito.times(0)).commit();
		Mockito.verify(connectionMock, Mockito.times(1)).rollback();
		Mockito.verify(connectionMock, Mockito.times(1)).close();
	}

	private DAOLocator mockDAOLocator(UserDAO daoMock) throws SQLException {
		DAOLocator daoProviderMock = Mockito.mock(DAOLocator.class);
		Mockito.doReturn(daoMock).when(daoProviderMock).get(User.class);
		return daoProviderMock;
	}
	
	@Test
	public void shouldReadUserByCriteria() throws Exception {
		UserService userService = null;
		Connection connectionMock = null;
		GIVEN: {
			connectionMock = this.mockConnectionForSuccess();
			ConnectionProvider connectioProviderMock = this.mockConnectionProvider(connectionMock);
			DAOLocator daoProviderMock = this.mockDAOLocator(this.mockDAOForSuccess());
			userService = new UserService(connectioProviderMock, daoProviderMock);
		}
		WHEN: {
			userService.readByCriteria(new HashMap<>());
		}
		THEN: {
			verifyConnectionUsageOnSuccess(connectionMock);
		}
	}
	
	@Test
	public void shouldThrowSQLExceptionWhenTriesToReadUserByCriteria() throws Exception {
		UserService userService = null;
		Connection connectionMock = null;
		GIVEN: {
			connectionMock = this.mockConnectionForFail();
			ConnectionProvider connectioProviderMock = this.mockConnectionProvider(connectionMock);
			DAOLocator daoProviderMock = this.mockDAOLocator(this.mockDAOForFail());
			userService = new UserService(connectioProviderMock, daoProviderMock);
		}
		WHEN: {
			userService.readByCriteria(new HashMap<>());
		}
		THEN: {
			verifyConnectionUsageOnFail(connectionMock);
		}
	}
}
