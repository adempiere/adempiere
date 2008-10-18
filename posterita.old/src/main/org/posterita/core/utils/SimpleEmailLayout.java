/*
 * Copyright (c) 2004 Kaizensoft Ltd. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Kaizensoft Ltd. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Kaizensoft.
 *
 * KAIZENSOFT MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR
 * A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. KAIZENSOFT SHALL NOT
 * BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING,
 * MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 * 
 * Created on Nov 22, 2004
 */
 
package org.posterita.core.utils;


public class SimpleEmailLayout
{

	public static final String header = 

		"<html>" +
			"<head>" +
				"<title>Email</title>" +
			"</head>"+

			"<body>" +
				"<table width=\"100%\" border=\"0\">" +
					"<tr>" +
						"<td align=\"left\" valign=\"bottom\">" +
							"<a href=\"http://www.posterita.com\" target=\"_blank\">" +
								"<img src=\"http://tmkgroup.dyndns.org/posterita/images/webstore/posterita-logo.gif\" alt=\"Tamak Logo\" width=\"95\" height=\"79\" border=\"0\">" +
							"</a>" +
	
							"<font style=\"font-size:20px; font-family: Verdana,Arial,Helvetica;\" color=\"#cccccc\">";	
						

	public static final String closeHeaderTitle = 
							"</font>" +
						"</td>" +
					"</tr>" +
								
					"<tr>" +
						"<td>" +
							"&nbsp;" +
						"</td>" +
					"</tr>";		

	public static final String contentSection = 
		
					"<tr>" +
						"<td align=\"center\">" +
							"<table width=\"500\">" +
								"<tr>" +
									"<td>" +
										"<table width=\"100%\">";

	public static final String separator = closeHeaderTitle + contentSection;
	
	public static final String openTagTR = "<tr>";
	public static final String closeTagTR = "</tr>";
	public static final String openTagTD = "<td>";
	public static final String closeTagTD = "</td>";
	
	public static final String openTagFontWhite = "<font style=\"font-size:12px; font-family: Verdana,Arial,Helvetica; color: #FFFFFF;\">";
	public static final String closeTagFont = "</font>";
	public static final String openTagFont = "<font style=\"font-size:12px; font-family: Verdana,Arial,Helvetica;\">";


	
	public static final String openTagTR_TD =
					openTagTR +
						openTagTD +
							"<font style=\"font-size:12px; font-family: Verdana,Arial,Helvetica;\">" ;

	public static final String closeTagTD_TR =
							"</font>" +		
						closeTagTD +
					closeTagTR;

	public static final String space = 
					"<tr>" +
						"<td>" +
							"&nbsp;" +
						"</td>" +
					"</tr>";	

	public static final String closeAndStartTagTR_TD = closeTagTD_TR + openTagTR_TD;
	
	public static final String closeTagTR_space_StartTagTR = closeTagTD_TR + space + openTagTR_TD;

	
	public static final String footer(String str)
	{
		
		String footer =					"</table>" +
									"</td>" +
								"</tr>" +
							"</table>" +
						"</td>" +
					"</tr>" +	
			
					"<tr>" +
						"<td valign=\"bottom\" align=\"right\">" +
							"<font style=\"font-size:20px; font-family: Verdana,Arial,Helvetica;\" color=\"#cccccc\">" + str + "</font>" +
						"</td>" +
					"</tr>" +
					
					"<tr>" +	
						"<td height=\"4\" bgcolor=\"#CCCCCC\" valign=\"bottom\"><img src=\"http://www.posterita.com/images/placeholder.jpg\" width=\"1\" height=\"1\" /></td>" +
					"</tr>" +		
		
					"<tr>" +
						"<td>" +
							"<font style=\"font-size:10px; font-family: Verdana,Arial,Helvetica;\">" +
								"Tamak.com" +
							"</font>" +
						"</td>" +
					"</tr>" +
				"</table>" +
			"</body>" +
		"</html>";	
		
		return footer;
	}
	
	public static final String openInnerTable()
	{
		String startInnerTable = openTagTR_TD + "<table cellspacing=\"1\" cellpadding=\"1\" border=\"0\" width=\"100%\">";
		
		return startInnerTable;
	}
	
	
	public static final String closeInnerTable()
	{
		String closeInnerTable = "</table>" + closeTagTD_TR;
		
		return closeInnerTable;
	}	
	
	public static final String innerTableHeader(String[] args)
	{
		StringBuffer strBuffer = new StringBuffer();
		
		strBuffer.append("<tr bgcolor=\"#666666\">"); 
		

		for (int i = 0; i < args.length; i++)
		{
			String field = args[i];
			
			strBuffer.append(
					  SimpleEmailLayout.openTagTD 
					+ SimpleEmailLayout.openTagFontWhite + field + SimpleEmailLayout.closeTagFont
					+ SimpleEmailLayout.closeTagTD);
		}

		
		strBuffer.append(SimpleEmailLayout.closeTagTR);	
		
		return strBuffer.toString();
	}
	
	public static final String innerTableRow(String[] args)
	{
		StringBuffer strBuffer = new StringBuffer();
		
		strBuffer.append("<tr bgcolor=\"#CCCCCC\">"); 
		
		for (int i = 0; i < args.length; i++)
		{
			String field = args[i];
			
			strBuffer.append(
					  SimpleEmailLayout.openTagTD 
					+ SimpleEmailLayout.openTagFont + field + SimpleEmailLayout.closeTagFont
					+ SimpleEmailLayout.closeTagTD);
		}
		
		strBuffer.append(SimpleEmailLayout.closeTagTR);	

		return strBuffer.toString();
	}
	
}
