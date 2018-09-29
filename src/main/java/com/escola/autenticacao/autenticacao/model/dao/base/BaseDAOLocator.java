package com.escola.autenticacao.autenticacao.model.dao.base;

import java.util.HashMap;
import java.util.Map;

import com.escola.autenticacao.autenticacao.model.entity.BaseEntity;

@SuppressWarnings("rawtypes")
public abstract class BaseDAOLocator {

	private Map<Class<? extends BaseEntity>, Class<? extends BaseDAO>> mappings = new HashMap<>();

	public BaseDAOLocator() {
		this.loadMappings();
	}

	protected abstract void loadMappings();

	protected void add(Class<? extends BaseEntity> entityClass, Class<? extends BaseDAO> daoClass){
		mappings.put(entityClass, daoClass);
	}

	public BaseDAO<?> get(Class<? extends BaseEntity> entityClass){
		BaseDAO<?> instance = null;
		try {
			instance = mappings.get(entityClass).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return instance;
	}

}
