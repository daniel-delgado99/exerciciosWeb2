package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.dao.LoginDAO;
import com.ufpr.tads.web2.beans.LoginBean;

public class LoginFacade {
	public static LoginBean efetuarLogin(String email, String senha) {
		return LoginDAO.efetuarLogin(email, senha);
	}
}
