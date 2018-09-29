package com.escola.autenticacao.autenticacao.model.constants;

public class DatabaseConstant {
	
	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "senha@1234";
	public static final String SERVER_NAME = "localhost";
	public static final String DATASOURCE_NAME = "authentication";	
	public static final String DB_ENDPOINT = "jdbc:mysql://" + SERVER_NAME + "/" + DATASOURCE_NAME + "?useTimezone=true&serverTimezone=UTC";
}
