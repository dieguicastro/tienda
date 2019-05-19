 	package com.tienda.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component // INDICA Q ES UN COMPONENETE DE SPRING PARA Q LO TOME COMO UN BEAN manejado por el
@Order(Ordered.HIGHEST_PRECEDENCE)   // AGREGFA IMPORTANCIA ALTA...
public class CORS implements Filter {  // implementa la interfaz filter q va A INTERCEPTAR LA PETICIONES 

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;

		
		//  AGReGAR EN LAS CABECERAS LO TRADICIONAL DE HABILITAR LAS PETICIONES GET PUT DELET... A CUALUIER DOMINIO...
		response.setHeader("Access-Control-Allow-Origin", "*"); // *  HABILITA TODOS LOS DOMINIOS..
		response.setHeader("Access-Control-Allow-Methods", "DELETE, GET, OPTIONS, PATCH, POST, PUT");
		response.setHeader("Access-Control-Max-Age", "3600"); 
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN");

		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(req, res);
		}
		// chain.doFilter(req, res);
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	
}
