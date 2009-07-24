/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.cm.xml;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * @author YS
 * @version $Id$
 */
public class XSLTProcessor
{
	/**
	 * 	Run
	 *	@param request
	 *	@param xslStream
	 *	@param xmlStream
	 *	@return xml
	 *	@throws Exception
	 */
	public static StringBuffer run (HttpServletRequest request,
		StringBuffer xslStream, StringBuffer xmlStream) throws Exception
	{
		return run(request, xslStream.toString (), xmlStream.toString ());
	}
	
	/**
	 * 	Run
	 *	@param request
	 *	@param xslStream
	 *	@param xmlStream
	 *	@return xml
	 *	@throws Exception
	 */
	public static StringBuffer run (HttpServletRequest request,
		String xslStream, String xmlStream)
		throws Exception
	{
		Calendar myCal = Calendar.getInstance ();
		TransformerFactory tFactory = TransformerFactory.newInstance ();
		Transformer transformer = tFactory.newTransformer (new StreamSource (
			new StringReader (xslStream)));
		Enumeration e = request.getParameterNames ();
		StringBuffer tStrHTML = new StringBuffer ();
		while (e.hasMoreElements ())
		{
			String name = (String)e.nextElement ();
			transformer.setParameter (name, request.getParameter (name));
		}
		OutputStream out = new ByteArrayOutputStream ();
		transformer.transform (new StreamSource (new StringReader (xmlStream)),
			new StreamResult (out));
		tStrHTML.append (out.toString ());
		if (request.getParameter ("debug") != null)
		{
			Calendar myCal2 = Calendar.getInstance ();
			long timeDiff = (myCal2.get (Calendar.HOUR_OF_DAY) * 60 * 60 * 1000)
				+ (myCal2.get (Calendar.MINUTE) * 60 * 1000)
				+ (myCal2.get (Calendar.SECOND) * 1000)
				+ myCal2.get (Calendar.MILLISECOND);
			timeDiff = timeDiff
				- ((myCal.get (Calendar.HOUR_OF_DAY) * 60 * 60 * 1000)
					+ (myCal.get (Calendar.MINUTE) * 60 * 1000)
					+ (myCal.get (Calendar.SECOND) * 1000) + myCal
					.get (Calendar.MILLISECOND));
			tStrHTML.append ("<!-- XSLT Processing done in: " + timeDiff
				+ " ms -->\n");
			System.out.println ("XSLT Processing:" + timeDiff);
		}
		return tStrHTML;
	}	//	run
	
}	//	XSLTProcessor
