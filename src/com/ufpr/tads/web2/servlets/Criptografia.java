package com.ufpr.tads.web2.servlets;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia {
	static MessageDigest md = null;
	
	public static String criptografar(String senha) {
		String senhaCriptografada = "";
		
		try {
			md = MessageDigest.getInstance("MD5");
			senhaCriptografada = new String(hexCodes(md.digest(senha.getBytes())));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return senhaCriptografada;
	}
	
	public static char[] hexCodes(byte[] text) {
		char[] hexOutput = new char[text.length * 2];
		String hexString = "";
		
		for (int i = 0; i < text.length; i++) {
			hexString = "00" + Integer.toHexString(text[i]);
			hexString.toUpperCase().getChars(hexString.length() - 2, hexString.length(), hexOutput, i*2);
		}
		return hexOutput;
	}

}
