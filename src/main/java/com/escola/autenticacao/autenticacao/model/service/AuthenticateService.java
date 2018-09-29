package com.escola.autenticacao.autenticacao.model.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.escola.autenticacao.autenticacao.model.criteria.UserCriteria;
import com.escola.autenticacao.autenticacao.model.dto.UserDTO;
import com.escola.autenticacao.autenticacao.model.entity.User;
import com.escola.autenticacao.autenticacao.model.service.factory.UserServiceFactory;
import com.escola.autenticacao.autenticacao.model.util.Md5Util;

public class AuthenticateService {
	
	private UserService userService;
	
	public AuthenticateService() {
		userService = new UserServiceFactory().create();
	}
	
	public User authenticateUser(UserDTO userDTO) throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException {
		Map<Integer, Object> criteria = new HashMap<>();
		String password = Md5Util.generateMd5(userDTO.getPassword());
		criteria.put(UserCriteria.USER_EQUAL, userDTO.getUsername());
		criteria.put(UserCriteria.PASSWORD_EQUAL, password);
		User user = userService.readByCriteria(criteria);
		return user;
	}

}
