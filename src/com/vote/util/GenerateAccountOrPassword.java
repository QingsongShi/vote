package com.vote.util;

import java.util.Random;

public class GenerateAccountOrPassword {

	private static char[] c = { '1','2','3','4','5','6',
								'7','8','9','a','b','c',
								'd','e','f','g','h','i',
								'g','k','m','n','p','q',
								'r','s','t','u','v','w',
								'x','y'
							  };
	// �˺ų���
	private static final int ACCOUNT_LENGTH = 6;
	
	// ���볤��
	private static final int PASSWORD_LENGTH = 8;
	
	private static Random random = new Random();
	/**
	 * �Զ������˺�
	 * @return
	 */
	public static String generateAccount() {
		StringBuffer account = new StringBuffer();
		for(int i=0; i<ACCOUNT_LENGTH; i++) {
			account.append(c[random.nextInt(c.length)]);
		}
		return account.toString();
	}
	/**
	 * �Զ���������
	 * @return
	 */
	public static String generatePassword() {
		StringBuffer password = new StringBuffer();
		for(int i=0; i<PASSWORD_LENGTH; i++) {
			password.append(c[random.nextInt(c.length)]);
		}
		return password.toString();
	}
}
