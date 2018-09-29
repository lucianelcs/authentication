package com.escola.autenticacao.autenticacao.model.util;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionProvider {
	private DBConfig dbConfig;
	
	public ConnectionProvider(DBConfig dbConfig) {
		this.dbConfig = dbConfig;
	}

	public Connection getConnection() throws Exception {
		Connection conn = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(this.dbConfig.getUrl(), this.dbConfig.getUsername(), this.dbConfig.getPassword());
		conn.setAutoCommit(this.dbConfig.getAutoCommit());
		return conn;
	}
}