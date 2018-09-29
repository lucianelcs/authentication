package com.escola.autenticacao.autenticacao.model.util;

public class DBConfig {

	private String name;
	private String url;
	private String username;
	private String password;
	private Boolean autoCommit;
	private Integer connectionTimeout;
	private Integer idleTimeout;
	private Integer maxLifetime;
	private Integer maximumPoolSize;
	private Integer minimumIdle;

	public DBConfig(String name, String url, String username, String password) {
		this.name = name;
		this.url = url;
		this.username = username;
		this.password = password;
		this.autoCommit = Boolean.FALSE;
		this.connectionTimeout = 30000;
		this.idleTimeout = 600000;
		this.maxLifetime = 1800000;
		this.maximumPoolSize = 10;
		this.minimumIdle = 5;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setAutoCommit(Boolean autoCommit) {
		this.autoCommit = autoCommit;
	}

	public Boolean getAutoCommit() {
		return autoCommit;
	}

	public Integer getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(Integer connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public Integer getIdleTimeout() {
		return idleTimeout;
	}

	public void setIdleTimeout(Integer idleTimeout) {
		this.idleTimeout = idleTimeout;
	}

	public Integer getMaxLifetime() {
		return maxLifetime;
	}

	public void setMaxLifetime(Integer maxLifetime) {
		this.maxLifetime = maxLifetime;
	}

	public Integer getMaximumPoolSize() {
		return maximumPoolSize;
	}

	public void setMaximumPoolSize(Integer maximumPoolSize) {
		this.maximumPoolSize = maximumPoolSize;
	}

	public Integer getMinimumIdle() {
		return minimumIdle;
	}

	public void setMinimumIdle(Integer minimumIdle) {
		this.minimumIdle = minimumIdle;
	}
}
