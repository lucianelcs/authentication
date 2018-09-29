package com.escola.autenticacao.autenticacao.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.escola.autenticacao.autenticacao.model.constants.RestConstant;
import com.escola.autenticacao.autenticacao.model.dto.UserDTO;
import com.escola.autenticacao.autenticacao.model.entity.User;
import com.escola.autenticacao.autenticacao.model.service.AuthenticateService;
import com.escola.autenticacao.autenticacao.model.service.factory.AuthenticateServiceFactory;
import com.escola.autenticacao.autenticacao.model.util.JsonUtil;


@RestController	
public class UserController {

	private static Log logger = LogFactory.getLog(UserController.class.getName());
	
	private AuthenticateService authenticateService;

	
	public UserController() {
		authenticateService = new AuthenticateServiceFactory().create();
	}
	
	@RequestMapping(value = RestConstant.RESOURCE_SEPARATOR + RestConstant.AUTHENTICATION_RESOURCE, method = RequestMethod.POST)
	public ResponseEntity<?> authenticateUser(@RequestHeader HttpHeaders headers, @RequestBody String body) {
		ResponseEntity<?> response = null;
		UserDTO userDTO = (UserDTO) JsonUtil.convertToObject(body, UserDTO.class);
		logger.info("Authentication - User: " +userDTO.getUsername());
		User user;
		try {
			user = authenticateService.authenticateUser(userDTO);
			if (user != null) {
				response = new ResponseEntity<>(HttpStatus.OK);
				logger.info("User: " +userDTO.getUsername() + " authenticated successfully");
			} else {
				response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				logger.info("Invalid password or user");
			}	
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException | SQLException e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		return response;
	}
}
