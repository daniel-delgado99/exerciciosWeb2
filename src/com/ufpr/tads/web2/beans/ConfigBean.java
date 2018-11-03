package com.ufpr.tads.web2.beans;

import java.io.Serializable;

public class ConfigBean implements Serializable {
	private String adminEmail;
	
	public ConfigBean() {
	}

	public String getAdminEmail() {
		return this.adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

}
