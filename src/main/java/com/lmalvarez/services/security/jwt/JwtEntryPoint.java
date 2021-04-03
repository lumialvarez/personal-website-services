package com.lmalvarez.services.security.jwt;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class JwtEntryPoint implements AuthenticationEntryPoint {

	private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

	@Override
	public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e)
			throws IOException, ServletException {
		logger.error("No autorizado: " + e.getMessage() + " (Path:" + req.getMethod() + " " + req.getRequestURI() + ") "
				+ (new Date()).toString());

		ZonedDateTime now = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault());
		String strTimedate = now.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		res.addHeader("Content-Type", "application/json");
		res.getOutputStream().print("{"
				+ "\"message\":\"Unauthorized\","
				+ "\"details\":[\"Recurso no autorizado\"],"
				+ "\"statusCode\":401,"
				+ "\"statusDetail\":\"Unauthorized\","
				+ "\"timestamp\":\"" + strTimedate + "\"}");
	}
}