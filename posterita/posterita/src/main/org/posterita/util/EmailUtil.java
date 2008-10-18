/*
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * @author Servansingh
 * */

package org.posterita.util;

import java.util.Properties;

import javax.mail.internet.InternetAddress;

import org.compiere.model.MCountry;
import org.compiere.util.EMail;
import org.posterita.beans.UserBean;
import org.posterita.exceptions.InvalidEmailException;

public class EmailUtil
{
	public static final String DEFAULT_SMTP_SERVER = "mail.tamakict.com";
	public static final String SUPPORT_EMAIL_ADDRESS = "support@posterita.org";
	public static final String DEFAULT_USER_AUTH_NAME = "admin@tamakict.com";
	public static final String DEFAULT_USER_AUTH_PWD = "kaizen";
	
	public static boolean send(Properties ctx, String smtpHost, String from, String to, 
			String subject, String message, String username, String password)
	{
		EMail email = new EMail(ctx, smtpHost, from, to, subject, message);
		
		email.createAuthenticator(username, password);
		String retMsg = email.send();
		
		return EMail.SENT_OK.equals(retMsg);
	}
	
	/**
	 * Validates an email address. Throws InvalidEmailException if mail address
	 * is not valid
	 * @param emailAddress
	 * @throws InvalidEmailException
	 */
	public static void checkEMail(String emailAddress) throws InvalidEmailException
	{
		if (emailAddress == null)
		{
			throw new InvalidEmailException("No email provided");
		}
		
		try
		{
			InternetAddress iNetAddress = new InternetAddress(emailAddress);
			iNetAddress.validate();
		}
		catch (Exception ex)
		{
			throw new InvalidEmailException(ex.getMessage(), ex);
		}
	}
	
	public static boolean sendRegistrationEmail(Properties ctx, UserBean userBean)
	{
		if (userBean == null)
		{
			throw new IllegalArgumentException("User cannot be null");
		}
		
		MCountry country = MCountry.get(ctx, userBean.getCountryId());
		StringBuffer msg = new StringBuffer();
        msg.append("Full Name: " + userBean.getName() + " " + userBean.getUserSurname());
        msg.append("\n\nAddress: " + userBean.getAddress1());
        msg.append("\n\nCity: " + userBean.getCity());
        msg.append("\n\nCountry: " + country.getName());
        msg.append("\n\nCompany: " + userBean.getCompany());
        msg.append("\n\nIndustry: " + userBean.getIndustry());
        msg.append("\n\nEmail: " + userBean.getEmail());
        msg.append("\n\nRemarks: " + userBean.getComments());
        
        String subject = "Posterita POS User Registration";
		String from = userBean.getEmail();
		
		return send(ctx, DEFAULT_SMTP_SERVER, from, 
				SUPPORT_EMAIL_ADDRESS, subject, msg.toString(), 
				DEFAULT_USER_AUTH_NAME, DEFAULT_USER_AUTH_PWD);
	}

}
