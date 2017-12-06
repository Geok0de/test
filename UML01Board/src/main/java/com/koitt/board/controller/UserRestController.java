package com.koitt.board.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.koitt.board.model.UserInfo;
import com.koitt.board.service.UserInfoService;

public class UserRestController {

	private Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// /user/login POST REST API 추가하기...
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public ResponseEntity<String> login(UserInfo userInfo) {

		logger.debug(userInfo);

		// 아이디 존재 유무와 비밀번호 일치 여부 확인
		boolean isMatched = userInfoService.isPasswordMatched(userInfo.getEmail(), userInfo.getPassword());

		if (isMatched) {

			// Base64 인코딩 전 평문
			String plainCredentials = userInfo.getEmail() + ":" + userInfo.getPassword();

			// 평문을 Base64로 인코딩
			String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));

			logger.debug(base64Credentials);

			return new ResponseEntity<String>(base64Credentials, HttpStatus.OK);
		}

		logger.debug("login failed");
		return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
	}

}
